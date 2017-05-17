package edu.nclg.netctoss.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.nclg.netctoss.entity.Business;

/**
 * 业务账号信息数据层
 * @author pjz
 *
 */
public interface BusinessDao extends Serializable {

	/**
	 * 查询所有业务账号信息
	 * @return
	 */
	List<Business> queryAll();
	
	/**
	 * 根据业务账号ID查询业务账号信息
	 * @return
	 */
	Business queryByBusinessId(int businessId);
	
	/**
	 * 根据账务账号ID查询业务账号信息
	 * @param accountId
	 * @return
	 */
	List<Business> queryByAccountId(int accountId);
	
	/**
	 * 根据OS账号(宽带账号)查询用户信息
	 * @param osUserName
	 * @return
	 */
	Business queryByOsUserName(String osUserName);
	
	/**
	 * 根据用户身份证查询业务账号信息
	 * @param idCard
	 * @return
	 */
	List<Business> queryByIdcard(String idCard);
	
	/**
	 * 根据服务器IP查询业务账号信息
	 * @param unixHost
	 * @return
	 */
	Business queryByUnixHost(String unixHost);
	
	/**
	 * 根据OS账号状态查询业务账号信息
	 * @param status
	 * @return
	 */
	List<Business> queryByStatus(int status);
	
	/**
	 * 添加业务账号信息
	 * @param business
	 */
	void addBusiness(Business business);
	
	/**
	 * 修改业务账号资费类型标志flag，方便月底统一触发修改资费类型时使用
	 * @param map(businessId,flag)
	 */
	void updateFlag(Map<String,Object> map);
	
	/**
	 * 修改业务资费类型ID
	 * @param business（businessID,costId）
	 */
	void updateCostId(Business business);
	
	/**
	 * 修改业务账号状态（businessId,status,pauseDate）
	 * @param business
	 */
	void updateStatus(Business business);
	
	/**
	 * 根据ID删除业务账号信息
	 * @param businessId
	 */
	void deleteBusiness(int businessId);
	
}



