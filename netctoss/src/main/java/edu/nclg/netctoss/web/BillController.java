package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Bill;
import edu.nclg.netctoss.service.BillService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 账单管理信息控制层
 * @author pjz
 */
@Controller
@RequestMapping("/bill")
public class BillController implements Serializable{

	private static final long serialVersionUID = -6274842177322408161L;
	
	@Resource
	private BillService billService;
	
	//查询所有账单
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Bill>> queryAll(){
		try {
			List<Bill> list = billService.queryAll();
			return new JsonResult<List<Bill>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Bill>>("查询所有账单失败");
		}
	}
	
	//按条件查询账单
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Bill>> queryBy(@RequestBody Bill bill){
		try {
			
			List<Bill> list = billService.queryAll();
			
			List<Bill> bills = new ArrayList<Bill>();
			String idcard = bill.getIdcard().trim();
			String name = bill.getName().trim();
			String yearMonth = bill.getYearMonth().trim();
			
			//身份证、姓名、时间：依次判断
			if(!idcard.isEmpty() && !name.isEmpty() && !yearMonth.isEmpty()){ //都不为空
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && yearMonth.equals(b.getYearMonth())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty() && !name.isEmpty()){ //身份证、姓名不为空，时间为空
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty() && !yearMonth.isEmpty()){ //身份证、时间不为空，姓名为空
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && yearMonth.equals(b.getYearMonth())){
						bills.add(b);
					}
				}
				
			}else if(!name.isEmpty() && !yearMonth.isEmpty()){ //姓名、时间不为空，身份证为空
				
				for(Bill b:list){
					if(yearMonth.equals(b.getYearMonth()) && name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty()){  //查询条件中只有有身份证(唯一账务账户)
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard())){
						bills.add(b);
					}
				}
				
			}else if(!name.isEmpty()){ //查询条件中只有姓名（重名时，会有多个账务账号）
				
				for(Bill b:list){
					if(name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!yearMonth.isEmpty()){ //查询条件中只有时间(年份月份)
				
				for(Bill b:list){
					if(yearMonth.equals(b.getYearMonth())){
						bills.add(b);
					}
				}
				
			}else{
				bills = list;
			}
			
			return new JsonResult<List<Bill>>(bills);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Bill>>("按条件查询失败");
		}
	}
	
	
	
	


	
}








