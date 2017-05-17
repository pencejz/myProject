package testDao;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.nclg.netctoss.dao.CostDao;
import edu.nclg.netctoss.entity.Cost;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-mybatis.xml")
public class CostTest {

	@Autowired
	public CostDao costDao;
	
	@Test
	public void testQueryAll() {
		List<Cost> list = costDao.queryAll();
		System.out.println(list);
	}
	
	@Test
	public void testQueryByCostId(){
		Cost cost = costDao.queryByCostId(1);
		System.out.println(cost);
	}
	
	@Test
	public void testAddCost(){
		Cost cost = new Cost(10,"����",0,100.0,0.0,0,"����100,,������ʱ��",new Date(),new Date(),4);
		costDao.addCost(cost);
		System.out.println(11111);
	}
	
	@Test
	public void testUpdateCost(){
		Cost cost = new Cost(10,"��baobao��",0,100.0,0.0,0,"����100,,������ʱ��",new Date(),new Date(),4);
		costDao.updateCost(cost);
		System.out.println(2222);
	}
	
	@Test
	public void testUpdateStatus(){
		int costId = 10;
		costDao.updateStatus(costId);
		System.out.println(3333);
	}
	
	@Test
	public void testDeleteCost(){
		costDao.deleteCost(10);
		System.out.println(4444);
	}
	
	
}
