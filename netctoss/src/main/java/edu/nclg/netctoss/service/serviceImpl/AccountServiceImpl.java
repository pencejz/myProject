package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BusinessDao;
import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.AccountService;

/**
 * �����˺�ҵ���
 * @author pjz
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private static final long serialVersionUID = 6624106790226160877L;
	
	@Resource
	private AccountDao accountDao;
	@Resource
	private BusinessDao businessDao;

	//��ѯ���������˺���Ϣ
	@Override
	@Transactional
	public List<Account> queryAll() throws Exception {
		List<Account> list = accountDao.queryAll();
		return list;
	}

	//����ID��ѯ�����˺���Ϣ
	@Override
	@Transactional
	public Account queryByAccountId(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		return account;
	}

	
	//�������֤��ѯ�����˺���Ϣ
	@Override
	@Transactional
	public Account queryByIdcard(String idCard) throws Exception {
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("���֤���벻��Ϊ��");
		}
		Account account = accountDao.queryByIdcard(idCard);
		return account;
	}

	//������ʵ������ѯ�����˺���Ϣ
	@Override
	@Transactional
	public List<Account> queryByRealName(String realName) throws Exception {
		if(realName==null || realName.trim().isEmpty()){
			throw new ServiceException("��ʵ��������Ϊ��");
		}
		List<Account> list = accountDao.queryByRealName(realName);
		return list;
	}

	//���ݵ�¼����ѯ�����˺���Ϣ
	@Override
	@Transactional
	public Account queryByLoginName(String loginName) throws Exception {
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("��¼������Ϊ��");
		}
		Account account = accountDao.queryByLoginName(loginName);
		return account;
	}

	//����״̬��ѯ�����˺���Ϣ
	@Override
	@Transactional
	public List<Account> queryByStatus(int status) throws Exception {
		List<Account> list = accountDao.queryByStatus(status);
		return list;
	}

	//��������˺���Ϣ
	@Override
	@Transactional
	public void addAccount(Account account) throws Exception {
		if(account==null){
			throw new ServiceException("�����˺���Ϣ���ܿ�");
		}

		//�жϸ��û��Ƿ��Ѿ�ע��
		String idCard = account.getIdCard();
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("���֤���벻��Ϊ��");
		}
		if(accountDao.queryByIdcard(idCard)!=null){
			throw new ServiceException("���û��Ѿ���ͨ�����˺�");
		}
		
		//�жϸõ�¼���Ƿ��Ѿ���ʹ��
		String loginName = account.getLoginName();
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("��¼������Ϊ��");
		}
		if(accountDao.queryByLoginName(loginName)!=null){
			throw new ServiceException("���û����Ѿ�����");
		}
		
		account.setCreateDate(new Date());
		account.setStatus(CREATE_STATUS);
		accountDao.addAccount(account);
	}

	//�޸������˺���Ϣ
	@Override
	@Transactional
	public void updateAccount(Account account) throws Exception {
		if(account==null){
			throw new ServiceException("�����˺���Ϣ���ܿ�");
		}
		accountDao.updateAccount(account);
		
	}

	//����ID�޸�״̬(��ͨ��Ϊ��ͣ����ͣ��Ϊ��ͨ)
	@Override
	@Transactional
	public void updateStatus(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		
		if(account.getStatus()==CREATE_STATUS){	//��ͨ��Ϊ��ͣ
			account.setPauseDate(new Date());
			account.setStatus(PAUSE_STATUS);
			
			//��ͣ�������˺�������ҵ���˺�
			List<Business> list = businessDao.queryByAccountId(accountId);
			for(Business b:list){
				b.setStatus(1);
				businessDao.updateStatus(b);
			}
			
		}else if(account.getStatus()==PAUSE_STATUS){	//��ͣ��Ϊ��ͨ
			account.setPauseDate(null);
			account.setStatus(CREATE_STATUS);
		}
		accountDao.updateStatus(account);
	}

	//����IDɾ�������˺���Ϣ
	@Override
	@Transactional
	public void deleteAccount(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		if(account.getStatus()!=PAUSE_STATUS){
			throw new ServiceException("�������˺Ų�����ͣ״̬������ɾ��");
		}
		accountDao.deleteAccount(accountId);
		//ɾ���������˺�������ҵ���˺�
		List<Business> list = businessDao.queryByAccountId(accountId);
		for(Business b:list){
			businessDao.deleteBusiness(b.getBusinessId());
		}
		
	}


}
