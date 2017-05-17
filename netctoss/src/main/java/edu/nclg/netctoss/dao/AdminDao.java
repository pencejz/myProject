package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Admin;

/**
 * ����Ա���ݲ�
 */
public interface AdminDao {

	/**
	 * ��ѯ���й���Ա��Ϣ
	 * @return
	 */
	List<Admin> queryAll();
	
	/**
	 * ���ݹ���ԱID��ѯ����Ա��Ϣ
	 * @param adminId
	 * @return
	 */
	Admin queryByAdminId(int adminId);
	
	/**
	 * ���ݽ�ɫID��ѯ����Ա��Ϣ
	 * @param roleId
	 * @return
	 */
	List<Admin> queryByRoleId(int roleId);
	
	/**
	 * ����������ѯ����Ա��Ϣ
	 * @param name
	 * @return
	 */
	Admin queryByName(String name);
	
	/**
	 * ���ݵ�¼����ѯ����Ա��Ϣ
	 * @param loginName
	 * @return
	 */
	Admin queryByLoginName(String loginName);
	
	/**
	 * ��ӹ���Ա��Ϣ
	 * @param admin
	 */
	void addAdmin(Admin admin);
	
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 */
	void updateAdmin(Admin admin);
	
	/**
	 * �޸�����
	 * @param admin
	 */
	void updatePwd(Admin admin);
	
	
	/**
	 * ���ݹ���ԱIDɾ������Ա��Ϣ����Ҫˢ��������ID���У�
	 * @param adminId
	 */
	void deleteAdmin(int adminId);
	
	/**
	 * ��������
	 */
	void updateKey();
	
	
}
