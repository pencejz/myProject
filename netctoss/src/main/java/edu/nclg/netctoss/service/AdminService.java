package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Admin;

/**
 * ����Աҵ���ӿ�
 */
public interface AdminService extends Serializable {

	/**
	 * ��ѯ���й���Ա��Ϣ
	 * @return
	 */
	List<Admin> queryAll() throws Exception ;
	
	/**
	 * ���ݹ���ԱID��ѯ����Ա��Ϣ
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	Admin queryByAdminId(int adminId) throws Exception ;
	
	/**
	 * ���ݽ�ɫID��ѯ����Ա��Ϣ
	 * @param roleId
	 * @return
	 */
	List<Admin> queryByRoleId(int roleId) throws Exception ;
	
	/**
	 * ����������ѯ����Ա��Ϣ
	 * @param name
	 * @return
	 */
	Admin queryByName(String name) throws Exception ;
	
	/**
	 * ���ݵ�¼����ѯ����Ա��Ϣ
	 * @param loginName
	 * @return
	 */
	Admin queryByLoginName(String loginName) throws Exception ;
	
	/**
	 * ��ӹ���Ա��Ϣ
	 * @param admin
	 */
	void addAdmin(Admin admin) throws Exception ;
	
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 */
	void updateAdmin(Admin admin) throws Exception ;
	
	/**
	 * �޸ĸ�������
	 * @param admin
	 */
	void updatePwd(Admin admin) throws Exception ;
	
	/**
	 * ���ݹ���ԱIDɾ������Ա��Ϣ
	 * @param adminId
	 */
	void deleteAdmin(int adminId) throws Exception ;

	
	
	
}
