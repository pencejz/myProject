package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.RolePermission;

/**
 * ��ɫ�������ݲ�
 * 
 * PermissionȨ�ޱ������ʱ���㲻���ٱ�ʹ���߸��ġ�
 * ��ȷʵ��Ҫ����ʱ������ͨ���汾���µķ������У�����ʹ��ϵͳ���Ӱ�ȫ��
 * @author pjz
 *
 */
public interface RolePermissionDao {

	/**
	 * ��ѯ���н�ɫ
	 * ��ʾrole��ɫ������Ϣ
	 * @return
	 */
	List<RolePermission> queryRole();
	
	/**
	 * ���ݽ�ɫ���Ʋ�ѯ��ɫ��
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleName(String roleName); 
	
	/**
	 * ���ݽ�ɫID��ѯ��ɫ��
	 * @param roleName
	 * @return
	 */
	RolePermission queryRoleByRoleId(int roleId); 
	
	/**
	 * ��ѯ����Ȩ��
	 * ��ʾ����Ȩ�ޱ�����Ϣ
	 * @return
	 */
	List<RolePermission> queryPermission();
	
	/**
	 * ��ѯ���н�ɫȨ��
	 * ��ʾrole_permission���е���Ϣ
	 * @return
	 */
	List<RolePermission> queryRolePermission();
	
	/**
	 * ���ݽ�ɫID��ѯ�ý�ɫ��Ӧ��Ȩ����Ϣ
	 * @param roleId
	 * @return
	 */
	List<RolePermission> queryRolePermissionByRoleId(int roleId);
	
	/**
	 * ��ӽ�ɫ���浽role������ȡ�ý�ɫIDֵ
	 * @param roleName
	 */
	int addRole(RolePermission role);
	
	/**
	 * ��ӽ�ɫȨ����Ϣ�����浽role_permission����
	 * @param rolePermission
	 */
	void addRolePermission(RolePermission rolePermission);
	
	/**
	 * ���ݽ�ɫIDɾ����ɫ
	 * ��role�����ɾ������
	 * @param roleId
	 */
	void deleteRole(int roleId);
	
	/**
	 * ���ݽ�ɫID��ɾ����ɫȨ��
	 * ��role_Permission�����ɾ������
	 * @param rolePermission
	 */
	void deleteRolePermission(int roleId);
	
	/**
	 * �޸Ľ�ɫ����
	 * ��role����в���
	 * @param role
	 */
	void updateRole(RolePermission role);
	
	
	
	
	/**
	 * ������������
	 */
	void updateKey();
	
}












