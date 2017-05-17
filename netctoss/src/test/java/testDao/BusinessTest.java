package testDao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.nclg.netctoss.dao.BusinessDao;
import edu.nclg.netctoss.entity.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-mybatis.xml")
public class BusinessTest {

	@Autowired
	public BusinessDao businessDao;
	
	@Test
	public void testQueryAll() {
		List<Business> list = businessDao.queryAll();
		System.out.println(list);
	}
	
	
	
	
}
