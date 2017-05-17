package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.BillDetail;

/**
 * �˵��굥���ݲ�
 * @author pjz
 *
 */
public interface BillDetailDao {

	/**
	 * ��ѯ�����˵��굥
	 * @return
	 */
	List<BillDetail> queryAll();
	
	/**
	 * ���ݷ�����IP��ѯ�˵��굥
	 * @param unixHost ������IP
	 * @return
	 */
	List<BillDetail> queryByUnixHost(String unixHost);
	
	/**
	 * ����˵��굥
	 * @param billDetail
	 */
	void addBillDetail(BillDetail billDetail);
	
}
