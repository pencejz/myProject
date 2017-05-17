package edu.nclg.netctoss.dao;

import java.util.List;
import edu.nclg.netctoss.entity.Cost;

/**
 * �ʷ���Ϣ���ݲ�
 * @author pjz
 *
 */
public interface CostDao {

	/**
	 * ��ѯ�����ʷ���Ϣ
	 * @return
	 */
	List<Cost> queryAll();
	
	/**
	 * �����ʷ�ID��ѯ�ʷ���Ϣ
	 * @param costId
	 * @return
	 */
	Cost queryByCostId(int costId);
	
	/**
	 * �����ʷ����Ʋ�ѯ�ʷ���Ϣ
	 * @param name
	 * @return
	 */
	Cost queryByName(String name);
	
	/**
	 * ����ʷ���Ϣ
	 * @param cost
	 */
	void addCost(Cost cost);
	
	/**
	 * �޸��ʷ���Ϣ
	 * @param cost
	 */
	void updateCost(Cost cost);
	
	/**
	 * �����ʷ�ID�����ʷ�״̬
	 * @param costId
	 */
	void updateStatus(int costId);
	
	/**
	 * �����ʷ�IDɾ���ʷ���Ϣ
	 * @param costId
	 */
	void deleteCost(int costId);
	
	/**
	 * ��������
	 */
	void updateKey();
	
	
	
	
	
}
