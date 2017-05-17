package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.BillItem;

/**
 * 账单管理业务层接口
 * @author pjz
 *
 */
public interface BillItemService extends Serializable {

	/**
	 * 查询所有账单明细
	 * @return
	 */
	List<BillItem> queryAll() throws Exception ;
	
	/**
	 * 根据账务账号ID查询账单详单信息
	 * 
	 * 
	 * 查询根据账务账号查询指定月份所有业务账号的账单详单信息
	//根据账务账号，得到所有业务账号的账单详单信息，在获得指定月份的所有业务账号的账单详单信息
	 * @param accountName
	 * @param year 年份
	 * @param month 月份
	 * @return
	 */
	List<BillItem> queryByAccountName(String accountName, int year, int month) throws Exception ;
	
	
	/**
	 * 根据服务器IP查询某账单明细
	 * @param billId
	 * @return
	 */
	List<BillItem> queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * 添加账单明细
	 * @param billItem
	 */
	void addBillItem(BillItem billItem) throws Exception ;
	
	
}
