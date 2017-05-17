package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.service.AccountService;
import edu.nclg.netctoss.service.BillDetailService;
import edu.nclg.netctoss.service.BusinessService;
import edu.nclg.netctoss.service.CostService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * ģ��ҵ���˺��û���¼��������ţ�
 * 
 * ���롢�ǳ��ɹ�֮�󣬱��汾�ε�¼��Ϣ���˵��굥����
 * @author pjz
 */
@Controller
@RequestMapping("/testLogin")
public class TestLoginController implements Serializable{

	private static final long serialVersionUID = -3214976597125442422L;
	
	public static Date inTime;			//����ʱ��
	public static Date outTime;			//�ǳ�ʱ��
	public static Account account;		//�����˺�ʵ��
	public static Business business;	//ҵ���˺�ʵ��
	public static Cost cost;			//�ʷ�����ʵ��
	
	@Resource
	private BusinessService businessService;
	@Resource
	private AccountService accountService;
	@Resource
	private BillDetailService billDetailService;
	@Resource
	private CostService costService;
	
	//�ж��û���¼��Ϣ�Ƿ���ȷ
	@RequestMapping("/login/{userName}/{userPwd}")
	@ResponseBody
	public JsonResult<Object> testLogin(@PathVariable String userName, @PathVariable String userPwd,HttpServletRequest request){
		try {
			if(userName==null || userName.trim().isEmpty()){
				return new JsonResult<Object>("�û���¼������Ϊ��");
			}
			if(userPwd==null || userPwd.trim().isEmpty()){
				return new JsonResult<Object>("��¼���벻��Ϊ��");
			}
			
			Business business = businessService.queryByOsUserName(userName); //��¼��Ψһ��list�н���һ��Ԫ��
			if(business.getStatus()==1){
				return new JsonResult<Object>("��ҵ���˺�����ͣʹ��");
			}
			if(!business.getLoginPassword().equals(userPwd)){
				return new JsonResult<Object>("�û��������벻��ȷ������������");
			}
			//�ɹ���¼֮�󣬽���¼��Ϣ������session��
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userPwd", userPwd);
//			System.out.println(session.getAttribute("userName"));
			
			inTime = new Date(); //����ʱ��
			return new JsonResult<Object>(JsonResult.SUCCESS,"��¼�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("�û��������벻��ȷ������������");
		}
	}
	
	//�ǳ��ɹ�֮�󱣴��˵���Ϣ
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult<Object> saveBill(HttpServletRequest request){
		try {
			
			outTime = new Date(); //��¼�ǳ�ʱ��
//			HttpSession session = request.getSession();
			String userName = (String) request.getSession().getAttribute("userName"); //��session�л�ȡ�û���¼��Ϣ
			System.out.println("�ǳ��û���"+userName);
			business = businessService.queryByOsUserName(userName); //����ҵ���˺ţ�userName�����ҵ���˺������Ϣ
			account = accountService.queryByAccountId(business.getAccountId()); //���������˺�ID������������˺���Ϣ
			cost = costService.queryByCostId(business.getCostId()); //����ҵ���˺�ID����ʷ�������Ϣ
			
			//�����˵��굥��Ϣ
			saveBillDetail();
			
			//�������˺��б������һ�ε�¼ʱ��
			account.setLastLoginTime(inTime);
			accountService.updateAccount(account);
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"���ε�¼��Ϣ����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("���ε�¼��Ϣ����ʧ��");
		}
	}
	
	//�����˵��굥��Ϣ
	public void saveBillDetail() throws Exception{
		
		BillDetail billDetail = new BillDetail();
		int duration = (int)(outTime.getTime()-inTime.getTime())/1000; //����ʹ��ʱ�����룩
		
		billDetail.setDuration(duration);
		billDetail.setUnixHost(business.getUnixHost());
		billDetail.setCostId(cost.getCostId());
		billDetail.setInTime(inTime);
		billDetail.setOutTime(outTime);
		billDetail.setCharge(getCharge()); //getCharge():��ñ���ʹ�÷���
		billDetail.setCostName(cost.getName());
		
		billDetailService.addBillDetail(billDetail);
	}
	
	/*
	 * ��ȡ����ʹ�÷���
	 * 
	 * ��ѯ���굱����ʹ�õ��˵���Ϣ���õ������Ѿ�ʹ�õ�ʱ���ܺͣ����ݲ�ͬ���ײ�����(����0,����1,�ײ�2,��ʱ�շ�3)������ͬ�Ĵ���
	 * �ж��Ƿ񳬳���Χ���ݴ˵ó����η���
	 */
	public Double getCharge() throws Exception{
		
		Double charge=0.0;
		
		//�����ײ����ͷֱ������á�ע�⣺���ꡢ���¡��ײ���ʹ��ʱ�����ε���ǳ����շ�
		if(0 == cost.getCostType()){ //����
			charge = 0.0;
		}else if(1 == cost.getCostType()){ //����
			charge = 0.0;
		}else if(2 == cost.getCostType()){ //�ײ�
			
			//���ݵ����굥�����ʹ��ʱ�����ײ��ڲ��շѣ������շ�
			List<BillDetail> billDetails = getBillDetails(); //��ǰҵ���˺ŵ��굱���굥
			int usedSecond = 0; //���굱����ʹ��ʱ�����룩
			double usedHour = 0.0; //������ʹ��ʱ����Сʱ��
			for(BillDetail b:billDetails){
				usedSecond = usedSecond + b.getDuration();
			}
			usedHour = usedSecond/60.0/60;
			//�ж��Ƿ񳬳��ײͷ�Χ
			if((double)cost.getBaseDuration() >= usedHour){
				charge = 0.0;
			}else{
				charge = cost.getBaseCost()*usedHour;
			}
			
		}else if(3 == cost.getCostType()){ //��ʱ�շ�
			//��ȡʱ��
			double hour = (outTime.getTime()-inTime.getTime())/1000.0/60/60;
			charge = cost.getUnitCost() * hour;
		}
		
		return charge;
	}
	
	//��õ�ǰҵ���˺ŵ��굱���굥
	public List<BillDetail> getBillDetails() throws Exception{
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //��õ�ǰ���
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1; //��ȡ��ǰ�·�
		
		List<BillDetail> list = billDetailService.queryByUnixHost(business.getUnixHost()); //����IP��ѯ�˵��굥
		
		List<BillDetail> billDetails = new ArrayList<BillDetail>(); //���굱���˵���Ϣ
		Calendar caledar = Calendar.getInstance();
		for(BillDetail b:list){
			
			caledar.setTime(b.getOutTime()); //�Եǳ�ʱ��Ϊ׼
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && currentMonth==month){ //��ǰ��ݡ��·ݱ�����billDetails
				billDetails.add(b);
			}
			
		}
		
		return billDetails;
	}
	
	
	
	
}

