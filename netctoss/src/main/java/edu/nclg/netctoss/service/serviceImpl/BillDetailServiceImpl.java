package edu.nclg.netctoss.service.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.BillDetailDao;
import edu.nclg.netctoss.dao.BillItemDao;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BillDetailService;

/**
 * �˵��굥ҵ���ʵ����
 * @author pjz
 *
 */
@Service("billDetailService")
public class BillDetailServiceImpl implements BillDetailService {

	private static final long serialVersionUID = 1605023002258627721L;
	
	@Resource
	private BillDetailDao billDetailDao;
	@Resource
	private BillItemDao billItemDao;

	//��ѯ�����˵��굥
	@Override
	@Transactional
	public List<BillDetail> queryAll() throws Exception {
		List<BillDetail> list = billDetailDao.queryAll();
		return list;
	}
	
	//���ݷ�����IP��ѯ�����˵��굥
	@Override
	@Transactional
	public List<BillDetail> queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("������IP����Ϊ��");
		}
		List<BillDetail> list = billDetailDao.queryByUnixHost(unixHost);
		return list;
	}
	
	//���ݷ�����IP��ѯָ������·ݵ��˵��굥
	@Override
	@Transactional
	public List<BillDetail> queryByUnixHost(int billItemId) throws Exception {
		
		//�����˵���ϸID��ã�������IP��ʱ�䣨����·ݣ�
		BillItem billItem = billItemDao.queryByBillItemId(billItemId);
		
		List<BillDetail> list = billDetailDao.queryByUnixHost(billItem.getUnixHost());	//���ݷ�����IP��������˵��굥��Ϣ
		//�������·���Ϣ
		Date date = billItem.getDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		//���ָ������·��˵��굥��Ϣ
		List<BillDetail> dateList = new ArrayList<BillDetail>();
		for(BillDetail b:list){
			calendar.setTime(b.getOutTime());
			if(year==calendar.get(Calendar.YEAR) && month==calendar.get(Calendar.MONTH)){
				dateList.add(b);
			}
			
		}
		
		return dateList;
	}

	//����˵��굥(ҵ���˻���¼ʱ���ã���¼ʱ��¼��¼ʱ�䣬�˳�ʱ��¼�˳�ʱ�䣬�������˵��굥����)
	@Override
	@Transactional
	public void addBillDetail(BillDetail billDetail) throws Exception {
		if(billDetail == null){
			throw new ServiceException("�˵��굥����Ϊ��");
		}
		billDetailDao.addBillDetail(billDetail);
	}
	
	
}
