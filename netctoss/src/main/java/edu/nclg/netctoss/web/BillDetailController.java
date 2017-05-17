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
 * �˵��굥��Ϣ���Ʋ�
 * @author pjz
 */
@Controller
@RequestMapping("/billDetail")
public class BillDetailController implements Serializable{

	private static final long serialVersionUID = -2576041048569778081L;
	
	@Resource
	private BillDetailService billDetailService;
	
	//��ѯ�����˵��굥��Ϣ
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<BillDetail>> queryAll(){
		try {
			List<BillDetail> list = billDetailService.queryAll();
			return new JsonResult<List<BillDetail>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillDetail>>("��ѯ�����˵��굥ʧ��");
		}
	}

	//�����˵���ϸID����˵���ϸ�еķ�����IP����ʱ�䣨�õ����£�
	//���ݷ�����IP��ѯָ������·ݵ��˵��굥��Ϣ���˵�ϸ��ҳ�棬������굥��ʱ���ã�
	@RequestMapping("/queryByUnixHost/{billItemId}")
	@ResponseBody
	public JsonResult<List<BillDetail>> queryBybillItemId(@PathVariable int billItemId){
		try {
			List<BillDetail> list = billDetailService.queryByUnixHost(billItemId);
			return new JsonResult<List<BillDetail>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillDetail>>("���ݷ�����IP��ѯ�˵��굥ʧ��");
		}
	}

	//����˵��굥��Ϣ
	@RequestMapping("/addBillDetail")
	@ResponseBody
	public JsonResult<BillDetail> addBillDetail(@RequestBody BillDetail billDetail){
		try {
			if(billDetail == null){
				return new JsonResult<BillDetail>("�˵��굥��Ϣ����Ϊ��");
			}
			billDetailService.addBillDetail(billDetail);
			return new JsonResult<BillDetail>(billDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<BillDetail>("�˵��굥��Ϣ���ʧ��");
		}
	}

	
}








