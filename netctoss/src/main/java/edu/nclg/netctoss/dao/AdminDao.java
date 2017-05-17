package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Admin;

/**
 * 管理员数据层
 */
public interface AdminDao {

	/**
	 * 查询所有管理员信息
	 * @return
	 */
	List<Admin> queryAll();
	
	/**
	 * 根据管理员ID查询管理员信息
	 * @param adminId
	 * @return
	 */
	Admin queryByAdminId(int adminId);
	
	/**
	 * 根据角色ID查询管理员信息
	 * @param roleId
	 * @return
	 */
	List<Admin> queryByRoleId(int roleId);
	
	/**
	 * 根据姓名查询管理员信息
	 * @param name
	 * @return
	 */
	Admin queryByName(String name);
	
	/**
	 * 根据登录名查询管理员信息
	 * @param loginName
	 * @return
	 */
	Admin queryByLoginName(String loginName);
	
	/**
	 * 添加管理员信息
	 * @param admin
	 */
	void addAdmin(Admin admin);
	
	/**
	 * 修改管理员信息
	 * @param admin
	 */
	void updateAdmin(Admin admin);
	
	/**
	 * 修改密码
	 * @param admin
	 */
	void updatePwd(Admin admin);
	
	
	/**
	 * 根据管理员ID删除管理员信息（需要刷新自增长ID序列）
	 * @param adminId
	 */
	void deleteAdmin(int adminId);
	
	/**
	 * 更新主键
	 */
	void updateKey();
	
	
}
