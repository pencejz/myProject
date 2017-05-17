package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.BillDetail;

/**
 * 账单详单数据层
 * @author pjz
 *
 */
public interface BillDetailDao {

	/**
	 * 查询所有账单详单
	 * @return
	 */
	List<BillDetail> queryAll();
	
	/**
	 * 根据服务器IP查询账单详单
	 * @param unixHost 服务器IP
	 * @return
	 */
	List<BillDetail> queryByUnixHost(String unixHost);
	
	/**
	 * 添加账单详单
	 * @param billDetail
	 */
	void addBillDetail(BillDetail billDetail);
	
}
