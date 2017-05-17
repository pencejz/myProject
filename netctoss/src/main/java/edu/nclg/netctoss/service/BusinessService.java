package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Business;

/**
 * ҵ���˺�ҵ���
 * @author pjz
 *
 */
public interface BusinessService extends Serializable {

	public static final int CREATE_STATUS = 0; //����״̬
	public static final int PAUSE_STATUS = 1; //��ͣ״̬
	
	/**
	 * ��ѯ����ҵ���˺���Ϣ
	 * @return
	 */
	List<Business> queryAll() throws Exception ;
	
	/**
	 * ����ҵ���˺�ID��ѯҵ���˺���Ϣ
	 * @return
	 */
	Business queryByBusinessId(int businessId) throws Exception ;
	
	/**
	 * ����OS�˺�(����˺�)��ѯ�û���Ϣ
	 * @param osUserName
	 * @return
	 */
	Business queryByOsUserName(String osUserName) throws Exception ;
	
	/**
	 * �����û����֤��ѯҵ���˺���Ϣ
	 * @param idCard
	 * @return
	 */
	List<Business> queryByIdcard(String idCard) throws Exception ;
	
	/**
	 * ���ݷ�����ID��ѯҵ���˺���Ϣ
	 * @param unixHost
	 * @return
	 */
	Business queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * ����OS�˺�״̬��ѯҵ���˺���Ϣ
	 * @param status
	 * @return
	 */
	List<Business> queryByStatus(int status) throws Exception ;
	
	/**
	 * ���ҵ���˺���Ϣ
	 * @param business
	 */
	void addBusiness(Business business) throws Exception ;
	
	/**
	 * �޸�ҵ���˺��ʷ����ͱ�־flag�������µ�ͳһ�����޸��ʷ�����ʱʹ��
	 * @param businessId
	 * @param flag �ʷ������޸ı�־
	 */
	void updateFlag(int businessId, int flag) throws Exception ;
	
	void updateCostId(Business business) throws Exception ;
	
	/**
	 * �޸�ҵ���˺�״̬��businessId,status,pauseDate��
	 * @param business
	 */
	void updateStatus(int businessId) throws Exception ;
	
	/**
	 * ����IDɾ��ҵ���˺���Ϣ
	 * @param businessId
	 */
	void deleteBusiness(int businessId) throws Exception ;
	
}
