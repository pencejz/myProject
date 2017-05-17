package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Admin;
import edu.nclg.netctoss.service.AdminService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * ����Ա��Ϣ���Ʋ�
 * @author pjz
 */
@Controller
@RequestMapping("/admin")
public class AdminController implements Serializable{

	private static final long serialVersionUID = -8019680744845033673L;
	
	@Resource
	private AdminService adminService;
	
	//��ѯ���й���Ա��Ϣ
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Admin>> queryAll(){
		try {
			List<Admin> list = adminService.queryAll();
			return new JsonResult<List<Admin>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("��ѯ���й���Ա��Ϣʧ��");
		}
	}
	
	//���ݹ���ԱID��ѯ����Ա��Ϣ
	@RequestMapping("/queryByAdminId/{adminId}")
	@ResponseBody
	public JsonResult<Admin> queryByAdminId(@PathVariable int adminId){
		try {
			Admin admin = adminService.queryByAdminId(adminId);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("���ݹ���ԱID��ѯ����Ա��Ϣʧ��");
		}
	}
	
	//���ݹ���Ա��¼����ѯ����Ա��Ϣ
	@RequestMapping("/queryByLoginName")
	@ResponseBody
	public JsonResult<Admin> queryByLoginName(HttpServletRequest request){
		try {
			//��session�л�ȡ��¼��
			String loginName = (String) request.getSession().getAttribute("adminName");
			
			Admin admin = adminService.queryByLoginName(loginName);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("���ݹ���ԱID��ѯ����Ա��Ϣʧ��");
		}
	}

	//���ݽ�ɫID��ѯ����Ա��Ϣ
	@RequestMapping("/queryByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<List<Admin>> queryByRoleId(@PathVariable int roleId){
		try {
			List<Admin> list = adminService.queryByRoleId(roleId);
			return new JsonResult<List<Admin>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("���ݽ�ɫID��ѯ����Ա��Ϣʧ��");
		}
	}
	
	//����������ѯ����Ա��Ϣ
	@RequestMapping("/queryByName/{name}")
	@ResponseBody
	public JsonResult<Admin> queryByName(@PathVariable String name){
		try {
			if(name == null || name.trim().isEmpty()){
				return new JsonResult<Admin>("��������Ϊ��");
			}
			Admin admin = adminService.queryByName(name);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("����������ѯ����Ա��Ϣʧ��");
		}
	}
	
	//����������ѯ����Ա��Ϣ��roleId, name��
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Admin>> queryBy(@RequestBody Admin admin){
		try {
			
			List<Admin> list = adminService.queryAll();
			List<Admin> admins = new ArrayList<Admin>();
			String name = admin.getName().trim();
			int roleId = admin.getRoleId();
			
			if(!name.isEmpty() && roleId!=0){ //��ɫID����������Ϊ��
				
				for(Admin a:list){
					if(name.equals(a.getName()) && roleId==a.getRoleId()){
						admins.add(a);
					}
				}
				
			}else if(!name.isEmpty() && roleId==0){ //������Ϊ��,��ɫIDΪ0(ȫ��)
				
				for(Admin a:list){
					if(name.equals(a.getName())){
						admins.add(a);
					}
				}
				
			}else if(roleId!=0 && name.isEmpty()){ //��ɫID��Ϊ0������Ϊ��
				
				for(Admin a:list){
					if(roleId == a.getRoleId()){
						admins.add(a);
					}
				}
				
			}else{		//��ɫIDΪΪ0������Ϊ��
				admins = list;
			}
			
			return new JsonResult<List<Admin>>(admins);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("����������ѯ����Ա��Ϣʧ��");
		}
	}

	//��ӹ���Ա��Ϣ
	@RequestMapping("/addAdmin")
	@ResponseBody
	public JsonResult<Admin> addAdmin(@RequestBody Admin admin){
		try {
			if(admin == null){
				return new JsonResult<Admin>("����Ա��Ϣ����Ϊ��");
			}
			Admin oldAdmin = adminService.queryByLoginName(admin.getLoginName());
			if(oldAdmin != null){
				return new JsonResult<Admin>("�ù���Ա�˺��Ѿ�����");
			}
			
			adminService.addAdmin(admin);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("����Ա��Ϣ���ʧ��");
		}
	}

	//�޸Ĺ���Ա��Ϣ
	@RequestMapping("/updateAdmin")
	@ResponseBody
	public JsonResult<Admin> updateAdmin(@RequestBody Admin admin){
		try {
			if(admin == null){
				return new JsonResult<Admin>("����Ա��Ϣ����Ϊ��");
			}
			adminService.updateAdmin(admin);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"����Ա��Ϣ�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("����Ա��Ϣ�޸�ʧ��");
		}
	}
	
	//����IDɾ������Ա��Ϣ
	@RequestMapping("/deleteAdmin/{adminId}")
	@ResponseBody
	public JsonResult<Admin> deleteAdmin(@PathVariable int adminId){
		try {
			adminService.deleteAdmin(adminId);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"����Ա��Ϣɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("����Ա��Ϣɾ��ʧ��");
		}
	}

	//�޸ĸ�������
	@RequestMapping("/updatePwd/{oldPwd}/{newPwd}")
	@ResponseBody
	public JsonResult<Admin> updatePwd(@PathVariable String oldPwd,@PathVariable String newPwd, HttpServletRequest request){
		try {
			
			if(oldPwd == null || oldPwd.trim().isEmpty()){
				return new JsonResult<>("�����벻��Ϊ��");
			}
			if(newPwd == null || newPwd.trim().isEmpty()){
				return new JsonResult<>("�����벻��Ϊ��");
			}
			if(oldPwd.equals(newPwd)){
				return new JsonResult<>("�������������һ�£��뻻һ��������");
			}
			
			//session��ȡ��¼��������
			String loginName = (String) request.getSession().getAttribute("adminName");
			
			//�жϾ�����
			Admin admin = adminService.queryByLoginName(loginName);
			System.out.println("ԭʼ��Ϣ---------"+admin);
			if(admin == null){
				return new JsonResult<Admin>("����Ա��Ϣ����Ϊ��");
			}
			if(admin.getPassword().equals(oldPwd)){
				admin.setPassword(newPwd);
				adminService.updatePwd(admin);
				System.out.println("�޸ĺ�----------"+admin);
				
			}
			return new JsonResult<Admin>(JsonResult.SUCCESS,"�����޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("�����޸�ʧ��");
		}
	}
	
	//�޸ĸ�����Ϣ(name,telephone,email)
	@RequestMapping("/updateMessage")
	@ResponseBody
	public JsonResult<Admin> updateMessage(@RequestBody Admin subAdmin, HttpServletRequest request){
		try {
			String name = subAdmin.getName();
			String telephone = subAdmin.getTelephone();
			String email = subAdmin.getEmail();
			if(name == null || name.trim().isEmpty()){
				return new JsonResult<>("��������Ϊ��");
			}
			
			//session��ȡ��¼��
			String loginName = (String) request.getSession().getAttribute("adminName");
			Admin admin = adminService.queryByLoginName(loginName);
			if(name.equals(admin.getName()) && telephone.equals(admin.getTelephone()) && email.equals(admin.getEmail())){
				return new JsonResult<>("����δ�Ը�����Ϣ�����޸�");
			}
			admin.setName(name);
			admin.setTelephone(telephone);
			admin.setEmail(email);
			adminService.updateAdmin(admin);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"������Ϣ�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("������Ϣ�޸�ʧ��");
		}
	}
	
	
}








