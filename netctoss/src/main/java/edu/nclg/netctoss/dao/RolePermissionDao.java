package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.RolePermission;

/**
 * 角色管理数据层
 * 
 * Permission权限表创建完成时，便不可再被使用者更改。
 * 当确实需要更改时，可以通过版本更新的方来进行，这样使得系统更加安全。
 * @author pjz
 *
 */
public interface RolePermissionDao {

	/**
	 * 查询所有角色
	 * 显示role角色表中信息
	 * @return
	 */
	List<RolePermission> queryRole();
	
	/**
	 * 根据角色名称查询角色表
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleName(String roleName); 
	
	/**
	 * 根据角色ID查询角色表
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleId(int roleId); 
	
	/**
	 * 查询所有权限
	 * 显示所有权限表中信息
	 * @return
	 */
	List<RolePermission> queryPermission();
	
	/**
	 * 查询所有角色权限
	 * 显示role_permission表中的信息
	 * @return
	 */
	List<RolePermission> queryRolePermission();
	
	/**
	 * 根据角色ID查询该角色对应的权限信息
	 * @param roleId
	 * @return
	 */
	List<RolePermission> queryRolePermissionByRoleId(int roleId);
	
	/**
	 * 添加角色保存到role表，并获取该角色ID值
	 * @param roleName
	 */
	int addRole(RolePermission role);
	
	/**
	 * 添加角色权限信息，保存到role_permission表中
	 * @param rolePermission
	 */
	void addRolePermission(RolePermission rolePermission);
	
	/**
	 * 根据角色ID删除角色
	 * 对role表进行删除操作
	 * @param roleId
	 */
	void deleteRole(int roleId);
	
	/**
	 * 根据角色ID来删除角色权限
	 * 对role_Permission表进行删除操作
	 * @param rolePermission
	 */
	void deleteRolePermission(int roleId);
	
	/**
	 * 修改角色名称
	 * 对role表进行操作
	 * @param role
	 */
	void updateRole(RolePermission role);
	
	
	
	
	/**
	 * 主键重新排序
	 */
	void updateKey();
	
}












