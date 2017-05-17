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
 * 角色管理业务实现类
 * @author pjz
 *
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	private static final long serialVersionUID = 8237694690947911186L;
	
	@Resource
	private RolePermissionDao rolePermissionDao;

	//查询所有角色
	@Override
	@Transactional
	public List<RolePermission> queryRole() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRole();
		return list;
	}
	
	//根据角色名查询角色
	@Override
	@Transactional
	public RolePermission queryRoleByRoleName(String roleName) throws Exception {
		RolePermission role = rolePermissionDao.queryRoleByRoleName(roleName);
		return role;
	}
	
	//根据角色ID查询角色
	@Override
	@Transactional
	public RolePermission queryRoleByRoleId(int roleId) throws Exception {
		RolePermission role = rolePermissionDao.queryRoleByRoleId(roleId);
		return role;
	}
	
	//查询所有权限
	@Override
	@Transactional
	public List<RolePermission> queryPermission() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryPermission();
		return list;
	}

	//查询所有角色对应的权限信息
	@Override
	@Transactional
	public List<RolePermission> queryRolePermission() throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRolePermission();
		return list;
	}

	//根据角色ID查询对应的权限信息
	@Override
	@Transactional
	public List<RolePermission> queryRolePermissionByRoleId(int roleId) throws Exception {
		List<RolePermission> list = rolePermissionDao.queryRolePermissionByRoleId(roleId);
		return list;
	}

	//添加角色，并返回角色主键值
	@Override
	@Transactional
	public int addRole(RolePermission rolePermission) throws Exception { //传入name
		
		if(rolePermission.getRoleName().trim().isEmpty()){
			throw new ServiceException("角色姓名不能为空");
		}
		
		rolePermissionDao.addRole(rolePermission); //添加角色
//		System.out.println("service---roleId====="+rolePermission.getRoleId());
		return rolePermission.getRoleId();
		
	}

	//根据角色ID添加角色的权限(角色ID，权限ID)
	@Override
	@Transactional
	public void addRolePermission(int roleId,int permissionId) throws Exception {
		
		//从数据库中获取所有角色ID，与当前参数roleId对比，判断该角色ID是否存在
		List<RolePermission> roles = rolePermissionDao.queryRole();
		for(RolePermission role:roles){
			//当该角色ID存在时，执行添加权限操作，否则抛出异常
			if(role.getRoleId()==roleId){
				//添加权限ID
				role.setPermissionId(permissionId);
				rolePermissionDao.addRolePermission(role);
			}
		}
	}

	//根据角色ID删除角色
	@Override
	@Transactional
	public void deleteRole(int roleId) throws Exception {
		rolePermissionDao.deleteRole(roleId);
		
		/*
		 * BUG:当删除的角色不是最后一个时，删除该角色后，重新排序主键后，
		 * 排在其后面的角色的主键会发生变化，这样就会与角色权限表中关联对应出错
		 */
//		rolePermissionDao.updateKey();
		
	}

	//根据角色ID删除角色的权限
	@Override
	@Transactional
	public void deleteRolePermission(int roleId) throws Exception {
		rolePermissionDao.deleteRolePermission(roleId);
	}
	
	//修改角色名称
	@Override
	@Transactional
	public void updateRole(RolePermission role) throws Exception {
		rolePermissionDao.updateRole(role);
	}



}













