package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AdminDao;
import edu.nclg.netctoss.dao.RolePermissionDao;
import edu.nclg.netctoss.entity.Admin;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.AdminService;

/**
 * ����Աҵ��ʵ����
 * @author Administrator
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	private static final long serialVersionUID = 2885343007796706777L;
	
	@Resource
	private AdminDao adminDao;
	@Resource
	private RolePermissionDao rolePermissionDao;

	//��ѯ���й���Ա��Ϣ
	@Override
	@Transactional
	public List<Admin> queryAll() throws Exception {
		List<Admin> list = adminDao.queryAll();
		return list;
	}
	
	//��ѯ���й���Ա��Ϣ
	@Override
	@Transactional
	public Admin queryByAdminId(int adminId) throws Exception {
		Admin admin = adminDao.queryByAdminId(adminId);
		return admin;
	}

	//���ݽ�ɫID��ѯ����Ա��Ϣ
	@Override
	@Transactional
	public List<Admin> queryByRoleId(int roleId) throws Exception {
		List<Admin> list = adminDao.queryByRoleId(roleId);
		return list;
	}

	//����������ѯ����Ա��Ϣ
	@Override
	@Transactional
	public Admin queryByName(String name) throws Exception {
		if(name==null || name.trim().isEmpty()){
			throw new ServiceException("��������Ϊ��");
		}
		Admin admin = adminDao.queryByName(name);
		return admin;
	}

	//���ݵ�¼����ѯ����Ա��Ϣ
	@Override
	@Transactional
	public Admin queryByLoginName(String loginName) throws Exception {
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("��������Ϊ��");
		}
		System.out.println(adminDao);
		Admin admin = adminDao.queryByLoginName(loginName);
		return admin;
	}

	//��ӹ���Ա��Ϣ
	@Override
	@Transactional
	public void addAdmin(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("����Ա��Ϣ����Ϊ��");
		}
		//�ж��Ƿ��Ѿ����ڸõ�¼��
		Admin oldAdmin = adminDao.queryByLoginName(admin.getLoginName());
		if(oldAdmin == null){
			String roleName = rolePermissionDao.queryRoleByRoleId(admin.getRoleId()).getRoleName();
			admin.setRoleName(roleName);
			admin.setEnrollDate(new Date());
			
			adminDao.addAdmin(admin);
		}else{
			throw new ServiceException("�õ�½�Ѿ�����");
		}
	}

	//�޸Ĺ���Ա��Ϣ
	@Override
	@Transactional
	public void updateAdmin(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("����Ա��Ϣ����Ϊ��");
		}
		Admin oldAdmin = adminDao.queryByAdminId(admin.getAdminId());
		
		oldAdmin.setName(admin.getName());
		oldAdmin.setTelephone(admin.getTelephone());
		oldAdmin.setEmail(admin.getEmail());
		oldAdmin.setRoleId(admin.getRoleId());
		
		adminDao.updateAdmin(oldAdmin);
	}
	
	//�޸ĸ�������
	@Override
	@Transactional
	public void updatePwd(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("����Ա��Ϣ����Ϊ��");
		}
		adminDao.updatePwd(admin);
	}

	//����IDɾ������Ա��Ϣ
	@Override
	@Transactional
	public void deleteAdmin(int adminId) throws Exception {
		adminDao.deleteAdmin(adminId);
		adminDao.updateKey();
	}
	

}
