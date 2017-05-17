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

import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.service.BusinessService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 业务账号信息控制层
 * @author pjz
 * 
 */
@Controller
@RequestMapping("/business")
public class BusinessController implements Serializable{

	private static final long serialVersionUID = 6784918830019597218L;
	
	@Resource
	private BusinessService businessService;
	
	//查询所有业务账号信息
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Business>> queryAll(){
		try {
			List<Business> list = businessService.queryAll();
			return new JsonResult<List<Business>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("查询所有业务账号信息失败");
		}
	}
	
	//查询所有业务账号信息(osUserName,idCard,status)
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Business>> queryBy(@RequestBody Business business){
		try {
			String idcard = business.getIdCard().trim();
			String osUserName = business.getOsUserName().trim();
			int status = business.getStatus();	//0开通，1暂停，2全部
			
			List<Business> list = businessService.queryAll();
			
			List<Business> businesss = new ArrayList<Business>();
			
			if(!idcard.isEmpty() && !osUserName.isEmpty() && status!=2){	//身份证、os用户名、状态都不为空
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && osUserName.equals(a.getOsUserName()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && !osUserName.isEmpty()){	//身份证、os用户名不为空， 状态为空
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && osUserName.equals(a.getOsUserName())){
						businesss.add(a);
					}
				}
				
			}else if(!osUserName.isEmpty() && status!=2){	//os用户名、状态不为空，身份证为空
				for(Business a:list){
					if(osUserName.equals(a.getOsUserName()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && status!=2){	//身份证、状态不为空，os用户名为空
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty()){	//身份证不为空，os用户名、状态为空
				for(Business a:list){
					if(idcard.equals(a.getIdCard())){
						businesss.add(a);
					}
				}
				
			}else if(!osUserName.isEmpty()){	//os用户名不为空，身份证、状态为空
				for(Business a:list){
					if(osUserName.equals(a.getOsUserName())){
						businesss.add(a);
					}
				}
				
			}else if(status!=2){	//状态不为空,身份证、os用户名为空
				for(Business a:list){
					if(status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else{
				businesss = list;
			}
			
			return new JsonResult<List<Business>>(businesss);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("查询所有业务账号信息失败");
		}
	}

	//根据业务账号ID查询业务账号信息
	@RequestMapping("/queryByBusinessId/{businessId}")
	@ResponseBody
	public JsonResult<Business> queryByBusinessId(@PathVariable int businessId){
		try {
			Business business = businessService.queryByBusinessId(businessId);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("根据业务账号ID查询业务账号信息失败");
		}
	}

	//根据OS登录名(宽带账户名)查询业务账号信息
	@RequestMapping("/queryByOsUserName/{osUserName}")
	@ResponseBody
	public JsonResult<Business> queryByOsUserName(@PathVariable String osUserName){
		try {
			if(osUserName==null || osUserName.trim().isEmpty()){
				return new JsonResult<Business>("OS登录名(宽带登录名)不能为空");
			}
			Business business = businessService.queryByOsUserName(osUserName);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("根据OS账户名查询账务账号信息失败");
		}
	}

	//根据身份证查询账务账号信息
	@RequestMapping("/queryByIdcard/{idCard}")
	@ResponseBody
	public JsonResult<List<Business>> queryByIdcard(@PathVariable String idCard){
		try {
			if(idCard==null || idCard.trim().isEmpty()){
				return new JsonResult<List<Business>>("身份证号码不能为空");
			}
			List<Business> list = businessService.queryByIdcard(idCard);
			return new JsonResult<List<Business>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("根据身份证号码查询账务账号信息失败");
		}
	}
	
	//根据服务器IP地址查询业务账号信息
	@RequestMapping("/queryByUnixHost")
	@ResponseBody
	public JsonResult<Business> queryByLoginName(@RequestBody Business business){
		try {
			if(business==null){
				return new JsonResult<Business>("业务账号不能为空");
			}
			if(business.getUnixHost()==null || business.getUnixHost().trim().isEmpty()){
				return new JsonResult<Business>("服务器IP不能为空");
			}
			Business business1 = businessService.queryByUnixHost(business.getUnixHost());
			return new JsonResult<Business>(business1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("根据服务器IP查询账务账号信息失败");
		}
	}
	
	
	//根据OS账号状态查询业务账号信息
	@RequestMapping("/queryByStatus/{status}")
	@ResponseBody
	public JsonResult<List<Business>> queryByStatus(@PathVariable int status){
		try {
			if(status==0 || status==1){
				List<Business> list = businessService.queryByStatus(status);
				return new JsonResult<List<Business>>(list);
			}else{
				return new JsonResult<List<Business>>("该OS账号状态信息有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("根据OS账号状态查询账务账号信息失败");
		}
	}
	
	//添加业务账号信息
	@RequestMapping("/addBusiness")
	@ResponseBody
	public JsonResult<Business> addBusiness(@RequestBody Business business){
		try {
			if(business == null){
				return new JsonResult<Business>("业务账号信息不能为空");
			}
			businessService.addBusiness(business);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("业务账号信息添加失败");
		}
	}

	//修改业务账号资费类型标志flag，方便月底统一触发修改资费类型时使用
	@RequestMapping("/updateFlag/{businessId}/{flag}")
	@ResponseBody
	public JsonResult<Business> updateFlag(@PathVariable int businessId, @PathVariable int flag){
		try {
			businessService.updateFlag(businessId, flag);
			return new JsonResult<Business>(JsonResult.SUCCESS,"资费类型修改成功，月底统一触发修改");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("资费类型修改失败");
		}
	}

	//根据账务账号ID更改OS账号状态
	@RequestMapping("/updateStatus/{businessId}")
	@ResponseBody
	public JsonResult<Business> updateStatus(@PathVariable int businessId){
		try {
			businessService.updateStatus(businessId);
			return new JsonResult<Business>(JsonResult.SUCCESS,"OS账号状态已更改");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("OS账号状态修改失败");
		}
	}

	//根据账务账号ID删除账务账号信息
	@RequestMapping("/deleteBusiness/{businessId}")
	@ResponseBody
	public JsonResult<Business> deleteBusiness(@PathVariable int businessId){
		try {
			businessService.deleteBusiness(businessId);
			return new JsonResult<Business>(JsonResult.SUCCESS,"账务账号信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("账务账号信息修改失败");
		}
	}
	
	
	
	
}
