package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.CostDao;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.CostService;

/**
 * 资费信息业务层
 * @author pjz
 * 
 * @Service 声明为业务层
 * @Resource 默认按照名称注入
 * @Transactional 事务支持
 *
 */
@Service("costService")
public class CostServiceImpl implements CostService{

	private static final long serialVersionUID = 7601785074329607151L;

	@Resource
	private CostDao costDao;
	
	//查询所有资费信息
	@Override
	@Transactional
	public List<Cost> queryAll() {
		List<Cost> list = costDao.queryAll();
		return list;
	}

	//根据资费ID查询资费信息
	@Override
	@Transactional
	public Cost queryByCostId(int costId) throws Exception {
		Cost cost = costDao.queryByCostId(costId);
		return cost;
	}

	//添加资费信息
	@Override
	@Transactional
	public void addCost(Cost cost) throws Exception {
		if(cost==null){
			throw new ServiceException("资费信息不能为空");
		}
		
		int status = NO_USE_STATUS;
		Date createTime = new Date();
		cost.setStatus(status);
		cost.setCreateTime(createTime);
		
		String name = cost.getName();
		if(name==null || name.trim().isEmpty()){
			throw new ServiceException("资费类型名字不能为空");
		}
		if("包年".equals(name)){
			cost.setCostType(YEAR_COSTTYPE);
		}else if("包月".equals(name)){
			cost.setCostType(MONTH_COSTTYPE);
		}else if("计时收费".equals(name)){
			cost.setCostType(HOUR_COSTTYPE);
		}else{
			cost.setCostType(PRICE_COSTTYPE);
		}
		
		costDao.addCost(cost);
		costDao.updateKey();
	}

	//修改资费信息
	@Override
	@Transactional
	public void updateCost(Cost cost) throws Exception {
		if(cost==null){
			throw new ServiceException("资费信息不能为空");
		}
		Cost oldCost = costDao.queryByCostId(cost.getCostId());
		cost.setStatus(oldCost.getStatus());
		cost.setCreateTime(oldCost.getCreateTime());
		
		costDao.updateCost(cost);
		costDao.updateKey();
	}

	//根据资费ID更改资费状态(只能将默认的暂停状态改为开通状态)
	@Override
	@Transactional
	public void updateStatus(int costId) throws Exception {
		costDao.updateStatus(costId);
		Cost cost = costDao.queryByCostId(costId);
		cost.setStartTime(new Date());
		costDao.updateCost(cost);
	}

	//根据资费ID删除资费信息
	@Override
	@Transactional
	public void deleteCost(int costId) throws Exception {
		costDao.deleteCost(costId);
		costDao.updateKey();
	}

}
