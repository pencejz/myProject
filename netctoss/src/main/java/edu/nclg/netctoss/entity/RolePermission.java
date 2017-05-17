package edu.nclg.netctoss.entity;

import java.io.Serializable;

/**
 * 角色权限实体类
 * 
 * 前端对应角色管理模块
 * 
 * 后端对应三张表：
 * 		role角色表
 * 		permission权限表
 * 		role_permission角色权限表
 * @author pjz
 *
 */
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = 4137357585615510675L;
	
	private int roleId; //角色ID
	private int permissionId; //权限ID
	private String roleName; //角色名称
	private String permissionName; //权限名称
	
	public RolePermission() {
		super();
	}
	public RolePermission(int roleId, int permissionId, String roleName, String permissionName) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.roleName = roleName;
		this.permissionName = permissionName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	@Override
	public String toString() {
		return "RolePermission [roleId=" + roleId + ", permissionId=" + permissionId + ", roleName=" + roleName
				+ ", permissionName=" + permissionName + "]";
	}
	
}
