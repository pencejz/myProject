package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.BillItem;

/**
 * 账单明细数据层
 * @author pjz
 *
 */
public interface BillItemDao {

	/**
	 * 查询所有账单明细
	 * @return
	 */
	List<BillItem> queryAll();
	
	/**
	 * 根据账单细节ID查询账单细节
	 * @param billItemId
	 * @return
	 */
	BillItem queryByBillItemId(int billItemId);
	
	/**
	 * 根据账务账号ID查询账单信息明细
	 * @param accountId
	 * @return
	 */
	List<BillItem> queryByAccountId(int accountId);
	
	
	/**
	 * 根据服务器IP查询某账单明细
	 * @param unixHost
	 * @return
	 */
	List<BillItem> queryByUnixHost(String unixHost);
	
	/**
	 * 添加账单明细
	 * @param billItem
	 */
	void addBillItem(BillItem billItem);
	
}
