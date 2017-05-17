package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.service.BillItemService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 账单细节信息控制层
 * @author pjz
 */
@Controller
@RequestMapping("/billItem")
public class BillItemController implements Serializable{
	
	private static final long serialVersionUID = -3560810228385858606L;
	
	@Resource
	private BillItemService billItemService;
	
	//查询所有账单细节
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<BillItem>> queryAll(){
		try {
			List<BillItem> list = billItemService.queryAll();
			return new JsonResult<List<BillItem>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillItem>>("查询所有账单失败");
		}
	}
	
	//根据账务账号查询指定月份所有业务账号的账单详单信息(账单管理页面点击“明细”时，调用本方法)
	//查询所有账单细节
		@RequestMapping("/queryByAccountName/{accountName}/{year}/{month}")
		@ResponseBody
		public JsonResult<List<BillItem>> queryByAccountName(@PathVariable String accountName, @PathVariable int year, @PathVariable int month){
			try {
				String yearMonth = year + "年" + month + "月";
				
				System.out.println("根据账务账号查询指定月份的账单:loginName="+accountName+"yearMonth="+yearMonth);
				if(accountName==null || accountName.trim().isEmpty()){
					return new JsonResult<List<BillItem>>("账务账号不能为空");
				}
				if(yearMonth==null || yearMonth.trim().isEmpty()){
					return new JsonResult<List<BillItem>>("年份月份不能为空");
				}
				List<BillItem> list = billItemService.queryByAccountName(accountName, year, month);
				System.out.println(list);
				return new JsonResult<List<BillItem>>(list);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult<List<BillItem>>("查询所有账单失败");
			}
		}
	
	
	
	
}








