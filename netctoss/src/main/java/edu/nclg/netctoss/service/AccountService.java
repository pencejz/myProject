package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Account;

/**
 * �����˺�ҵ���
 * @author pjz
 *
 */
public interface AccountService extends Serializable {

	public static final int CREATE_STATUS = 0; //����״̬
	public static final int PAUSE_STATUS = 1; //��ͣ״̬
	
	/**
	 * ��ѯ���������˺���Ϣ
	 * @return
	 */
	List<Account> queryAll() throws Exception ;
	
	/**
	 * ���������˺�ID��ѯ�����˺���Ϣ
	 * @param accountId
	 * @return
	 */
	Account queryByAccountId(int accountId) throws Exception ;
	
	/**
	 * �������֤��ѯ�����˺���Ϣ
	 * @param idCard
	 * @return
	 */
	Account queryByIdcard(String idCard) throws Exception ;
	
	/**
	 * ������ʵ������ѯ�����˺���Ϣ
	 * @param realName
	 * @return
	 */
	List<Account> queryByRealName(String realName) throws Exception ;
	
	/**
	 *���ݵ�¼����ѯ�����˺���Ϣ
	 * @param lastLoginName
	 * @return
	 */
	Account queryByLoginName(String loginName) throws Exception ;
	
	/**
	 * ����״̬��ѯ�����˺���Ϣ
	 * @param status
	 * @return
	 */
	List<Account> queryByStatus(int status) throws Exception ;
	
	/**
	 * ��������˺���Ϣ
	 * @param account
	 */
	void addAccount(Account account) throws Exception ;
	
	/**
	 * �޸������˺���Ϣ
	 * @param account
	 */
	void updateAccount(Account account) throws Exception ;
	
	/**
	 * ���������˺�״̬
	 * @param accountId
	 */
	void updateStatus(int accountId) throws Exception ;
	
	/**
	 * ���������˺�IDɾ�������˺���Ϣ
	 * @param accountId
	 */
	void deleteAccount(int accountId) throws Exception ;
	
}
