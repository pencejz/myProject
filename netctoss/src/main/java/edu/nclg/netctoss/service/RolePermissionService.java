package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.RolePermission;

/**
 * ��ɫ����ҵ���
 * @author pjz
 *
 */
public interface RolePermissionService extends Serializable {

	/**
	 * ��ѯ���н�ɫ
	 * @return
	 */
	List<RolePermission> queryRole() throws Exception ;
	
	/**
	 * ���ݽ�ɫ����ѯ��ɫ
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleName(String roleName) throws Exception ;
	
	/**
	 * ���ݽ�ɫID��ѯ��ɫ
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleId(int roleId) throws Exception ;
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	List<RolePermission> queryPermission() throws Exception ;
	
	/**
	 * ��ѯ���н�ɫ������Ȩ��
	 * @return
	 */
	List<RolePermission> queryRolePermission() throws Exception ;
	
	/**
	 * ���ݽ�ɫID��ѯ�ý�ɫ��Ӧ��Ȩ����Ϣ
	 * @param roleId
	 * @return
	 */
	List<RolePermission> queryRolePermissionByRoleId(int roleId) throws Exception ;
	
	/**
	 * ��ӽ�ɫ���浽role������ȡ�ý�ɫIDֵ
	 * @param rolePermission ������ɫ������
	 */
	int addRole(RolePermission rolePermission) throws Exception ;
	
	/**
	 * ��ӽ�ɫȨ����Ϣ�����浽role_permission����
	 * �˴���ͨ����ɫID������ɫ����Ȩ��
	 * ��ɫID��Ȩ��ID������һ�Զ�Ӧ�Ĺ�ϵ��һ��ֻ�ܱ���һ��ID����һ����ɫӵ�ж��Ȩ��ʱ����Ҫ��ε��ñ�����
	 * @param roleId
	 * @param permissionIds
	 */
	void addRolePermission(int roleId, int permissionId) throws Exception ;
	
	/**
	 * �޸Ľ�ɫ����
	 * ��role����в���
	 * @param rolePermission
	 */
	void updateRole(RolePermission rolePermission) throws Exception ;
	
	/**
	 * �޸Ľ�ɫ��Ȩ����Ϣ
	 * �ȸ��ݽ�ɫIDɾ��role_permission���еĽ�ɫȨ����Ϣ��Ȼ������޸ĺ��ɫȨ����Ϣ
	 * ��role_Permission����в���
	 * @param rolePermission
	 */
//	void updateRolePermission(RolePermission rolePermission) throws Exception ;
	
	/**
	 * ���ݽ�ɫIDɾ����ɫ
	 * ��role�����ɾ������
	 * @param roleId
	 */
	void deleteRole(int roleId) throws Exception ;
	
	/**
	 * ���ݽ�ɫID��ɾ����ɫȨ��
	 * ��role_Permission�����ɾ������
	 * @param rolePermission
	 */
	void deleteRolePermission(int roleId) throws Exception ;
	
}
