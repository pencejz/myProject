package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Account;

/**
 * �����˺����ݲ�
 * @author pjz
 *
 */
public interface AccountDao {

	/**
	 * ��ѯ���������˺���Ϣ
	 * @return
	 */
	List<Account> queryAll();
	
	/**
	 * ���������˺�ID��ѯ�����˺���Ϣ
	 * @param accountId
	 * @return
	 */
	Account queryByAccountId(int accountId);
	
	/**
	 * �������֤��ѯ�����˺���Ϣ
	 * @param idCard
	 * @return
	 */
	Account queryByIdcard(String idCard);
	
	/**
	 * ������ʵ������ѯ�����˺���Ϣ
	 * @param realName
	 * @return
	 */
	List<Account> queryByRealName(String realName);
	
	/**
	 *���ݵ�¼����ѯ�����˺���Ϣ
	 * @param loginName
	 * @return
	 */
	Account queryByLoginName(String loginName);
	
	/**
	 * ����״̬��ѯ�����˺���Ϣ
	 * @param status
	 * @return
	 */
	List<Account> queryByStatus(int status);
	
	/**
	 * ��������˺���Ϣ
	 * @param account
	 */
	void addAccount(Account account);
	
	/**
	 * �޸������˺���Ϣ
	 * @param account
	 */
	void updateAccount(Account account);
	
	/**
	 * ���������˺�״̬
	 * @param account
	 */
	void updateStatus(Account account);
	
	/**
	 * ���������˺�IDɾ�������˺���Ϣ
	 * @param accountId
	 */
	void deleteAccount(int accountId);
	
}
