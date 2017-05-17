package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Bill;

/**
 * 账单管理数据层
 * @author pjz
 *
 */
public interface BillDao {

	/**
	 * 查询所有账单
	 * @return
	 */
	List<Bill> queryAll();
	
	/**
	 * 添加账单
	 * @param bill
	 */
	void addBill(Bill bill);
	
	
}
