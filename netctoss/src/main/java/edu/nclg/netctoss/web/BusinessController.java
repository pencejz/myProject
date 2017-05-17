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
 * ҵ���˺���Ϣ���Ʋ�
 * @author pjz
 * 
 */
@Controller
@RequestMapping("/business")
public class BusinessController implements Serializable{

	private static final long serialVersionUID = 6784918830019597218L;
	
	@Resource
	private BusinessService businessService;
	
	//��ѯ����ҵ���˺���Ϣ
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Business>> queryAll(){
		try {
			List<Business> list = businessService.queryAll();
			return new JsonResult<List<Business>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("��ѯ����ҵ���˺���Ϣʧ��");
		}
	}
	
	//��ѯ����ҵ���˺���Ϣ(osUserName,idCard,status)
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Business>> queryBy(@RequestBody Business business){
		try {
			String idcard = business.getIdCard().trim();
			String osUserName = business.getOsUserName().trim();
			int status = business.getStatus();	//0��ͨ��1��ͣ��2ȫ��
			
			List<Business> list = businessService.queryAll();
			
			List<Business> businesss = new ArrayList<Business>();
			
			if(!idcard.isEmpty() && !osUserName.isEmpty() && status!=2){	//���֤��os�û�����״̬����Ϊ��
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && osUserName.equals(a.getOsUserName()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && !osUserName.isEmpty()){	//���֤��os�û�����Ϊ�գ� ״̬Ϊ��
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && osUserName.equals(a.getOsUserName())){
						businesss.add(a);
					}
				}
				
			}else if(!osUserName.isEmpty() && status!=2){	//os�û�����״̬��Ϊ�գ����֤Ϊ��
				for(Business a:list){
					if(osUserName.equals(a.getOsUserName()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty() && status!=2){	//���֤��״̬��Ϊ�գ�os�û���Ϊ��
				for(Business a:list){
					if(idcard.equals(a.getIdCard()) && status==a.getStatus()){
						businesss.add(a);
					}
				}
				
			}else if(!idcard.isEmpty()){	//���֤��Ϊ�գ�os�û�����״̬Ϊ��
				for(Business a:list){
					if(idcard.equals(a.getIdCard())){
						businesss.add(a);
					}
				}
				
			}else if(!osUserName.isEmpty()){	//os�û�����Ϊ�գ����֤��״̬Ϊ��
				for(Business a:list){
					if(osUserName.equals(a.getOsUserName())){
						businesss.add(a);
					}
				}
				
			}else if(status!=2){	//״̬��Ϊ��,���֤��os�û���Ϊ��
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
			return new JsonResult<List<Business>>("��ѯ����ҵ���˺���Ϣʧ��");
		}
	}

	//����ҵ���˺�ID��ѯҵ���˺���Ϣ
	@RequestMapping("/queryByBusinessId/{businessId}")
	@ResponseBody
	public JsonResult<Business> queryByBusinessId(@PathVariable int businessId){
		try {
			Business business = businessService.queryByBusinessId(businessId);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("����ҵ���˺�ID��ѯҵ���˺���Ϣʧ��");
		}
	}

	//����OS��¼��(����˻���)��ѯҵ���˺���Ϣ
	@RequestMapping("/queryByOsUserName/{osUserName}")
	@ResponseBody
	public JsonResult<Business> queryByOsUserName(@PathVariable String osUserName){
		try {
			if(osUserName==null || osUserName.trim().isEmpty()){
				return new JsonResult<Business>("OS��¼��(�����¼��)����Ϊ��");
			}
			Business business = businessService.queryByOsUserName(osUserName);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("����OS�˻�����ѯ�����˺���Ϣʧ��");
		}
	}

	//�������֤��ѯ�����˺���Ϣ
	@RequestMapping("/queryByIdcard/{idCard}")
	@ResponseBody
	public JsonResult<List<Business>> queryByIdcard(@PathVariable String idCard){
		try {
			if(idCard==null || idCard.trim().isEmpty()){
				return new JsonResult<List<Business>>("���֤���벻��Ϊ��");
			}
			List<Business> list = businessService.queryByIdcard(idCard);
			return new JsonResult<List<Business>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("�������֤�����ѯ�����˺���Ϣʧ��");
		}
	}
	
	//���ݷ�����IP��ַ��ѯҵ���˺���Ϣ
	@RequestMapping("/queryByUnixHost")
	@ResponseBody
	public JsonResult<Business> queryByLoginName(@RequestBody Business business){
		try {
			if(business==null){
				return new JsonResult<Business>("ҵ���˺Ų���Ϊ��");
			}
			if(business.getUnixHost()==null || business.getUnixHost().trim().isEmpty()){
				return new JsonResult<Business>("������IP����Ϊ��");
			}
			Business business1 = businessService.queryByUnixHost(business.getUnixHost());
			return new JsonResult<Business>(business1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("���ݷ�����IP��ѯ�����˺���Ϣʧ��");
		}
	}
	
	
	//����OS�˺�״̬��ѯҵ���˺���Ϣ
	@RequestMapping("/queryByStatus/{status}")
	@ResponseBody
	public JsonResult<List<Business>> queryByStatus(@PathVariable int status){
		try {
			if(status==0 || status==1){
				List<Business> list = businessService.queryByStatus(status);
				return new JsonResult<List<Business>>(list);
			}else{
				return new JsonResult<List<Business>>("��OS�˺�״̬��Ϣ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Business>>("����OS�˺�״̬��ѯ�����˺���Ϣʧ��");
		}
	}
	
	//���ҵ���˺���Ϣ
	@RequestMapping("/addBusiness")
	@ResponseBody
	public JsonResult<Business> addBusiness(@RequestBody Business business){
		try {
			if(business == null){
				return new JsonResult<Business>("ҵ���˺���Ϣ����Ϊ��");
			}
			businessService.addBusiness(business);
			return new JsonResult<Business>(business);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("ҵ���˺���Ϣ���ʧ��");
		}
	}

	//�޸�ҵ���˺��ʷ����ͱ�־flag�������µ�ͳһ�����޸��ʷ�����ʱʹ��
	@RequestMapping("/updateFlag/{businessId}/{flag}")
	@ResponseBody
	public JsonResult<Business> updateFlag(@PathVariable int businessId, @PathVariable int flag){
		try {
			businessService.updateFlag(businessId, flag);
			return new JsonResult<Business>(JsonResult.SUCCESS,"�ʷ������޸ĳɹ����µ�ͳһ�����޸�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("�ʷ������޸�ʧ��");
		}
	}

	//���������˺�ID����OS�˺�״̬
	@RequestMapping("/updateStatus/{businessId}")
	@ResponseBody
	public JsonResult<Business> updateStatus(@PathVariable int businessId){
		try {
			businessService.updateStatus(businessId);
			return new JsonResult<Business>(JsonResult.SUCCESS,"OS�˺�״̬�Ѹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("OS�˺�״̬�޸�ʧ��");
		}
	}

	//���������˺�IDɾ�������˺���Ϣ
	@RequestMapping("/deleteBusiness/{businessId}")
	@ResponseBody
	public JsonResult<Business> deleteBusiness(@PathVariable int businessId){
		try {
			businessService.deleteBusiness(businessId);
			return new JsonResult<Business>(JsonResult.SUCCESS,"�����˺���Ϣɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Business>("�����˺���Ϣ�޸�ʧ��");
		}
	}
	
	
	
	
}
