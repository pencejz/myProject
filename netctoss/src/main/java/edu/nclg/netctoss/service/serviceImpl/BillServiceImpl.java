package edu.nclg.netctoss.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.BillDao;
import edu.nclg.netctoss.entity.Bill;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BillService;

@Service("billService")
public class BillServiceImpl implements BillService {

	private static final long serialVersionUID = 6168163936655510737L;

	@Resource
	private BillDao billDao;
	
	//��ѯ�����˵�
	@Override
	@Transactional
	public List<Bill> queryAll() throws Exception {
		List<Bill> list = billDao.queryAll();
		return list;
	}
	
	//�����˵�ID��ѯ�˵�
	@Override
	@Transactional
	public List<Bill> queryByAccountId(int accountId) throws Exception {
		List<Bill> list = billDao.queryAll();
		List<Bill> accountList = new ArrayList<Bill>();
		for(Bill b:list){
			if(accountId == b.getAccountId()){
				accountList.add(b);
			}
		}
		return accountList;
	}
	
	//����˵�
	@Override
	@Transactional
	public void addBill(Bill bill) throws Exception {
		if(bill==null){
			throw new ServiceException("�˵�ʵ�岻��Ϊ��");
		}
		billDao.addBill(bill);
	}


}
