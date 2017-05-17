package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.BillItem;

/**
 * �˵�����ҵ���ӿ�
 * @author pjz
 *
 */
public interface BillItemService extends Serializable {

	/**
	 * ��ѯ�����˵���ϸ
	 * @return
	 */
	List<BillItem> queryAll() throws Exception ;
	
	/**
	 * ���������˺�ID��ѯ�˵��굥��Ϣ
	 * 
	 * 
	 * ��ѯ���������˺Ų�ѯָ���·�����ҵ���˺ŵ��˵��굥��Ϣ
	//���������˺ţ��õ�����ҵ���˺ŵ��˵��굥��Ϣ���ڻ��ָ���·ݵ�����ҵ���˺ŵ��˵��굥��Ϣ
	 * @param accountName
	 * @param year ���
	 * @param month �·�
	 * @return
	 */
	List<BillItem> queryByAccountName(String accountName, int year, int month) throws Exception ;
	
	
	/**
	 * ���ݷ�����IP��ѯĳ�˵���ϸ
	 * @param billId
	 * @return
	 */
	List<BillItem> queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * ����˵���ϸ
	 * @param billItem
	 */
	void addBillItem(BillItem billItem) throws Exception ;
	
	
}
