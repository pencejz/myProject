package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BusinessDao;
import edu.nclg.netctoss.dao.CostDao;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BusinessService;

/**
 * 业务账号业务层
 * @author pjz
 *
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
	
	private static final long serialVersionUID = 3702467959574317359L;
	
	@Resource
	private BusinessDao businessDao;
	
	@Resource
	private CostDao costDao;
	
	@Resource
	private AccountDao accountDao;

	//查询所有业务账号信息
	@Override
	@Transactional
	public List<Business> queryAll() throws Exception {
		List<Business> list = businessDao.queryAll();
		return list;
	}

	//根据业务账号ID查询业务账号信息
	@Override
	@Transactional
	public Business queryByBusinessId(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		return business;
	}

	//根据OS登录名(宽带账户名)查询业务账号信息
	@Override
	@Transactional
	public Business queryByOsUserName(String osUserName) throws Exception {
		if(osUserName==null || osUserName.trim().isEmpty()){
			throw new ServiceException("OS登录名不能为空");
		}
		Business business = businessDao.queryByOsUserName(osUserName);
		return business;
	}

	//根据身份证号码查询业务账号信息
	@Override
	@Transactional
	public List<Business> queryByIdcard(String idCard) throws Exception {
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("身份证号码不能为空");
		}
		List<Business> list = businessDao.queryByIdcard(idCard);
		return list;
	}

	//根据服务器IP地址查询业务账号信息
	@Override
	@Transactional
	public Business queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("服务器IP地址不能为空");
		}
		Business business = businessDao.queryByUnixHost(unixHost);
		return business;
	}

	//根据OS账号状态查询业务账号信息
	@Override
	@Transactional
	public List<Business> queryByStatus(int status) throws Exception {
		List<Business> list = businessDao.queryByStatus(status);
		return list;
	}

	//添加业务账号信息
	@Override
	@Transactional
	public void addBusiness(Business business) throws Exception {
		if(business==null){
			throw new ServiceException("业务账号信息不能为空");
		}
		
		String osUserName = business.getOsUserName();
		if(osUserName==null || osUserName.trim().isEmpty()){
			throw new ServiceException("OS账户名不能为空");
		}
		if(businessDao.queryByOsUserName(osUserName)!=null){
			throw new ServiceException("你输入的OS账户名已存在");
		}
		
		business.setStatus(CREATE_STATUS);
		business.setCreateDate(new Date());
		business.setFlag(-1);
		
		businessDao.addBusiness(business);
	}

	//修改业务账号资费类型标志flag，方便月底统一触发修改资费类型时使用
	@Override
	@Transactional
	public void updateFlag(int businessId, int flag) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("businessId", businessId);
		map.put("flag", flag);
		businessDao.updateFlag(map);
	}
	
	//修改业务账号资费类型标志flag，方便月底统一触发修改资费类型时使用
	@Override
	@Transactional
	public void updateCostId(Business business) throws Exception {
		if(business==null){
			throw new ServiceException("不存在该业务账号");
		}
		businessDao.updateCostId(business);
	}

	//根据业务账号ID修改OS账号状态
	@Override
	@Transactional
	public void updateStatus(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		if(business==null){
			throw new ServiceException("不存在该业务账号");
		}
		
		if(business.getStatus()==CREATE_STATUS){
			business.setPauseDate(new Date());
			business.setStatus(PAUSE_STATUS);
		}else if(business.getStatus()==PAUSE_STATUS){
			business.setPauseDate(null);
			business.setStatus(CREATE_STATUS);
		}
		
		businessDao.updateStatus(business);
		
	}

	//删除业务账号信息
	@Override
	@Transactional
	public void deleteBusiness(int businessId) throws Exception {
		Business business = businessDao.queryByBusinessId(businessId);
		if(business.getStatus()!=PAUSE_STATUS){
			throw new ServiceException("该业务账号不是暂停状态不可删除");
		}
		businessDao.deleteBusiness(businessId);
		
	}

	

	


}
