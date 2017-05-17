package edu.nclg.netctoss.dao;

import java.util.List;

import edu.nclg.netctoss.entity.BillItem;

/**
 * �˵���ϸ���ݲ�
 * @author pjz
 *
 */
public interface BillItemDao {

	/**
	 * ��ѯ�����˵���ϸ
	 * @return
	 */
	List<BillItem> queryAll();
	
	/**
	 * �����˵�ϸ��ID��ѯ�˵�ϸ��
	 * @param billItemId
	 * @return
	 */
	BillItem queryByBillItemId(int billItemId);
	
	/**
	 * ���������˺�ID��ѯ�˵���Ϣ��ϸ
	 * @param accountId
	 * @return
	 */
	List<BillItem> queryByAccountId(int accountId);
	
	
	/**
	 * ���ݷ�����IP��ѯĳ�˵���ϸ
	 * @param unixHost
	 * @return
	 */
	List<BillItem> queryByUnixHost(String unixHost);
	
	/**
	 * ����˵���ϸ
	 * @param billItem
	 */
	void addBillItem(BillItem billItem);
	
}
