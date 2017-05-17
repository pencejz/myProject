package edu.nclg.netctoss.dao;

import java.util.List;
import edu.nclg.netctoss.entity.Cost;

/**
 * 资费信息数据层
 * @author pjz
 *
 */
public interface CostDao {

	/**
	 * 查询所有资费信息
	 * @return
	 */
	List<Cost> queryAll();
	
	/**
	 * 根据资费ID查询资费信息
	 * @param costId
	 * @return
	 */
	Cost queryByCostId(int costId);
	
	/**
	 * 根据资费名称查询资费信息
	 * @param name
	 * @return
	 */
	Cost queryByName(String name);
	
	/**
	 * 添加资费信息
	 * @param cost
	 */
	void addCost(Cost cost);
	
	/**
	 * 修改资费信息
	 * @param cost
	 */
	void updateCost(Cost cost);
	
	/**
	 * 根据资费ID更改资费状态
	 * @param costId
	 */
	void updateStatus(int costId);
	
	/**
	 * 根据资费ID删除资费信息
	 * @param costId
	 */
	void deleteCost(int costId);
	
	/**
	 * 主键更新
	 */
	void updateKey();
	
	
	
	
	
}
