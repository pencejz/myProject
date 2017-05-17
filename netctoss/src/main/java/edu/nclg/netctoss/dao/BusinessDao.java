package edu.nclg.netctoss.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.nclg.netctoss.entity.Business;

/**
 * ҵ���˺���Ϣ���ݲ�
 * @author pjz
 *
 */
public interface BusinessDao extends Serializable {

	/**
	 * ��ѯ����ҵ���˺���Ϣ
	 * @return
	 */
	List<Business> queryAll();
	
	/**
	 * ����ҵ���˺�ID��ѯҵ���˺���Ϣ
	 * @return
	 */
	Business queryByBusinessId(int businessId);
	
	/**
	 * ���������˺�ID��ѯҵ���˺���Ϣ
	 * @param accountId
	 * @return
	 */
	List<Business> queryByAccountId(int accountId);
	
	/**
	 * ����OS�˺�(����˺�)��ѯ�û���Ϣ
	 * @param osUserName
	 * @return
	 */
	Business queryByOsUserName(String osUserName);
	
	/**
	 * �����û����֤��ѯҵ���˺���Ϣ
	 * @param idCard
	 * @return
	 */
	List<Business> queryByIdcard(String idCard);
	
	/**
	 * ���ݷ�����IP��ѯҵ���˺���Ϣ
	 * @param unixHost
	 * @return
	 */
	Business queryByUnixHost(String unixHost);
	
	/**
	 * ����OS�˺�״̬��ѯҵ���˺���Ϣ
	 * @param status
	 * @return
	 */
	List<Business> queryByStatus(int status);
	
	/**
	 * ���ҵ���˺���Ϣ
	 * @param business
	 */
	void addBusiness(Business business);
	
	/**
	 * �޸�ҵ���˺��ʷ����ͱ�־flag�������µ�ͳһ�����޸��ʷ�����ʱʹ��
	 * @param map(businessId,flag)
	 */
	void updateFlag(Map<String,Object> map);
	
	/**
	 * �޸�ҵ���ʷ�����ID
	 * @param business��businessID,costId��
	 */
	void updateCostId(Business business);
	
	/**
	 * �޸�ҵ���˺�״̬��businessId,status,pauseDate��
	 * @param business
	 */
	void updateStatus(Business business);
	
	/**
	 * ����IDɾ��ҵ���˺���Ϣ
	 * @param businessId
	 */
	void deleteBusiness(int businessId);
	
}



