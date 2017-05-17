package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.service.BillDetailService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 账单详单信息控制层
 * @author pjz
 */
@Controller
@RequestMapping("/billDetail")
public class BillDetailController implements Serializable{

	private static final long serialVersionUID = -2576041048569778081L;
	
	@Resource
	private BillDetailService billDetailService;
	
	//查询所有账单详单信息
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<BillDetail>> queryAll(){
		try {
			List<BillDetail> list = billDetailService.queryAll();
			return new JsonResult<List<BillDetail>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillDetail>>("查询所有账单详单失败");
		}
	}

	//根据账单明细ID获得账单明细中的服务器IP，和时间（得到年月）
	//根据服务器IP查询指定年份月份的账单详单信息（账单细节页面，点击“详单”时调用）
	@RequestMapping("/queryByUnixHost/{billItemId}")
	@ResponseBody
	public JsonResult<List<BillDetail>> queryBybillItemId(@PathVariable int billItemId){
		try {
			List<BillDetail> list = billDetailService.queryByUnixHost(billItemId);
			return new JsonResult<List<BillDetail>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillDetail>>("根据服务器IP查询账单详单失败");
		}
	}

	//添加账单详单信息
	@RequestMapping("/addBillDetail")
	@ResponseBody
	public JsonResult<BillDetail> addBillDetail(@RequestBody BillDetail billDetail){
		try {
			if(billDetail == null){
				return new JsonResult<BillDetail>("账单详单信息不能为空");
			}
			billDetailService.addBillDetail(billDetail);
			return new JsonResult<BillDetail>(billDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<BillDetail>("账单详单信息添加失败");
		}
	}

	
}








