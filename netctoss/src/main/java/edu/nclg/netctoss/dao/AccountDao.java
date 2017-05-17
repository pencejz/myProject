package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Account;

/**
 * 账务账号数据层
 * @author pjz
 *
 */
public interface AccountDao {

	/**
	 * 查询所有账务账号信息
	 * @return
	 */
	List<Account> queryAll();
	
	/**
	 * 根据账务账号ID查询账务账号信息
	 * @param accountId
	 * @return
	 */
	Account queryByAccountId(int accountId);
	
	/**
	 * 根据身份证查询账务账号信息
	 * @param idCard
	 * @return
	 */
	Account queryByIdcard(String idCard);
	
	/**
	 * 根据真实姓名查询账务账号信息
	 * @param realName
	 * @return
	 */
	List<Account> queryByRealName(String realName);
	
	/**
	 *根据登录名查询账务账号信息
	 * @param loginName
	 * @return
	 */
	Account queryByLoginName(String loginName);
	
	/**
	 * 根据状态查询账务账号信息
	 * @param status
	 * @return
	 */
	List<Account> queryByStatus(int status);
	
	/**
	 * 添加账务账号信息
	 * @param account
	 */
	void addAccount(Account account);
	
	/**
	 * 修改账务账号信息
	 * @param account
	 */
	void updateAccount(Account account);
	
	/**
	 * 更改账务账号状态
	 * @param account
	 */
	void updateStatus(Account account);
	
	/**
	 * 根据账务账号ID删除账务账号信息
	 * @param accountId
	 */
	void deleteAccount(int accountId);
	
}
