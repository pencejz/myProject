package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.RolePermission;

/**
 * 角色管理业务层
 * @author pjz
 *
 */
public interface RolePermissionService extends Serializable {

	/**
	 * 查询所有角色
	 * @return
	 */
	List<RolePermission> queryRole() throws Exception ;
	
	/**
	 * 根据角色名查询角色
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleName(String roleName) throws Exception ;
	
	/**
	 * 根据角色ID查询角色
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleId(int roleId) throws Exception ;
	
	/**
	 * 查询所有权限
	 * @return
	 */
	List<RolePermission> queryPermission() throws Exception ;
	
	/**
	 * 查询所有角色的所有权限
	 * @return
	 */
	List<RolePermission> queryRolePermission() throws Exception ;
	
	/**
	 * 根据角色ID查询该角色对应的权限信息
	 * @param roleId
	 * @return
	 */
	List<RolePermission> queryRolePermissionByRoleId(int roleId) throws Exception ;
	
	/**
	 * 添加角色保存到role表，并获取该角色ID值
	 * @param rolePermission 包含角色名对象
	 */
	int addRole(RolePermission rolePermission) throws Exception ;
	
	/**
	 * 添加角色权限信息，保存到role_permission表中
	 * 此处是通过角色ID来给角色增加权限
	 * 角色ID和权限ID可能是一对多应的关系，一次只能保存一对ID。当一个角色拥有多个权限时，需要多次调用本方法
	 * @param roleId
	 * @param permissionIds
	 */
	void addRolePermission(int roleId, int permissionId) throws Exception ;
	
	/**
	 * 修改角色名称
	 * 对role表进行操作
	 * @param rolePermission
	 */
	void updateRole(RolePermission rolePermission) throws Exception ;
	
	/**
	 * 修改角色的权限信息
	 * 先根据角色ID删除role_permission表中的角色权限信息，然后添加修改后角色权限信息
	 * 对role_Permission表进行操作
	 * @param rolePermission
	 */
//	void updateRolePermission(RolePermission rolePermission) throws Exception ;
	
	/**
	 * 根据角色ID删除角色
	 * 对role表进行删除操作
	 * @param roleId
	 */
	void deleteRole(int roleId) throws Exception ;
	
	/**
	 * 根据角色ID来删除角色权限
	 * 对role_Permission表进行删除操作
	 * @param rolePermission
	 */
	void deleteRolePermission(int roleId) throws Exception ;
	
}
