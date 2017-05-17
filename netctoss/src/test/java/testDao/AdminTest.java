package testDao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.nclg.netctoss.dao.AdminDao;
import edu.nclg.netctoss.entity.Admin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-mybatis.xml")
public class AdminTest {

	@Autowired
	public AdminDao adminDao;
	
	
	@Test
	public void queryAll(){
		List<Admin> list = adminDao.queryAll();
		for(Admin a:list){
			System.out.println(a);
		}
	}
	
	@Test
	public void queryByRoleId(){
		List<Admin> list = adminDao.queryByRoleId(1);
		System.out.println(list);
	}
	
	
	@Test
	public void queryByName(){
		Admin a = adminDao.queryByName("ADMIN");
		System.out.println(a);
	}
	
	@Test
	public void add(){
		Admin a = new Admin();
		a.setRoleId(7);
		a.setLoginName("CJADMIN");
		a.setPassword("123456");
		a.setName("超级管理员");
		a.setEnrollDate(new Date());
		adminDao.addAdmin(a);
		System.out.println("添加成功");
	}
	
	@Test 
	public void update(){
		Admin a = new Admin();
		a.setAdminId(7001);
		a.setRoleId(7);
		a.setLoginName("CJADMIN");
		a.setPassword("123456");
		a.setName("超级管理员");
		a.setEnrollDate(new Date()); //修改时间
		System.out.println(11111111);
		adminDao.updateAdmin(a);
		System.out.println("修改成功");
	}
	
	@Test
	public void delete(){
		adminDao.deleteAdmin(7001);
		System.out.println("删除成功");
	}
	
	
	
	
}
