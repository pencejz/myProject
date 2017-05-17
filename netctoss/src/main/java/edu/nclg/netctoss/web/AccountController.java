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
 * �����˺���Ϣ���Ʋ�
 * @author pjz
 * 
 */
@Controller
@RequestMapping("/account")
public class AccountController implements Serializable{
	
	private static final long serialVersionUID = 5631452218732279186L;
	
	@Resource
	private AccountService accountService;
	
	//��ѯ���������˺���Ϣ
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Account>> queryAll(){
		try {
			List<Account> list = accountService.queryAll();
			
			return new JsonResult<List<Account>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("��ѯ���������˺���Ϣʧ��");
		}
	}
	
	//����������ѯ�����˺���Ϣ(���֤��������״̬)
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Account>> queryBy(@RequestBody Account account){
		try {
			String idcard = account.getIdCard().trim();
			String name = account.getRealName().trim();
			int status = account.getStatus();	//0��ͨ��1��ͣ��2ȫ��
			
			List<Account> list = accountService.queryAll();
			
			List<Account> accounts = new ArrayList<Account>();
			
			if(!idcard.isEmpty() && !name.isEmpty() && status!=2){	//���֤��������״̬����Ϊ��
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && name.equals(a.getRealName()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && !name.isEmpty()){	//���֤��������Ϊ�գ� ״̬Ϊ��
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && name.equals(a.getRealName())){
						accounts.add(a);
					}
				}
				
			}else if(!name.isEmpty() && status!=2){	//������״̬��Ϊ�գ����֤Ϊ��
				for(Account a:list){
					if(name.equals(a.getRealName()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && status!=2){	//���֤��״̬��Ϊ�գ�����Ϊ��
				for(Account a:list){
					if(idcard.equals(a.getIdCard()) && status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else if(!idcard.isEmpty()){	//���֤��Ϊ�գ�������״̬Ϊ��
				for(Account a:list){
					if(idcard.equals(a.getIdCard())){
						accounts.add(a);
					}
				}
				
			}else if(!name.isEmpty()){	//������Ϊ�գ����֤��״̬Ϊ��
				for(Account a:list){
					if(name.equals(a.getRealName())){
						accounts.add(a);
					}
				}
				
			}else if(status!=2){	//״̬��Ϊ��,���֤������Ϊ��
				for(Account a:list){
					if(status==a.getStatus()){
						accounts.add(a);
					}
				}
				
			}else{	//״̬,���֤��������Ϊ��
				accounts = list;
			}
			
			return new JsonResult<List<Account>>(accounts);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("��ѯ���������˺���Ϣʧ��");
		}
	}
	

	//���������˺�ID��ѯ�����˺���Ϣ
	@RequestMapping("/queryByAccountId/{accountId}")
	@ResponseBody
	public JsonResult<Account> queryByAccountId(@PathVariable int accountId){
		try {
			Account account = accountService.queryByAccountId(accountId);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("���������˺�ID��ѯ�����˺���Ϣʧ��");
		}
	}

	//�������֤��ѯ�����˺���Ϣ
	@RequestMapping("/queryByIdcard/{idCard}")
	@ResponseBody
	public JsonResult<Account> queryByIdcard(@PathVariable String idCard){
		try {
			if(idCard==null || idCard.trim().isEmpty()){
				return new JsonResult<Account>("���֤���벻��Ϊ��");
			}
			Account account = accountService.queryByIdcard(idCard);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("�������֤�����ѯ�����˺���Ϣʧ��");
		}
	}
	
	//������ʵ������ѯ�����˺���Ϣ
	@RequestMapping("/queryByRealName/{realName}")
	@ResponseBody
	public JsonResult<List<Account>> queryByRealName(@PathVariable String realName){
		try {
			if(realName==null || realName.trim().isEmpty()){
				return new JsonResult<List<Account>>("��ʵ��������Ϊ��");
			}
			List<Account> list = accountService.queryByRealName(realName);
			return new JsonResult<List<Account>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("������ʵ������ѯ�����˺���Ϣʧ��");
		}
	}
	
	//���ݵ�¼����ѯ�����˺���Ϣ
	@RequestMapping("/queryByLoginName/{loginName}")
	@ResponseBody
	public JsonResult<Account> queryByLoginName(@PathVariable String loginName){
		try {
			if(loginName==null || loginName.trim().isEmpty()){
				return new JsonResult<Account>("��¼������Ϊ��");
			}
			Account account = accountService.queryByLoginName(loginName);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("���ݵ�¼����ѯ�����˺���Ϣʧ��");
		}
	}
	
	
	//����״̬��ѯ�����˺���Ϣ
	@RequestMapping("/queryByStatus/{status}")
	@ResponseBody
	public JsonResult<List<Account>> queryByStatus(@PathVariable int status){
		try {
			if(status==0 || status==1){
				List<Account> list = accountService.queryByStatus(status);
				return new JsonResult<List<Account>>(list);
			}else{
				return new JsonResult<List<Account>>("�������˺�״̬��Ϣ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Account>>("����״̬��ѯ�����˺���Ϣʧ��");
		}
	}
	
	//��������˺���Ϣ
	@RequestMapping("/addAccount")
	@ResponseBody
	public JsonResult<Account> addAccount(@RequestBody Account account){
		try {
			if(account == null){
				return new JsonResult<Account>("�����˺���Ϣ����Ϊ��");
			}
			accountService.addAccount(account);
			return new JsonResult<Account>(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("�����˺���Ϣ���ʧ��");
		}
	}

	//�޸������˺���Ϣ
	@RequestMapping("/updateAccount")
	@ResponseBody
	public JsonResult<Account> updateCost(@RequestBody Account account){
		try {
			if(account == null){
				return new JsonResult<Account>("�����˺���Ϣ����Ϊ��");
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
			return new JsonResult<Account>(JsonResult.SUCCESS,"�����˺���Ϣ�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("�����˺���Ϣ�޸�ʧ��");
		}
	}

	//���������˺�ID����״̬
	@RequestMapping("/updateStatus/{accountId}")
	@ResponseBody
	public JsonResult<Account> updateStatus(@PathVariable int accountId){
		try {
			accountService.updateStatus(accountId);
			return new JsonResult<Account>(JsonResult.SUCCESS,"�����˺�״̬�Ѹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("�����˺�״̬�޸�ʧ��");
		}
	}

	//���������˺�IDɾ�������˺���Ϣ
	@RequestMapping("/deleteAccount/{accountId}")
	@ResponseBody
	public JsonResult<Account> deleteAccount(@PathVariable int accountId){
		try {
			accountService.deleteAccount(accountId);
			return new JsonResult<Account>(JsonResult.SUCCESS,"�����˺���Ϣɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Account>("�����˺���Ϣ�޸�ʧ��");
		}
	}
	
	
	
}
