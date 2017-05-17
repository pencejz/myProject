package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Bill;

/**
 * 账单细节业务层接口
 * @author pjz
 *
 */
public interface BillService extends Serializable {

	/**
	 * 查询所有账单
	 * @return
	 */
	List<Bill> queryAll() throws Exception ;
	
	/**
	 * 账务账号ID查询账单
	 * @param accountId
	 * @return
	 */
	List<Bill> queryByAccountId(int accountId) throws Exception ;
	
	/**
	 * 添加账单
	 * @param bill
	 */
	void addBill(Bill bill) throws Exception ;
	
	
}
