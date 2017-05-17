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
 * 管理员业务实现类
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

	//查询所有管理员信息
	@Override
	@Transactional
	public List<Admin> queryAll() throws Exception {
		List<Admin> list = adminDao.queryAll();
		return list;
	}
	
	//查询所有管理员信息
	@Override
	@Transactional
	public Admin queryByAdminId(int adminId) throws Exception {
		Admin admin = adminDao.queryByAdminId(adminId);
		return admin;
	}

	//根据角色ID查询管理员信息
	@Override
	@Transactional
	public List<Admin> queryByRoleId(int roleId) throws Exception {
		List<Admin> list = adminDao.queryByRoleId(roleId);
		return list;
	}

	//根据姓名查询管理员信息
	@Override
	@Transactional
	public Admin queryByName(String name) throws Exception {
		if(name==null || name.trim().isEmpty()){
			throw new ServiceException("姓名不能为空");
		}
		Admin admin = adminDao.queryByName(name);
		return admin;
	}

	//根据登录名查询管理员信息
	@Override
	@Transactional
	public Admin queryByLoginName(String loginName) throws Exception {
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("姓名不能为空");
		}
		System.out.println(adminDao);
		Admin admin = adminDao.queryByLoginName(loginName);
		return admin;
	}

	//添加管理员信息
	@Override
	@Transactional
	public void addAdmin(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("管理员信息不能为空");
		}
		//判断是否已经存在该登录名
		Admin oldAdmin = adminDao.queryByLoginName(admin.getLoginName());
		if(oldAdmin == null){
			String roleName = rolePermissionDao.queryRoleByRoleId(admin.getRoleId()).getRoleName();
			admin.setRoleName(roleName);
			admin.setEnrollDate(new Date());
			
			adminDao.addAdmin(admin);
		}else{
			throw new ServiceException("该登陆已经存在");
		}
	}

	//修改管理员信息
	@Override
	@Transactional
	public void updateAdmin(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("管理员信息不能为空");
		}
		Admin oldAdmin = adminDao.queryByAdminId(admin.getAdminId());
		
		oldAdmin.setName(admin.getName());
		oldAdmin.setTelephone(admin.getTelephone());
		oldAdmin.setEmail(admin.getEmail());
		oldAdmin.setRoleId(admin.getRoleId());
		
		adminDao.updateAdmin(oldAdmin);
	}
	
	//修改个人密码
	@Override
	@Transactional
	public void updatePwd(Admin admin) throws Exception {
		if(admin == null){
			throw new ServiceException("管理员信息不能为空");
		}
		adminDao.updatePwd(admin);
	}

	//根据ID删除管理员信息
	@Override
	@Transactional
	public void deleteAdmin(int adminId) throws Exception {
		adminDao.deleteAdmin(adminId);
		adminDao.updateKey();
	}
	

}
