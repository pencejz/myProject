package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Cost;

/**
 * �ʷ���Ϣҵ���
 * @author pjz
 *
 */
public interface CostService extends Serializable {

	public static final int USE_STATUS = 0; //�ʷ�����
	public static final int NO_USE_STATUS = 1; //�ʷ���ͣ
	
	public static final int YEAR_COSTTYPE = 0; //�ʷ�����---����
	public static final int MONTH_COSTTYPE = 1; //�ʷ�����---���£�����ʱ��
	public static final int PRICE_COSTTYPE = 2; //�ʷ�����---�ײͣ�ÿ�¹̶�ʱ��
	public static final int HOUR_COSTTYPE = 3; //�ʷ�����---��ʱ
	
	/**
	 * ��ѯ�����ʷ���Ϣ
	 * @throws Exception
	 * @return
	 */
	List<Cost> queryAll() throws Exception ;
	
	/**
	 * �����ʷ�ID��ѯ�ʷ���Ϣ
	 * @param costId
	 * @throws Exception
	 * @return
	 */
	Cost queryByCostId(int costId) throws Exception ;
	
	/**
	 * ����ʷ���Ϣ
	 * @param cost
	 * @throws Exception
	 */
	void addCost(Cost cost) throws Exception ;
	
	/**
	 * �޸��ʷ���Ϣ
	 * @param cost
	 * @throws Exception
	 */
	void updateCost(Cost cost) throws Exception ;
	
	/**
	 * �����ʷ�ID�����ʷ�״̬
	 * @param costId
	 * @throws Exception
	 */
	void updateStatus(int costId) throws Exception ;
	
	/**
	 * �����ʷ�IDɾ���ʷ���Ϣ
	 * @param costId
	 * @throws Exception
	 */
	void deleteCost(int costId) throws Exception ;
}
