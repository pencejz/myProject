package testDao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.nclg.netctoss.dao.RolePermissionDao;
import edu.nclg.netctoss.entity.RolePermission;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-mybatis.xml")
public class RolePermissionTest {

	@Autowired
	public RolePermissionDao rolePermissionDao;
	
	
	@Test
	public void testQueryRole(){
		List<RolePermission> list = rolePermissionDao.queryRole();
		for(RolePermission r:list){
			System.out.println(r);
		}
	}
	
	@Test
	public void testQueryPermission(){
		List<RolePermission> list = rolePermissionDao.queryPermission();
		for(RolePermission r:list){
			System.out.println(r);
		}
	}
	
	@Test
	public void testQueryRolePermission(){
		List<RolePermission> list = rolePermissionDao.queryRolePermission();
		System.out.println(111111);
		for(RolePermission r:list){
			System.out.println(r);
		}
	}
	
	@Test
	public void testQueryRolePermissionByRoleId(){
		List<RolePermission> list = rolePermissionDao.queryRolePermissionByRoleId(200);
		for(RolePermission r:list){
			System.out.println(r);
		}
	}

	@Test
	public void testAddRole(){
		
		RolePermission r = new RolePermission();
		r.setRoleName("我是超级管理员");
		System.out.println(r);
		rolePermissionDao.addRole(r);
		System.out.println(r.getRoleId());
	}
	
	@Test
	public void testAddRolePermission(){
		
		RolePermission r = new RolePermission();
		r.setRoleId(7);
		
		r.setPermissionId(3);
		rolePermissionDao.addRolePermission(r);
		System.out.println(r);
	}
	
	@Test
	public void testDeleteRole(){
		rolePermissionDao.deleteRole(611);
		System.out.println(11111111);
	}
	
	@Test
	public void testDeleteRolePermission(){
		rolePermissionDao.deleteRolePermission(611);
		System.out.println(11111111);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
