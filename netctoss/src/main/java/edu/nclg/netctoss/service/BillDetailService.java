package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.BillDetail;

/**
 * �˵��굥ҵ���ӿ�
 * @author pjz
 *
 */
public interface BillDetailService extends Serializable {

	/**
	 * ��ѯ�����˵��굥
	 * @return
	 */
	List<BillDetail> queryAll() throws Exception ;
	
	/**
	 * ���ݷ�����IP��ѯ�˵��굥
	 * @param unixHost ������IP
	 * @return
	 */
	List<BillDetail> queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * ���ݷ�����IP��ѯָ������·ݵ��˵��굥
	 * @param unixHost ������IP
	 * @param billItemId
	 * @return
	 */
	List<BillDetail> queryByUnixHost(int billItemId) throws Exception ;
	
	/**
	 * ����˵��굥
	 * @param billDetail
	 */
	void addBillDetail(BillDetail billDetail) throws Exception ;
	
	
	
}
