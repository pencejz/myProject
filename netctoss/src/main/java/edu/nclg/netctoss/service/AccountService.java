package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Account;

/**
 * 账务账号业务层
 * @author pjz
 *
 */
public interface AccountService extends Serializable {

	public static final int CREATE_STATUS = 0; //创建状态
	public static final int PAUSE_STATUS = 1; //暂停状态
	
	/**
	 * 查询所有账务账号信息
	 * @return
	 */
	List<Account> queryAll() throws Exception ;
	
	/**
	 * 根据账务账号ID查询账务账号信息
	 * @param accountId
	 * @return
	 */
	Account queryByAccountId(int accountId) throws Exception ;
	
	/**
	 * 根据身份证查询账务账号信息
	 * @param idCard
	 * @return
	 */
	Account queryByIdcard(String idCard) throws Exception ;
	
	/**
	 * 根据真实姓名查询账务账号信息
	 * @param realName
	 * @return
	 */
	List<Account> queryByRealName(String realName) throws Exception ;
	
	/**
	 *根据登录名查询账务账号信息
	 * @param lastLoginName
	 * @return
	 */
	Account queryByLoginName(String loginName) throws Exception ;
	
	/**
	 * 根据状态查询账务账号信息
	 * @param status
	 * @return
	 */
	List<Account> queryByStatus(int status) throws Exception ;
	
	/**
	 * 添加账务账号信息
	 * @param account
	 */
	void addAccount(Account account) throws Exception ;
	
	/**
	 * 修改账务账号信息
	 * @param account
	 */
	void updateAccount(Account account) throws Exception ;
	
	/**
	 * 更改账务账号状态
	 * @param accountId
	 */
	void updateStatus(int accountId) throws Exception ;
	
	/**
	 * 根据账务账号ID删除账务账号信息
	 * @param accountId
	 */
	void deleteAccount(int accountId) throws Exception ;
	
}
