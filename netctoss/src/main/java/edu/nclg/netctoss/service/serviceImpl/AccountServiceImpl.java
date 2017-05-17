package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BusinessDao;
import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.AccountService;

/**
 * 账务账号业务层
 * @author pjz
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private static final long serialVersionUID = 6624106790226160877L;
	
	@Resource
	private AccountDao accountDao;
	@Resource
	private BusinessDao businessDao;

	//查询所有账务账号信息
	@Override
	@Transactional
	public List<Account> queryAll() throws Exception {
		List<Account> list = accountDao.queryAll();
		return list;
	}

	//根据ID查询账务账号信息
	@Override
	@Transactional
	public Account queryByAccountId(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		return account;
	}

	
	//根据身份证查询账务账号信息
	@Override
	@Transactional
	public Account queryByIdcard(String idCard) throws Exception {
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("身份证号码不能为空");
		}
		Account account = accountDao.queryByIdcard(idCard);
		return account;
	}

	//根据真实姓名查询账务账号信息
	@Override
	@Transactional
	public List<Account> queryByRealName(String realName) throws Exception {
		if(realName==null || realName.trim().isEmpty()){
			throw new ServiceException("真实姓名不能为空");
		}
		List<Account> list = accountDao.queryByRealName(realName);
		return list;
	}

	//根据登录名查询账务账号信息
	@Override
	@Transactional
	public Account queryByLoginName(String loginName) throws Exception {
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("登录名不能为空");
		}
		Account account = accountDao.queryByLoginName(loginName);
		return account;
	}

	//根据状态查询账务账号信息
	@Override
	@Transactional
	public List<Account> queryByStatus(int status) throws Exception {
		List<Account> list = accountDao.queryByStatus(status);
		return list;
	}

	//添加账务账号信息
	@Override
	@Transactional
	public void addAccount(Account account) throws Exception {
		if(account==null){
			throw new ServiceException("账务账号信息不能空");
		}

		//判断该用户是否已经注册
		String idCard = account.getIdCard();
		if(idCard==null || idCard.trim().isEmpty()){
			throw new ServiceException("身份证号码不能为空");
		}
		if(accountDao.queryByIdcard(idCard)!=null){
			throw new ServiceException("该用户已经开通账务账号");
		}
		
		//判断该登录名是否已经被使用
		String loginName = account.getLoginName();
		if(loginName==null || loginName.trim().isEmpty()){
			throw new ServiceException("登录名不能为空");
		}
		if(accountDao.queryByLoginName(loginName)!=null){
			throw new ServiceException("该用户名已经存在");
		}
		
		account.setCreateDate(new Date());
		account.setStatus(CREATE_STATUS);
		accountDao.addAccount(account);
	}

	//修改账务账号信息
	@Override
	@Transactional
	public void updateAccount(Account account) throws Exception {
		if(account==null){
			throw new ServiceException("账务账号信息不能空");
		}
		accountDao.updateAccount(account);
		
	}

	//根据ID修改状态(开通改为暂停，暂停改为开通)
	@Override
	@Transactional
	public void updateStatus(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		
		if(account.getStatus()==CREATE_STATUS){	//开通改为暂停
			account.setPauseDate(new Date());
			account.setStatus(PAUSE_STATUS);
			
			//暂停该账务账号下所有业务账号
			List<Business> list = businessDao.queryByAccountId(accountId);
			for(Business b:list){
				b.setStatus(1);
				businessDao.updateStatus(b);
			}
			
		}else if(account.getStatus()==PAUSE_STATUS){	//暂停改为开通
			account.setPauseDate(null);
			account.setStatus(CREATE_STATUS);
		}
		accountDao.updateStatus(account);
	}

	//根据ID删除账务账号信息
	@Override
	@Transactional
	public void deleteAccount(int accountId) throws Exception {
		Account account = accountDao.queryByAccountId(accountId);
		if(account.getStatus()!=PAUSE_STATUS){
			throw new ServiceException("该账务账号不是暂停状态，不可删除");
		}
		accountDao.deleteAccount(accountId);
		//删除该账务账号下所有业务账号
		List<Business> list = businessDao.queryByAccountId(accountId);
		for(Business b:list){
			businessDao.deleteBusiness(b.getBusinessId());
		}
		
	}


}
