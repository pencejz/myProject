package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.Bill;

/**
 * �˵��������ݲ�
 * @author pjz
 *
 */
public interface BillDao {

	/**
	 * ��ѯ�����˵�
	 * @return
	 */
	List<Bill> queryAll();
	
	/**
	 * ����˵�
	 * @param bill
	 */
	void addBill(Bill bill);
	
	
}
