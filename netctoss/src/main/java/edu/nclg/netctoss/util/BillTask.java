package edu.nclg.netctoss.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.Bill;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.service.AccountService;
import edu.nclg.netctoss.service.BillDetailService;
import edu.nclg.netctoss.service.BillItemService;
import edu.nclg.netctoss.service.BillService;
import edu.nclg.netctoss.service.BusinessService;
import edu.nclg.netctoss.service.CostService;

/**
 * �˵��Զ����湤����
 * @author pjz
 */
@Component
public class BillTask implements Serializable {

	private static final long serialVersionUID = -5635898206834890825L;
	
	@Resource
	private BusinessService businessService;
	@Resource
	private AccountService accountService;
	@Resource
	private BillDetailService billDetailService;
	@Resource
	private BillItemService billItemService;
	@Resource
	private BillService billService;
	@Resource
	private CostService costService;
	
	/**
	 * �Զ������˵�ϸ����Ϣ��ʱ��Ϊÿ��1�գ��Զ����棩
	 * @throws Exception 
	 */
//	@Scheduled(cron = "*/1 * * * * ?")  //ÿ��ִ��һ�� 
//	@Scheduled(cron = "0 0 0 1 * ?" )  //ÿ��1��0ʱִ��һ�� 
	@Scheduled(cron = "*/10 * * * * ?") //ÿ10��ִ��һ��
	public void saveBillItem() throws Exception{
		System.out.println("�˵�ϸ�ڱ�����������С�����");
		List<BillDetail> billDetails = getBillDetails();	//��õ������������굥��Ϣ��ʱ�������ã��ʷ����ͣ�
		System.out.println("�������������굥------"+billDetails);
		
		Set<String> ips = new HashSet<String>();		//��ȡ�˵��굥������IP���ظ�ֻ��һ�Σ�
		for(BillDetail b:billDetails){
			ips.add(b.getUnixHost());
		}
		System.out.println("�˵��굥�д��ڵ�IP������"+ips);
		
		for(String ip:ips){	//���������˵�ϸ�ڶ���(��IPΪ��λ����)
			Business business = businessService.queryByUnixHost(ip);	//����IP���accountID��ҵ���˺�
			int accountId = business.getAccountId();
			int costId = business.getCostId();
			String osUsername = business.getOsUserName();
			Cost cost = costService.queryByCostId(costId);	//�����ʷ�ID����ʷ���Ϣ��������ÿ�µĻ������ã���ʱ��������Ϊ0��
			double totalCharge = 0.0;	//һ��ҵ���˺�һ���µķ����ܺ�
			totalCharge = cost.getBaseCost(); //���ϻ�������
			if(7 == costId){ //���꣺ÿ�·���0Ԫ
				totalCharge = 0.0;
			}
			//����ip���飬���һ��ҵ���˺����µ������˵��굥����ͳ����ʱ�����ܷ��ã��ʷ�����ID�����һ�εǳ�ʱ��
			int totalDuration = 0 ;		//һ��ҵ���˺�һ���µ�ʹ��ʱ��
			Date date = null;			//���һ�εǳ�ʱ��
			for(BillDetail b:billDetails){
				if(ip.equals(b.getUnixHost())){
					
					totalDuration = totalDuration + b.getDuration();
					totalCharge = totalCharge + b.getCharge();
					costId = b.getCostId();
					date = b.getOutTime();
				}
			}
			String strDuration = getDuration(totalDuration);	//����������ת����ַ�����xx��xxСʱxx����xx��
			BillItem billItem = new BillItem();		//���һ���˵�ϸ�ڶ���
			billItem.setUnixHost(ip);
			billItem.setAccountId(accountId);
			billItem.setCostId(costId);
			billItem.setOsUsername(osUsername);
			billItem.setDuration(strDuration);
			billItem.setBusinessCharge(totalCharge);
			billItem.setDate(date);
			billItem.setCostName(cost.getName());
			
			//����һ���˵�ϸ�ڶ�������˵�ϸ�����Ѿ����ڸö��󣬾Ͳ��ڱ���
			Calendar c = Calendar.getInstance();
			c.setTime(date);	//��ü���������˵�ϸ�ڶ����ʱ�䣨��������ʱ�䣩
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
//			System.out.println(year +"  ����������˵�ϸ�ڶ���       "+ month);
			
			List<BillItem> list = billItemService.queryByUnixHost(ip);	//����IP����˵�ϸ�ڱ����������ݶ���
			if(list.size()==0){		//��ǰIP��δ������˵�ϸ��
				
				billItemService.addBillItem(billItem);
				System.out.println("��ǰ"+ip+"���˵�ϸ��Ϊ�գ�����һ������");
				
			}else{		//��ǰIP������˵�ϸ�ڣ�����Ҫ�жϵ��ǵ��������Ƿ��Ѿ����������
				
				BillItem bit = null;  //������ʱ���浱�������˵�ϸ�ڶ���
				for(BillItem b:list){
					
					c.setTime(b.getDate());		//����˵�ϸ�ڱ��е�ʱ������
					int oldYear = c.get(Calendar.YEAR);
					int oldMonth = c.get(Calendar.MONTH)+1;
					System.out.println(oldYear +"  �˵�ϸ�ڱ������б������       "+ oldMonth);
					
					if(oldYear==year && oldMonth==month){	//�뼴��������˵�ϸ�ڶ����ʱ�����Ƚϣ�������ھͽ��ö��󱣴�����ʱ������
						bit = new BillItem();
						bit = b;
					}
					
				}
				
				if(bit == null){	//����˵�ϸ�ڱ���û�����µ��˵�ϸ����Ϣ�������
					billItemService.addBillItem(billItem);
					System.out.println("�˵�ϸ�ڱ���ɹ���������");
				}
				
			}
			
			
		}
		
	}
	
	/**
	 * �Զ������˵�������Ϣ��ʱ��Ϊÿ��1�գ��Զ����棩
	 * @throws Exception 
	 */
//	@Scheduled(cron = "0 0 0 1 * ?" )  //ÿ��ִ��һ��
	@Scheduled(cron = "*/20 * * * * ?") //ÿ10��ִ��һ��
	public void saveBill() throws Exception{
		System.out.println("�˵��������С�������");
		
		List<BillItem> billItems = getBillItems();		//��õ������������˵�ϸ����Ϣ
		System.out.println("�������������˵�ϸ�ڣ���"+billItems);
		
		Set<Integer> accountIds = new HashSet<Integer>();	//�������˵�ϸ���л�����������˺�ID
		for(BillItem b:billItems){
			accountIds.add(b.getAccountId());
		}
		
		//���������˵��������
		for(int accountId:accountIds){
			
			Account account = accountService.queryByAccountId(accountId);
			
			//���������˺�ID���飬���һ�������˺��µ�����ҵ���˺����µ��˵���ϸ��Ϣ�����������˵��������
			double totalCharge = 0.0;
			Date date = new Date();
			for(BillItem b:billItems){
				if(accountId == b.getAccountId()){
					totalCharge = totalCharge + b.getBusinessCharge();
					date = b.getDate();
				}
			}
			
			Calendar c = Calendar.getInstance();	//��ü���������˵���������£���������ʱ�䣩
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			String yearMonth = year + "��" + month + "��";
			
			//���һ���˵��������
			Bill bill = new Bill();
			bill.setAccountId(accountId);
			bill.setIdcard(account.getIdCard());
			bill.setMonthCharge(totalCharge);
			bill.setName(account.getRealName());
			bill.setYearMonth(yearMonth);
			bill.setDate(date);
			bill.setAccountName(account.getLoginName());
			System.out.println("���������˵��������"+bill);
			
			//����һ���˵��������,����˵���������Ѿ����ڸö��󣬾Ͳ���Ҫ���棨ͨ��ʱ�������жϣ�
			List<Bill> list = billService.queryByAccountId(accountId);
			
			if(list.size()==0){		//��ǰ�����˺�û�б�����˵�
				billService.addBill(bill);
				
			}else{		//��ǰ�����˺��Ѿ�������˵����ͼ����ж��Ƿ��ǵ������µ��˵�
				
				Bill bil = null;	//������ʱ���浱�������˵�������Ϣ
				for(Bill b:list){
					
					c.setTime(b.getDate());	//����˵���������Ѵ����˵����������
					int oldYear = c.get(Calendar.YEAR);
					int oldMonth = c.get(Calendar.MONTH)+1;
					System.out.println(oldYear +"   �˵���������Ѵ��ڵ��˵����������      "+ oldMonth);
					
					if(oldYear==year && oldMonth==month){	//������Ѿ��е�ʱ�����Աȣ����������·ݣ��Ѿ����ڣ��Ͳ���Ҫ�ٴα���
						bil = new Bill();
						bil = b;
					}
				}
				
				if(bil ==null){		//�������û�����µ��˵�����������
					billService.addBill(bill);
					System.out.println("�˵�������󱣴�ɹ�");
				}
				
			}
			
		}
		
	}

	//��õ������������˵��굥��Ϣ
	public List<BillDetail> getBillDetails() throws Exception{
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //��õ�ǰ���
		int lastMonth = Calendar.getInstance().get(Calendar.MONTH); //��ȡ�����·�
		
		List<BillDetail> list = billDetailService.queryAll(); //��ѯ�����˵��굥��Ϣ
		
		List<BillDetail> billDetails = new ArrayList<BillDetail>(); //���浱�굱���������˵��굥��Ϣ
		Calendar caledar = Calendar.getInstance();
		for(BillDetail b:list){
			
			caledar.setTime(b.getOutTime());    //�Եǳ�ʱ��Ϊ׼������˵��굥���������·�
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && lastMonth==month){ //��ǰ��ݡ����µ��˵��굥������billDetails
				billDetails.add(b);
			}
			
		}
		
		return billDetails;
	}
	
	//��õ������������˵�ϸ����Ϣ
	public List<BillItem> getBillItems() throws Exception{
		System.out.println("��õ������������˵�ϸ����Ϣ��������");
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //��õ�ǰ���
		int lastMonth = Calendar.getInstance().get(Calendar.MONTH); //��ȡ�����·�
		
		List<BillItem> list = billItemService.queryAll(); //��ѯ�����˵��굥��Ϣ
		
		List<BillItem> billItems = new ArrayList<BillItem>(); //���浱�굱���������˵�ϸ����Ϣ
		Calendar caledar = Calendar.getInstance();
		for(BillItem b:list){
			
			caledar.setTime(b.getDate());	//����˵�ϸ�ڶ��������·�
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && lastMonth==month){ //��ǰ��ݡ����µ��˵��굥������billDetails
				billItems.add(b);
			}
			
		}
		
		return billItems;
	}
	
	//����������ת����ַ�����xx��xxСʱxx����xx��
	public String getDuration(int second){
//		System.out.println("����������ת����ַ�����������");
		
		int minite = second/60;
		int hour = minite/60;
		int day = hour/24;
		
		String str = "";
		
		if(second/60 == 0){ //����1����
			str = second + "��";
		}else if(minite/60 == 0){  //����1Сʱ
			str = minite + "����" + second%60 + "��";
		}else if(hour/24 == 0){ //����һ��
			str = hour + "Сʱ" + minite%60 + "����" + second%60 + "��";
		}else{ //����һ��
			str = day + "��" + hour%24 + "Сʱ" + minite%60 + "����" + second%60 + "��";
		}
		
		return str;
				
	}
	
	/**
	 * �޸�ҵ���˺ŵ��ʷ�����
	 * 
	 * ͨ��flag���жϣ�Ϊ-1����Ҫ�޸ġ������ֵ������Ҫ�޸ĵ��ʷ�����ID
	 * @throws Exception 
	 */
//	@Scheduled(cron = "0 0 0 1 * ?" )  //ÿ��1��0ʱִ��һ�� 
	@Scheduled(cron = "*/10 * * * * ?") //ÿ10��ִ��һ��
	public void updateCostId() throws Exception{
//		System.out.println("�޸��ʷ����Ͳ���ִ���С���������");
		List<Business> list = businessService.queryAll();
		for(Business b:list){
			if(b.getFlag() != -1){
				Business business = b;
				business.setCostId(b.getFlag());
				businessService.updateCostId(business);
				//��flag����Ϊ-1
				businessService.updateFlag(business.getBusinessId(), -1);
			}
			
		}
		
	}
	
}









