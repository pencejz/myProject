package edu.nclg.netctoss.service;

import java.io.Serializable;
import java.util.List;

import edu.nclg.netctoss.entity.Business;

/**
 * 业务账号业务层
 * @author pjz
 *
 */
public interface BusinessService extends Serializable {

	public static final int CREATE_STATUS = 0; //创建状态
	public static final int PAUSE_STATUS = 1; //暂停状态
	
	/**
	 * 查询所有业务账号信息
	 * @return
	 */
	List<Business> queryAll() throws Exception ;
	
	/**
	 * 根据业务账号ID查询业务账号信息
	 * @return
	 */
	Business queryByBusinessId(int businessId) throws Exception ;
	
	/**
	 * 根据OS账号(宽带账号)查询用户信息
	 * @param osUserName
	 * @return
	 */
	Business queryByOsUserName(String osUserName) throws Exception ;
	
	/**
	 * 根据用户身份证查询业务账号信息
	 * @param idCard
	 * @return
	 */
	List<Business> queryByIdcard(String idCard) throws Exception ;
	
	/**
	 * 根据服务器ID查询业务账号信息
	 * @param unixHost
	 * @return
	 */
	Business queryByUnixHost(String unixHost) throws Exception ;
	
	/**
	 * 根据OS账号状态查询业务账号信息
	 * @param status
	 * @return
	 */
	List<Business> queryByStatus(int status) throws Exception ;
	
	/**
	 * 添加业务账号信息
	 * @param business
	 */
	void addBusiness(Business business) throws Exception ;
	
	/**
	 * 修改业务账号资费类型标志flag，方便月底统一触发修改资费类型时使用
	 * @param businessId
	 * @param flag 资费类型修改标志
	 */
	void updateFlag(int businessId, int flag) throws Exception ;
	
	void updateCostId(Business business) throws Exception ;
	
	/**
	 * 修改业务账号状态（businessId,status,pauseDate）
	 * @param business
	 */
	void updateStatus(int businessId) throws Exception ;
	
	/**
	 * 根据ID删除业务账号信息
	 * @param businessId
	 */
	void deleteBusiness(int businessId) throws Exception ;
	
}
