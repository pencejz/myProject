package edu.nclg.netctoss.entity;

import java.io.Serializable;

/**
 * ��ɫȨ��ʵ����
 * 
 * ǰ�˶�Ӧ��ɫ����ģ��
 * 
 * ��˶�Ӧ���ű�
 * 		role��ɫ��
 * 		permissionȨ�ޱ�
 * 		role_permission��ɫȨ�ޱ�
 * @author pjz
 *
 */
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = 4137357585615510675L;
	
	private int roleId; //��ɫID
	private int permissionId; //Ȩ��ID
	private String roleName; //��ɫ����
	private String permissionName; //Ȩ������
	
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
