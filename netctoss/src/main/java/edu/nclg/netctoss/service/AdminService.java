package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Admin;

/**
 * 管理员业务层接口
 */
public interface AdminService extends Serializable {

	/**
	 * 查询所有管理员信息
	 * @return
	 */
	List<Admin> queryAll() throws Exception ;
	
	/**
	 * 根据管理员ID查询管理员信息
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	Admin queryByAdminId(int adminId) throws Exception ;
	
	/**
	 * 根据角色ID查询管理员信息
	 * @param roleId
	 * @return
	 */
	List<Admin> queryByRoleId(int roleId) throws Exception ;
	
	/**
	 * 根据姓名查询管理员信息
	 * @param name
	 * @return
	 */
	Admin queryByName(String name) throws Exception ;
	
	/**
	 * 根据登录名查询管理员信息
	 * @param loginName
	 * @return
	 */
	Admin queryByLoginName(String loginName) throws Exception ;
	
	/**
	 * 添加管理员信息
	 * @param admin
	 */
	void addAdmin(Admin admin) throws Exception ;
	
	/**
	 * 修改管理员信息
	 * @param admin
	 */
	void updateAdmin(Admin admin) throws Exception ;
	
	/**
	 * 修改个人密码
	 * @param admin
	 */
	void updatePwd(Admin admin) throws Exception ;
	
	/**
	 * 根据管理员ID删除管理员信息
	 * @param adminId
	 */
	void deleteAdmin(int adminId) throws Exception ;

	
	
	
}
