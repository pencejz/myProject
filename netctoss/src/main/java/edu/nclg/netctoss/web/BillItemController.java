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
 * �˵�ϸ����Ϣ���Ʋ�
 * @author pjz
 */
@Controller
@RequestMapping("/billItem")
public class BillItemController implements Serializable{
	
	private static final long serialVersionUID = -3560810228385858606L;
	
	@Resource
	private BillItemService billItemService;
	
	//��ѯ�����˵�ϸ��
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<BillItem>> queryAll(){
		try {
			List<BillItem> list = billItemService.queryAll();
			return new JsonResult<List<BillItem>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<BillItem>>("��ѯ�����˵�ʧ��");
		}
	}
	
	//���������˺Ų�ѯָ���·�����ҵ���˺ŵ��˵��굥��Ϣ(�˵�����ҳ��������ϸ��ʱ�����ñ�����)
	//��ѯ�����˵�ϸ��
		@RequestMapping("/queryByAccountName/{accountName}/{year}/{month}")
		@ResponseBody
		public JsonResult<List<BillItem>> queryByAccountName(@PathVariable String accountName, @PathVariable int year, @PathVariable int month){
			try {
				String yearMonth = year + "��" + month + "��";
				
				System.out.println("���������˺Ų�ѯָ���·ݵ��˵�:loginName="+accountName+"yearMonth="+yearMonth);
				if(accountName==null || accountName.trim().isEmpty()){
					return new JsonResult<List<BillItem>>("�����˺Ų���Ϊ��");
				}
				if(yearMonth==null || yearMonth.trim().isEmpty()){
					return new JsonResult<List<BillItem>>("����·ݲ���Ϊ��");
				}
				List<BillItem> list = billItemService.queryByAccountName(accountName, year, month);
				System.out.println(list);
				return new JsonResult<List<BillItem>>(list);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult<List<BillItem>>("��ѯ�����˵�ʧ��");
			}
		}
	
	
	
	
}








