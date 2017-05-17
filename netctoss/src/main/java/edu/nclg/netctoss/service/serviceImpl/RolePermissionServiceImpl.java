package edu.nclg.netctoss.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.RolePermissionDao;
import edu.nclg.netctoss.entity.RolePermission;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.RolePermissionService;

/**
 * ��ɫ����ҵ��ʵ����
 * @author pjz
 *
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	private static final long serialVersionUID = 8237694690947911186L;
	
	@Resource
	private RolePermissionDao rolePermissionDao;

	//��ѯ���н�ɫ
	@Override
	@Transactional
	public List<RolePermission> queryRole() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRole();
		return list;
	}
	
	//���ݽ�ɫ����ѯ��ɫ
	@Override
	@Transactional
	public RolePermission queryRoleByRoleName(String roleName) throws Exception {
		RolePermission role = rolePermissionDao.queryRoleByRoleName(roleName);
		return role;
	}
	
	//���ݽ�ɫID��ѯ��ɫ
	@Override
	@Transactional
	public RolePermission queryRoleByRoleId(int roleId) throws Exception {
		RolePermission role = rolePermissionDao.queryRoleByRoleId(roleId);
		return role;
	}
	
	//��ѯ����Ȩ��
	@Override
	@Transactional
	public List<RolePermission> queryPermission() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryPermission();
		return list;
	}

	//��ѯ���н�ɫ��Ӧ��Ȩ����Ϣ
	@Override
	@Transactional
	public List<RolePermission> queryRolePermission() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRolePermission();
		return list;
	}

	//���ݽ�ɫID��ѯ��Ӧ��Ȩ����Ϣ
	@Override
	@Transactional
	public List<RolePermission> queryRolePermissionByRoleId(int roleId) throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRolePermissionByRoleId(roleId);
		return list;
	}

	//��ӽ�ɫ�������ؽ�ɫ����ֵ
	@Override
	@Transactional
	public int addRole(RolePermission rolePermission) throws Exception { //����name
		
		if(rolePermission.getRoleName().trim().isEmpty()){
			throw new ServiceException("��ɫ��������Ϊ��");
		}
		
		rolePermissionDao.addRole(rolePermission); //��ӽ�ɫ
//		System.out.println("service---roleId====="+rolePermission.getRoleId());
		return rolePermission.getRoleId();
		
	}

	//���ݽ�ɫID��ӽ�ɫ��Ȩ��(��ɫID��Ȩ��ID)
	@Override
	@Transactional
	public void addRolePermission(int roleId,int permissionId) throws Exception {
		
		//�����ݿ��л�ȡ���н�ɫID���뵱ǰ����roleId�Աȣ��жϸý�ɫID�Ƿ����
		List<RolePermission> roles = rolePermissionDao.queryRole();
		for(RolePermission role:roles){
			//���ý�ɫID����ʱ��ִ�����Ȩ�޲����������׳��쳣
			if(role.getRoleId()==roleId){
				//���Ȩ��ID
				role.setPermissionId(permissionId);
				rolePermissionDao.addRolePermission(role);
			}
		}
	}

	//���ݽ�ɫIDɾ����ɫ
	@Override
	@Transactional
	public void deleteRole(int roleId) throws Exception {
		rolePermissionDao.deleteRole(roleId);
		
		/*
		 * BUG:��ɾ���Ľ�ɫ�������һ��ʱ��ɾ���ý�ɫ����������������
		 * ���������Ľ�ɫ�������ᷢ���仯�������ͻ����ɫȨ�ޱ��й�����Ӧ����
		 */
//		rolePermissionDao.updateKey();
		
	}

	//���ݽ�ɫIDɾ����ɫ��Ȩ��
	@Override
	@Transactional
	public void deleteRolePermission(int roleId) throws Exception {
		rolePermissionDao.deleteRolePermission(roleId);
	}
	
	//�޸Ľ�ɫ����
	@Override
	@Transactional
	public void updateRole(RolePermission role) throws Exception {
		rolePermissionDao.updateRole(role);
	}



}













