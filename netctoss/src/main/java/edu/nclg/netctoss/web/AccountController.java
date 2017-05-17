package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.service.AccountService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 账务账号信息控制层
 * @author pjz
 * 
 */
@Controller
@RequestMapping("/account")
public class AccountController implements Serializable{
	
	private static final long serialVersionUID = 5631452218732279186L;
	
	@Resource
	private AccountService accountService;
	
	//查询所有账务账号信息
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Account>> queryAll(){
		try {
			List<Account> list = accountService.queryAll();
			
			return new JsonResult<List<Account>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("查询所有账务账号信息失败");
		}
	}
	
	//根据条件查询账务账号信息(身份证、姓名、状态)
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Account>> queryBy(@RequestBody Account account){
		try {
			String idcard = account.getIdCard().trim();
			String name = account.getRealName().trim();
			int status = account.getStatus();	//0开通，1暂停，2全部
			
			List<Account> list = accountService.queryAll();
			
			List<Account> accounts = new ArrayList<Account>();
			
			if(!idcard.isEmpty() && !name.isEmpty() && status!=2){	//身份证、姓名、状态都不为空
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && name.equals(a.getRealName()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && !name.isEmpty()){	//身份证、姓名不为空， 状态为空
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && name.equals(a.getRealName())){
						accounts.add(a);
					}
				}
				
			}else if(!name.isEmpty() && status!=2){	//姓名、状态不为空，身份证为空
				for(Account a:list){
					if(name.equals(a.getRealName()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && status!=2){	//身份证、状态不为空，姓名为空
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty()){	//身份证不为空，姓名、状态为空
				for(Account a:list){
					if(idcard.equals(a.getIdCard())){
						accounts.add(a);
					}
				}
				
			}else if(!name.isEmpty()){	//姓名不为空，身份证、状态为空
				for(Account a:list){
					if(name.equals(a.getRealName())){
						accounts.add(a);
					}
				}
				
			}else if(status!=2){	//状态不为空,身份证、姓名为空
				for(Account a:list){
					if(status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else{	//状态,身份证、姓名都为空
				accounts = list;
			}
			
			return new JsonResult<List<Account>>(accounts);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("查询所有账务账号信息失败");
		}
	}
	

	//根据账务账号ID查询账务账号信息
	@RequestMapping("/queryByAccountId/{accountId}")
	@ResponseBody
	public JsonResult<Account> queryByAccountId(@PathVariable int accountId){
		try {
			Account account = accountService.queryByAccountId(accountId);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("根据账务账号ID查询账务账号信息失败");
		}
	}

	//根据身份证查询账务账号信息
	@RequestMapping("/queryByIdcard/{idCard}")
	@ResponseBody
	public JsonResult<Account> queryByIdcard(@PathVariable String idCard){
		try {
			if(idCard==null || idCard.trim().isEmpty()){
				return new JsonResult<Account>("身份证号码不能为空");
			}
			Account account = accountService.queryByIdcard(idCard);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("根据身份证号码查询账务账号信息失败");
		}
	}
	
	//根据真实姓名查询账务账号信息
	@RequestMapping("/queryByRealName/{realName}")
	@ResponseBody
	public JsonResult<List<Account>> queryByRealName(@PathVariable String realName){
		try {
			if(realName==null || realName.trim().isEmpty()){
				return new JsonResult<List<Account>>("真实姓名不能为空");
			}
			List<Account> list = accountService.queryByRealName(realName);
			return new JsonResult<List<Account>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("根据真实姓名查询账务账号信息失败");
		}
	}
	
	//根据登录名查询账务账号信息
	@RequestMapping("/queryByLoginName/{loginName}")
	@ResponseBody
	public JsonResult<Account> queryByLoginName(@PathVariable String loginName){
		try {
			if(loginName==null || loginName.trim().isEmpty()){
				return new JsonResult<Account>("登录名不能为空");
			}
			Account account = accountService.queryByLoginName(loginName);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("根据登录名查询账务账号信息失败");
		}
	}
	
	
	//根据状态查询账务账号信息
	@RequestMapping("/queryByStatus/{status}")
	@ResponseBody
	public JsonResult<List<Account>> queryByStatus(@PathVariable int status){
		try {
			if(status==0 || status==1){
				List<Account> list = accountService.queryByStatus(status);
				return new JsonResult<List<Account>>(list);
			}else{
				return new JsonResult<List<Account>>("该账务账号状态信息有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("根据状态查询账务账号信息失败");
		}
	}
	
	//添加账务账号信息
	@RequestMapping("/addAccount")
	@ResponseBody
	public JsonResult<Account> addAccount(@RequestBody Account account){
		try {
			if(account == null){
				return new JsonResult<Account>("账务账号信息不能为空");
			}
			accountService.addAccount(account);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("账务账号信息添加失败");
		}
	}

	//修改账务账号信息
	@RequestMapping("/updateAccount")
	@ResponseBody
	public JsonResult<Account> updateCost(@RequestBody Account account){
		try {
			if(account == null){
				return new JsonResult<Account>("账务账号信息不能为空");
			}
			Account oldAccount = accountService.queryByAccountId(account.getAccountId());
			
			oldAccount.setTelephone(account.getTelephone());
			oldAccount.setEmail(account.getEmail());
			oldAccount.setOccupation(account.getOccupation());
			oldAccount.setGender(account.getGender());
			oldAccount.setMailaddress(account.getMailaddress());
			oldAccount.setZipCode(account.getZipCode());
			oldAccount.setQq(account.getQq());
			
			accountService.updateAccount(oldAccount);
			return new JsonResult<Account>(JsonResult.SUCCESS,"账务账号信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("账务账号信息修改失败");
		}
	}

	//根据账务账号ID更改状态
	@RequestMapping("/updateStatus/{accountId}")
	@ResponseBody
	public JsonResult<Account> updateStatus(@PathVariable int accountId){
		try {
			accountService.updateStatus(accountId);
			return new JsonResult<Account>(JsonResult.SUCCESS,"账务账号状态已更改");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("账务账号状态修改失败");
		}
	}

	//根据账务账号ID删除账务账号信息
	@RequestMapping("/deleteAccount/{accountId}")
	@ResponseBody
	public JsonResult<Account> deleteAccount(@PathVariable int accountId){
		try {
			accountService.deleteAccount(accountId);
			return new JsonResult<Account>(JsonResult.SUCCESS,"账务账号信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("账务账号信息修改失败");
		}
	}
	
	
	
}
