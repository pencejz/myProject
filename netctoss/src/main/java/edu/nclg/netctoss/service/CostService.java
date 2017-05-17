package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Cost;

/**
 * 资费信息业务层
 * @author pjz
 *
 */
public interface CostService extends Serializable {

	public static final int USE_STATUS = 0; //资费启用
	public static final int NO_USE_STATUS = 1; //资费暂停
	
	public static final int YEAR_COSTTYPE = 0; //资费类型---包年
	public static final int MONTH_COSTTYPE = 1; //资费类型---包月，不限时长
	public static final int PRICE_COSTTYPE = 2; //资费类型---套餐，每月固定时长
	public static final int HOUR_COSTTYPE = 3; //资费类型---计时
	
	/**
	 * 查询所有资费信息
	 * @throws Exception
	 * @return
	 */
	List<Cost> queryAll() throws Exception ;
	
	/**
	 * 根据资费ID查询资费信息
	 * @param costId
	 * @throws Exception
	 * @return
	 */
	Cost queryByCostId(int costId) throws Exception ;
	
	/**
	 * 添加资费信息
	 * @param cost
	 * @throws Exception
	 */
	void addCost(Cost cost) throws Exception ;
	
	/**
	 * 修改资费信息
	 * @param cost
	 * @throws Exception
	 */
	void updateCost(Cost cost) throws Exception ;
	
	/**
	 * 根据资费ID更改资费状态
	 * @param costId
	 * @throws Exception
	 */
	void updateStatus(int costId) throws Exception ;
	
	/**
	 * 根据资费ID删除资费信息
	 * @param costId
	 * @throws Exception
	 */
	void deleteCost(int costId) throws Exception ;
}
