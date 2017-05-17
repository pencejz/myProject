package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BusinessDao;
import edu.nclg.netctoss.dao.CostDao;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BusinessService;

/**
 * ҵ���˺�ҵ���
 * @author pjz
 *
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
	
	private static final long serialVersionUID = 3702467959574317359L;
	
	@Resource
	private BusinessDao businessDao;
	
	@Resource
	private CostDao costDao;
	
	@Resource
	private AccountDao accountDao;

	//��ѯ����ҵ���˺���Ϣ
	@Override
	@Transactional
	public List<Business> queryAll() throws Exception {
		List<Business> list = businessDao.queryAll();
		return list;
	}

	//����ҵ���˺�ID��ѯҵ���˺���Ϣ
	@Override
	@Transactional
	public Business queryByBusinessId(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		return business;
	}

	//����OS��¼��(����˻���)��ѯҵ���˺���Ϣ
	@Override
	@Transactional
	public Business queryByOsUserName(String osUserName) throws Exception {
		if(osUserName==null || osUserName.trim().isEmpty()){
			throw new ServiceException("OS��¼������Ϊ��");
		}
		Business business = businessDao.queryByOsUserName(osUserName);
		return business;
	}

	//�������֤�����ѯҵ���˺���Ϣ
	@Override
	@Transactional
	public List<Business> queryByIdcard(String idCard) throws Exception {
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("���֤���벻��Ϊ��");
		}
		List<Business> list = businessDao.queryByIdcard(idCard);
		return list;
	}

	//���ݷ�����IP��ַ��ѯҵ���˺���Ϣ
	@Override
	@Transactional
	public Business queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("������IP��ַ����Ϊ��");
		}
		Business business = businessDao.queryByUnixHost(unixHost);
		return business;
	}

	//����OS�˺�״̬��ѯҵ���˺���Ϣ
	@Override
	@Transactional
	public List<Business> queryByStatus(int status) throws Exception {
		List<Business> list = businessDao.queryByStatus(status);
		return list;
	}

	//���ҵ���˺���Ϣ
	@Override
	@Transactional
	public void addBusiness(Business business) throws Exception {
		if(business==null){
			throw new ServiceException("ҵ���˺���Ϣ����Ϊ��");
		}
		
		String osUserName = business.getOsUserName();
		if(osUserName==null || osUserName.trim().isEmpty()){
			throw new ServiceException("OS�˻�������Ϊ��");
		}
		if(businessDao.queryByOsUserName(osUserName)!=null){
			throw new ServiceException("�������OS�˻����Ѵ���");
		}
		
		business.setStatus(CREATE_STATUS);
		business.setCreateDate(new Date());
		business.setFlag(-1);
		
		businessDao.addBusiness(business);
	}

	//�޸�ҵ���˺��ʷ����ͱ�־flag�������µ�ͳһ�����޸��ʷ�����ʱʹ��
	@Override
	@Transactional
	public void updateFlag(int businessId, int flag) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("businessId", businessId);
		map.put("flag", flag);
		businessDao.updateFlag(map);
	}
	
	//�޸�ҵ���˺��ʷ����ͱ�־flag�������µ�ͳһ�����޸��ʷ�����ʱʹ��
	@Override
	@Transactional
	public void updateCostId(Business business) throws Exception {
		if(business==null){
			throw new ServiceException("�����ڸ�ҵ���˺�");
		}
		businessDao.updateCostId(business);
	}

	//����ҵ���˺�ID�޸�OS�˺�״̬
	@Override
	@Transactional
	public void updateStatus(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		if(business==null){
			throw new ServiceException("�����ڸ�ҵ���˺�");
		}
		
		if(business.getStatus()==CREATE_STATUS){
			business.setPauseDate(new Date());
			business.setStatus(PAUSE_STATUS);
		}else if(business.getStatus()==PAUSE_STATUS){
			business.setPauseDate(null);
			business.setStatus(CREATE_STATUS);
		}
		
		businessDao.updateStatus(business);
		
	}

	//ɾ��ҵ���˺���Ϣ
	@Override
	@Transactional
	public void deleteBusiness(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		if(business.getStatus()!=PAUSE_STATUS){
			throw new ServiceException("��ҵ���˺Ų�����ͣ״̬����ɾ��");
		}
		businessDao.deleteBusiness(businessId);
		
	}

	

	


}
