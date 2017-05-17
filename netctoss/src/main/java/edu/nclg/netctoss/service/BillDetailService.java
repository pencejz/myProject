package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.BillDetail;

/**
 * 账单详单业务层接口
 * @author pjz
 *
 */
public interface BillDetailService extends Serializable {

	/**
	 * 查询所有账单详单
	 * @return
	 */
	List<BillDetail> queryAll() throws Exception ;
	
	/**
	 * 根据服务器IP查询账单详单
	 * @param unixHost 服务器IP
	 * @return
	 */
	List<BillDetail> queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * 根据服务器IP查询指定年份月份的账单详单
	 * @param unixHost 服务器IP
	 * @param billItemId
	 * @return
	 */
	List<BillDetail> queryByUnixHost(int billItemId) throws Exception ;
	
	/**
	 * 添加账单详单
	 * @param billDetail
	 */
	void addBillDetail(BillDetail billDetail) throws Exception ;
	
	
	
}
