package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.service.AccountService;
import edu.nclg.netctoss.service.BillDetailService;
import edu.nclg.netctoss.service.BusinessService;
import edu.nclg.netctoss.service.CostService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 模拟业务账号用户登录（宽带拨号）
 * 
 * 登入、登出成功之后，保存本次登录信息至账单详单表中
 * @author pjz
 */
@Controller
@RequestMapping("/testLogin")
public class TestLoginController implements Serializable{

	private static final long serialVersionUID = -3214976597125442422L;
	
	public static Date inTime;			//登入时间
	public static Date outTime;			//登出时间
	public static Account account;		//账务账号实体
	public static Business business;	//业务账号实体
	public static Cost cost;			//资费类型实体
	
	@Resource
	private BusinessService businessService;
	@Resource
	private AccountService accountService;
	@Resource
	private BillDetailService billDetailService;
	@Resource
	private CostService costService;
	
	//判断用户登录信息是否正确
	@RequestMapping("/login/{userName}/{userPwd}")
	@ResponseBody
	public JsonResult<Object> testLogin(@PathVariable String userName, @PathVariable String userPwd,HttpServletRequest request){
		try {
			if(userName==null || userName.trim().isEmpty()){
				return new JsonResult<Object>("用户登录名不能为空");
			}
			if(userPwd==null || userPwd.trim().isEmpty()){
				return new JsonResult<Object>("登录密码不能为空");
			}
			
			Business business = businessService.queryByOsUserName(userName); //登录名唯一，list中仅有一个元素
			if(business.getStatus()==1){
				return new JsonResult<Object>("该业务账号已暂停使用");
			}
			if(!business.getLoginPassword().equals(userPwd)){
				return new JsonResult<Object>("用户名或密码不正确，请重新输入");
			}
			//成功登录之后，将登录信息保存至session中
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userPwd", userPwd);
//			System.out.println(session.getAttribute("userName"));
			
			inTime = new Date(); //登入时间
			return new JsonResult<Object>(JsonResult.SUCCESS,"登录成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("用户名或密码不正确，请重新输入");
		}
	}
	
	//登出成功之后保存账单信息
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult<Object> saveBill(HttpServletRequest request){
		try {
			
			outTime = new Date(); //记录登出时间
//			HttpSession session = request.getSession();
			String userName = (String) request.getSession().getAttribute("userName"); //从session中获取用户登录信息
			System.out.println("登出用户："+userName);
			business = businessService.queryByOsUserName(userName); //根据业务账号（userName）获得业务账号相关信息
			account = accountService.queryByAccountId(business.getAccountId()); //根据账务账号ID获得账务账务账号信息
			cost = costService.queryByCostId(business.getCostId()); //根据业务账号ID获得资费类型信息
			
			//保存账单详单信息
			saveBillDetail();
			
			//在账务账号中保存最近一次登录时间
			account.setLastLoginTime(inTime);
			accountService.updateAccount(account);
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"本次登录信息保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("本次登录信息保存失败");
		}
	}
	
	//保存账单详单信息
	public void saveBillDetail() throws Exception{
		
		BillDetail billDetail = new BillDetail();
		int duration = (int)(outTime.getTime()-inTime.getTime())/1000; //本次使用时长（秒）
		
		billDetail.setDuration(duration);
		billDetail.setUnixHost(business.getUnixHost());
		billDetail.setCostId(cost.getCostId());
		billDetail.setInTime(inTime);
		billDetail.setOutTime(outTime);
		billDetail.setCharge(getCharge()); //getCharge():获得本次使用费用
		billDetail.setCostName(cost.getName());
		
		billDetailService.addBillDetail(billDetail);
	}
	
	/*
	 * 获取本次使用费用
	 * 
	 * 查询当年当月已使用的账单信息，得到本月已经使用的时长总和，根据不同的套餐类型(包年0,包月1,套餐2,计时收费3)做出不同的处理。
	 * 判断是否超出范围，据此得出本次费用
	 */
	public Double getCharge() throws Exception{
		
		Double charge=0.0;
		
		//根据套餐类型分别计算费用。注意：包年、包月、套餐内使用时，单次登入登出不收费
		if(0 == cost.getCostType()){ //包年
			charge = 0.0;
		}else if(1 == cost.getCostType()){ //包月
			charge = 0.0;
		}else if(2 == cost.getCostType()){ //套餐
			
			//根据当月详单获得已使用时长，套餐内不收费，超出收费
			List<BillDetail> billDetails = getBillDetails(); //当前业务账号当年当月详单
			int usedSecond = 0; //当年当月已使用时长（秒）
			double usedHour = 0.0; //当月已使用时长（小时）
			for(BillDetail b:billDetails){
				usedSecond = usedSecond + b.getDuration();
			}
			usedHour = usedSecond/60.0/60;
			//判断是否超出套餐范围
			if((double)cost.getBaseDuration() >= usedHour){
				charge = 0.0;
			}else{
				charge = cost.getBaseCost()*usedHour;
			}
			
		}else if(3 == cost.getCostType()){ //计时收费
			//获取时长
			double hour = (outTime.getTime()-inTime.getTime())/1000.0/60/60;
			charge = cost.getUnitCost() * hour;
		}
		
		return charge;
	}
	
	//获得当前业务账号当年当月详单
	public List<BillDetail> getBillDetails() throws Exception{
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //获得当前年份
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1; //获取当前月份
		
		List<BillDetail> list = billDetailService.queryByUnixHost(business.getUnixHost()); //根据IP查询账单详单
		
		List<BillDetail> billDetails = new ArrayList<BillDetail>(); //当年当月账单信息
		Calendar caledar = Calendar.getInstance();
		for(BillDetail b:list){
			
			caledar.setTime(b.getOutTime()); //以登出时间为准
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && currentMonth==month){ //当前年份、月份保存至billDetails
				billDetails.add(b);
			}
			
		}
		
		return billDetails;
	}
	
	
	
	
}

