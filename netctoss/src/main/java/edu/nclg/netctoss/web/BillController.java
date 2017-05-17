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
 * �˵�������Ϣ���Ʋ�
 * @author pjz
 */
@Controller
@RequestMapping("/bill")
public class BillController implements Serializable{

	private static final long serialVersionUID = -6274842177322408161L;
	
	@Resource
	private BillService billService;
	
	//��ѯ�����˵�
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Bill>> queryAll(){
		try {
			List<Bill> list = billService.queryAll();
			return new JsonResult<List<Bill>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Bill>>("��ѯ�����˵�ʧ��");
		}
	}
	
	//��������ѯ�˵�
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Bill>> queryBy(@RequestBody Bill bill){
		try {
			
			List<Bill> list = billService.queryAll();
			
			List<Bill> bills = new ArrayList<Bill>();
			String idcard = bill.getIdcard().trim();
			String name = bill.getName().trim();
			String yearMonth = bill.getYearMonth().trim();
			
			//���֤��������ʱ�䣺�����ж�
			if(!idcard.isEmpty() && !name.isEmpty() && !yearMonth.isEmpty()){ //����Ϊ��
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && yearMonth.equals(b.getYearMonth())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty() && !name.isEmpty()){ //���֤��������Ϊ�գ�ʱ��Ϊ��
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty() && !yearMonth.isEmpty()){ //���֤��ʱ�䲻Ϊ�գ�����Ϊ��
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard()) && yearMonth.equals(b.getYearMonth())){
						bills.add(b);
					}
				}
				
			}else if(!name.isEmpty() && !yearMonth.isEmpty()){ //������ʱ�䲻Ϊ�գ����֤Ϊ��
				
				for(Bill b:list){
					if(yearMonth.equals(b.getYearMonth()) && name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!idcard.isEmpty()){  //��ѯ������ֻ�������֤(Ψһ�����˻�)
				
				for(Bill b:list){
					if(idcard.equals(b.getIdcard())){
						bills.add(b);
					}
				}
				
			}else if(!name.isEmpty()){ //��ѯ������ֻ������������ʱ�����ж�������˺ţ�
				
				for(Bill b:list){
					if(name.equals(b.getName())){
						bills.add(b);
					}
				}
				
			}else if(!yearMonth.isEmpty()){ //��ѯ������ֻ��ʱ��(����·�)
				
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
			return new JsonResult<List<Bill>>("��������ѯʧ��");
		}
	}
	
	
	
	


	
}








