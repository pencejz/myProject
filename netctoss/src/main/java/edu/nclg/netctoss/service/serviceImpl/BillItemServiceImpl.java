package edu.nclg.netctoss.service.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BillItemDao;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BillItemService;

@Service("billItemService")
public class BillItemServiceImpl implements BillItemService {
	
	private static final long serialVersionUID = 615406081219826155L;
	
	@Resource
	private BillItemDao billItemDao;
	@Resource
	private AccountDao accountDao;
	
	//��ѯ�����˵��굥��Ϣ
	@Override
	@Transactional
	public List<BillItem> queryAll() throws Exception {
		List<BillItem> list = billItemDao.queryAll();
		return list;
	}

	//�˵�����ҳ��������ϸ��ʱ�����ñ�����
	//���������˺Ų�ѯָ���·�����ҵ���˺ŵ��˵��굥��Ϣ
	//���������˺ţ��õ�����ҵ���˺ŵ��˵��굥��Ϣ���ڻ��ָ���·ݵ�����ҵ���˺ŵ��˵��굥��Ϣ
	@Override
	@Transactional
	public List<BillItem> queryByAccountName(String accountName, int year, int month) throws Exception {
		if(accountName==null || accountName.trim().isEmpty()){
			throw new ServiceException("�����˺Ų���Ϊ��");
		}
		
		//���������˺ŵ�¼���Ƶõ������˺�ID
		int accountId = accountDao.queryByLoginName(accountName).getAccountId();
		//���������˺�ID�������ҵ���˺��˵���ϸ
		List<BillItem> list = billItemDao.queryByAccountId(accountId);
		//���ָ������·� ����ҵ���˺��˵���ϸ
		List<BillItem> businessList = new ArrayList<BillItem>();
		for(BillItem b:list){
			
			Calendar c = Calendar.getInstance();
			c.setTime(b.getDate());
			
			if(year==c.get(Calendar.YEAR) && month==c.get(Calendar.MONTH)+1){
				businessList.add(b);
			}
			
		}
		System.out.println(businessList);
		return businessList;
	}
	
	//����ip��ѯ�����˵�ϸ����Ϣ
	@Override
	@Transactional
	public List<BillItem> queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("IP����Ϊ��");
		}
		List<BillItem> list = billItemDao.queryByUnixHost(unixHost);
		return list;
	}
	
	//����˵�ϸ����Ϣ
	@Override
	@Transactional
	public void addBillItem(BillItem billItem) throws Exception {
		if(billItem==null){
			throw new ServiceException("�˵�ϸ����Ϣ����Ϊ��");
		}
		billItemDao.addBillItem(billItem);
		
	}

	
	
	

}
