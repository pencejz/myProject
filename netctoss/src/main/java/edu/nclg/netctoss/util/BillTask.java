package edu.nclg.netctoss.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.nclg.netctoss.entity.Account;
import edu.nclg.netctoss.entity.Bill;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.entity.Business;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.service.AccountService;
import edu.nclg.netctoss.service.BillDetailService;
import edu.nclg.netctoss.service.BillItemService;
import edu.nclg.netctoss.service.BillService;
import edu.nclg.netctoss.service.BusinessService;
import edu.nclg.netctoss.service.CostService;

/**
 * 账单自动保存工具类
 * @author pjz
 */
@Component
public class BillTask implements Serializable {

	private static final long serialVersionUID = -5635898206834890825L;
	
	@Resource
	private BusinessService businessService;
	@Resource
	private AccountService accountService;
	@Resource
	private BillDetailService billDetailService;
	@Resource
	private BillItemService billItemService;
	@Resource
	private BillService billService;
	@Resource
	private CostService costService;
	
	/**
	 * 自动保存账单细节信息（时间为每月1日，自动保存）
	 * @throws Exception 
	 */
//	@Scheduled(cron = "*/1 * * * * ?")  //每秒执行一次 
//	@Scheduled(cron = "0 0 0 1 * ?" )  //每月1日0时执行一次 
	@Scheduled(cron = "*/10 * * * * ?") //每10秒执行一次
	public void saveBillItem() throws Exception{
		System.out.println("账单细节保存任务进行中。。。");
		List<BillDetail> billDetails = getBillDetails();	//获得当年上月所有详单信息（时长，费用，资费类型）
		System.out.println("当年上月所有详单------"+billDetails);
		
		Set<String> ips = new HashSet<String>();		//获取账单详单中所有IP（重复只算一次）
		for(BillDetail b:billDetails){
			ips.add(b.getUnixHost());
		}
		System.out.println("账单详单中存在的IP：：："+ips);
		
		for(String ip:ips){	//保存所有账单细节对象(以IP为单位保存)
			Business business = businessService.queryByUnixHost(ip);	//根据IP获得accountID、业务账号
			int accountId = business.getAccountId();
			int costId = business.getCostId();
			String osUsername = business.getOsUserName();
			Cost cost = costService.queryByCostId(costId);	//根据资费ID获得资费信息，并加上每月的基本费用（包时基本费用为0）
			double totalCharge = 0.0;	//一个业务账号一个月的费用总和
			totalCharge = cost.getBaseCost(); //加上基本费用
			if(7 == costId){ //包年：每月费用0元
				totalCharge = 0.0;
			}
			//根据ip分组，获得一个业务账号上月的所有账单详单，并统计总时长，总费用，资费类型ID、最后一次登出时间
			int totalDuration = 0 ;		//一个业务账号一个月的使用时长
			Date date = null;			//最后一次登出时间
			for(BillDetail b:billDetails){
				if(ip.equals(b.getUnixHost())){
					
					totalDuration = totalDuration + b.getDuration();
					totalCharge = totalCharge + b.getCharge();
					costId = b.getCostId();
					date = b.getOutTime();
				}
			}
			String strDuration = getDuration(totalDuration);	//将整型秒数转变成字符串：xx天xx小时xx分钟xx秒
			BillItem billItem = new BillItem();		//获得一个账单细节对象
			billItem.setUnixHost(ip);
			billItem.setAccountId(accountId);
			billItem.setCostId(costId);
			billItem.setOsUsername(osUsername);
			billItem.setDuration(strDuration);
			billItem.setBusinessCharge(totalCharge);
			billItem.setDate(date);
			billItem.setCostName(cost.getName());
			
			//保存一个账单细节对象，如果账单细节中已经存在该对象，就不在保存
			Calendar c = Calendar.getInstance();
			c.setTime(date);	//获得即将保存的账单细节对象的时间（当年上月时间）
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
//			System.out.println(year +"  即将保存的账单细节对象       "+ month);
			
			List<BillItem> list = billItemService.queryByUnixHost(ip);	//根据IP获得账单细节表中所有数据对象
			if(list.size()==0){		//当前IP还未保存过账单细节
				
				billItemService.addBillItem(billItem);
				System.out.println("当前"+ip+"中账单细节为空，存入一个对象");
				
			}else{		//当前IP保存过账单细节，还需要判断的是当年上月是否已经保存过数据
				
				BillItem bit = null;  //用于临时保存当年上月账单细节对象
				for(BillItem b:list){
					
					c.setTime(b.getDate());		//获得账单细节表中的时间和年份
					int oldYear = c.get(Calendar.YEAR);
					int oldMonth = c.get(Calendar.MONTH)+1;
					System.out.println(oldYear +"  账单细节表中所有保存对象       "+ oldMonth);
					
					if(oldYear==year && oldMonth==month){	//与即将保存的账单细节对象的时间作比较，如果存在就将该对象保存至临时对象中
						bit = new BillItem();
						bit = b;
					}
					
				}
				
				if(bit == null){	//如果账单细节表中没有上月的账单细节信息，就添加
					billItemService.addBillItem(billItem);
					System.out.println("账单细节保存成功！！！！");
				}
				
			}
			
			
		}
		
	}
	
	/**
	 * 自动保存账单管理信息（时间为每月1日，自动保存）
	 * @throws Exception 
	 */
//	@Scheduled(cron = "0 0 0 1 * ?" )  //每月执行一次
	@Scheduled(cron = "*/20 * * * * ?") //每10秒执行一次
	public void saveBill() throws Exception{
		System.out.println("账单管理保存中。。。。");
		
		List<BillItem> billItems = getBillItems();		//获得当年上月所有账单细节信息
		System.out.println("当年上月所有账单细节：："+billItems);
		
		Set<Integer> accountIds = new HashSet<Integer>();	//从上月账单细节中获得所有账务账号ID
		for(BillItem b:billItems){
			accountIds.add(b.getAccountId());
		}
		
		//保存所有账单管理对象
		for(int accountId:accountIds){
			
			Account account = accountService.queryByAccountId(accountId);
			
			//根据账务账号ID分组，获得一个账务账号下的所有业务账号上月的账单详细信息，并保存至账单管理表中
			double totalCharge = 0.0;
			Date date = new Date();
			for(BillItem b:billItems){
				if(accountId == b.getAccountId()){
					totalCharge = totalCharge + b.getBusinessCharge();
					date = b.getDate();
				}
			}
			
			Calendar c = Calendar.getInstance();	//获得即将保存的账单管理的年月（当年上月时间）
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			String yearMonth = year + "年" + month + "月";
			
			//获得一个账单管理对象
			Bill bill = new Bill();
			bill.setAccountId(accountId);
			bill.setIdcard(account.getIdCard());
			bill.setMonthCharge(totalCharge);
			bill.setName(account.getRealName());
			bill.setYearMonth(yearMonth);
			bill.setDate(date);
			bill.setAccountName(account.getLoginName());
			System.out.println("即将保存账单管理对象："+bill);
			
			//保存一个账单管理对象,如果账单管理表中已经存在该对象，就不需要保存（通过时间来做判断）
			List<Bill> list = billService.queryByAccountId(accountId);
			
			if(list.size()==0){		//当前账务账号没有保存过账单
				billService.addBill(bill);
				
			}else{		//当前账务账号已经保存过账单，就继续判断是否是当年上月的账单
				
				Bill bil = null;	//用于临时保存当年上月账单管理信息
				for(Bill b:list){
					
					c.setTime(b.getDate());	//获得账单管理表中已存在账单对象的年月
					int oldYear = c.get(Calendar.YEAR);
					int oldMonth = c.get(Calendar.MONTH)+1;
					System.out.println(oldYear +"   账单管理表中已存在的账单对象的年月      "+ oldMonth);
					
					if(oldYear==year && oldMonth==month){	//与表中已经有的时间做对比，如果该年份月份，已经存在，就不需要再次保存
						bil = new Bill();
						bil = b;
					}
				}
				
				if(bil ==null){		//如果表中没有上月的账单管理表，就添加
					billService.addBill(bill);
					System.out.println("账单管理对象保存成功");
				}
				
			}
			
		}
		
	}

	//获得当年上月所有账单详单信息
	public List<BillDetail> getBillDetails() throws Exception{
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //获得当前年份
		int lastMonth = Calendar.getInstance().get(Calendar.MONTH); //获取上月月份
		
		List<BillDetail> list = billDetailService.queryAll(); //查询所有账单详单信息
		
		List<BillDetail> billDetails = new ArrayList<BillDetail>(); //保存当年当上月所有账单详单信息
		Calendar caledar = Calendar.getInstance();
		for(BillDetail b:list){
			
			caledar.setTime(b.getOutTime());    //以登出时间为准，获得账单详单对象的年份月份
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && lastMonth==month){ //当前年份、上月的账单详单保存至billDetails
				billDetails.add(b);
			}
			
		}
		
		return billDetails;
	}
	
	//获得当年上月所有账单细节信息
	public List<BillItem> getBillItems() throws Exception{
		System.out.println("获得当年上月所有账单细节信息。。。。");
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); //获得当前年份
		int lastMonth = Calendar.getInstance().get(Calendar.MONTH); //获取上月月份
		
		List<BillItem> list = billItemService.queryAll(); //查询所有账单详单信息
		
		List<BillItem> billItems = new ArrayList<BillItem>(); //保存当年当上月所有账单细节信息
		Calendar caledar = Calendar.getInstance();
		for(BillItem b:list){
			
			caledar.setTime(b.getDate());	//获得账单细节对象的年份月份
			int year = caledar.get(Calendar.YEAR);
			int month = caledar.get(Calendar.MONTH)+1;
			
			if(currentYear==year && lastMonth==month){ //当前年份、上月的账单详单保存至billDetails
				billItems.add(b);
			}
			
		}
		
		return billItems;
	}
	
	//将整型秒数转变成字符串：xx天xx小时xx分钟xx秒
	public String getDuration(int second){
//		System.out.println("将整型秒数转变成字符串。。。。");
		
		int minite = second/60;
		int hour = minite/60;
		int day = hour/24;
		
		String str = "";
		
		if(second/60 == 0){ //不足1分钟
			str = second + "秒";
		}else if(minite/60 == 0){  //不足1小时
			str = minite + "分钟" + second%60 + "秒";
		}else if(hour/24 == 0){ //不足一天
			str = hour + "小时" + minite%60 + "分钟" + second%60 + "秒";
		}else{ //超过一天
			str = day + "天" + hour%24 + "小时" + minite%60 + "分钟" + second%60 + "秒";
		}
		
		return str;
				
	}
	
	/**
	 * 修改业务账号的资费类型
	 * 
	 * 通过flag来判断：为-1不需要修改。否则该值就是需要修改的资费类型ID
	 * @throws Exception 
	 */
//	@Scheduled(cron = "0 0 0 1 * ?" )  //每月1日0时执行一次 
	@Scheduled(cron = "*/10 * * * * ?") //每10秒执行一次
	public void updateCostId() throws Exception{
//		System.out.println("修改资费类型操作执行中。。。。。");
		List<Business> list = businessService.queryAll();
		for(Business b:list){
			if(b.getFlag() != -1){
				Business business = b;
				business.setCostId(b.getFlag());
				businessService.updateCostId(business);
				//将flag重置为-1
				businessService.updateFlag(business.getBusinessId(), -1);
			}
			
		}
		
	}
	
}









