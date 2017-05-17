package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Bill;

/**
 * �˵�ϸ��ҵ���ӿ�
 * @author pjz
 *
 */
public interface BillService extends Serializable {

	/**
	 * ��ѯ�����˵�
	 * @return
	 */
	List<Bill> queryAll() throws Exception ;
	
	/**
	 * �����˺�ID��ѯ�˵�
	 * @param accountId
	 * @return
	 */
	List<Bill> queryByAccountId(int accountId) throws Exception ;
	
	/**
	 * ����˵�
	 * @param bill
	 */
	void addBill(Bill bill) throws Exception ;
	
	
}
