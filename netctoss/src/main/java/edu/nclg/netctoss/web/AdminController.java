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
 * 管理员信息控制层
 * @author pjz
 */
@Controller
@RequestMapping("/admin")
public class AdminController implements Serializable{

	private static final long serialVersionUID = -8019680744845033673L;
	
	@Resource
	private AdminService adminService;
	
	//查询所有管理员信息
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Admin>> queryAll(){
		try {
			List<Admin> list = adminService.queryAll();
			return new JsonResult<List<Admin>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("查询所有管理员信息失败");
		}
	}
	
	//根据管理员ID查询管理员信息
	@RequestMapping("/queryByAdminId/{adminId}")
	@ResponseBody
	public JsonResult<Admin> queryByAdminId(@PathVariable int adminId){
		try {
			Admin admin = adminService.queryByAdminId(adminId);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("根据管理员ID查询管理员信息失败");
		}
	}
	
	//根据管理员登录名查询管理员信息
	@RequestMapping("/queryByLoginName")
	@ResponseBody
	public JsonResult<Admin> queryByLoginName(HttpServletRequest request){
		try {
			//从session中获取登录名
			String loginName = (String) request.getSession().getAttribute("adminName");
			
			Admin admin = adminService.queryByLoginName(loginName);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("根据管理员ID查询管理员信息失败");
		}
	}

	//根据角色ID查询管理员信息
	@RequestMapping("/queryByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<List<Admin>> queryByRoleId(@PathVariable int roleId){
		try {
			List<Admin> list = adminService.queryByRoleId(roleId);
			return new JsonResult<List<Admin>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("根据角色ID查询管理员信息失败");
		}
	}
	
	//根据姓名查询管理员信息
	@RequestMapping("/queryByName/{name}")
	@ResponseBody
	public JsonResult<Admin> queryByName(@PathVariable String name){
		try {
			if(name == null || name.trim().isEmpty()){
				return new JsonResult<Admin>("姓名不能为空");
			}
			Admin admin = adminService.queryByName(name);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("根据姓名查询管理员信息失败");
		}
	}
	
	//根据条件查询管理员信息（roleId, name）
	@RequestMapping("/queryBy")
	@ResponseBody
	public JsonResult<List<Admin>> queryBy(@RequestBody Admin admin){
		try {
			
			List<Admin> list = adminService.queryAll();
			List<Admin> admins = new ArrayList<Admin>();
			String name = admin.getName().trim();
			int roleId = admin.getRoleId();
			
			if(!name.isEmpty() && roleId!=0){ //角色ID、姓名都不为空
				
				for(Admin a:list){
					if(name.equals(a.getName()) && roleId==a.getRoleId()){
						admins.add(a);
					}
				}
				
			}else if(!name.isEmpty() && roleId==0){ //姓名不为空,角色ID为0(全部)
				
				for(Admin a:list){
					if(name.equals(a.getName())){
						admins.add(a);
					}
				}
				
			}else if(roleId!=0 && name.isEmpty()){ //角色ID不为0，姓名为空
				
				for(Admin a:list){
					if(roleId == a.getRoleId()){
						admins.add(a);
					}
				}
				
			}else{		//角色ID为为0，姓名为空
				admins = list;
			}
			
			return new JsonResult<List<Admin>>(admins);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Admin>>("根据姓名查询管理员信息失败");
		}
	}

	//添加管理员信息
	@RequestMapping("/addAdmin")
	@ResponseBody
	public JsonResult<Admin> addAdmin(@RequestBody Admin admin){
		try {
			if(admin == null){
				return new JsonResult<Admin>("管理员信息不能为空");
			}
			Admin oldAdmin = adminService.queryByLoginName(admin.getLoginName());
			if(oldAdmin != null){
				return new JsonResult<Admin>("该管理员账号已经存在");
			}
			
			adminService.addAdmin(admin);
			return new JsonResult<Admin>(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("管理员信息添加失败");
		}
	}

	//修改管理员信息
	@RequestMapping("/updateAdmin")
	@ResponseBody
	public JsonResult<Admin> updateAdmin(@RequestBody Admin admin){
		try {
			if(admin == null){
				return new JsonResult<Admin>("管理员信息不能为空");
			}
			adminService.updateAdmin(admin);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"管理员信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("管理员信息修改失败");
		}
	}
	
	//根据ID删除管理员信息
	@RequestMapping("/deleteAdmin/{adminId}")
	@ResponseBody
	public JsonResult<Admin> deleteAdmin(@PathVariable int adminId){
		try {
			adminService.deleteAdmin(adminId);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"管理员信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("管理员信息删除失败");
		}
	}

	//修改个人密码
	@RequestMapping("/updatePwd/{oldPwd}/{newPwd}")
	@ResponseBody
	public JsonResult<Admin> updatePwd(@PathVariable String oldPwd,@PathVariable String newPwd, HttpServletRequest request){
		try {
			
			if(oldPwd == null || oldPwd.trim().isEmpty()){
				return new JsonResult<>("旧密码不能为空");
			}
			if(newPwd == null || newPwd.trim().isEmpty()){
				return new JsonResult<>("新密码不能为空");
			}
			if(oldPwd.equals(newPwd)){
				return new JsonResult<>("新密码与旧密码一致，请换一个新密码");
			}
			
			//session获取登录名、密码
			String loginName = (String) request.getSession().getAttribute("adminName");
			
			//判断旧密码
			Admin admin = adminService.queryByLoginName(loginName);
			System.out.println("原始信息---------"+admin);
			if(admin == null){
				return new JsonResult<Admin>("管理员信息不能为空");
			}
			if(admin.getPassword().equals(oldPwd)){
				admin.setPassword(newPwd);
				adminService.updatePwd(admin);
				System.out.println("修改后----------"+admin);
				
			}
			return new JsonResult<Admin>(JsonResult.SUCCESS,"密码修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("密码修改失败");
		}
	}
	
	//修改个人信息(name,telephone,email)
	@RequestMapping("/updateMessage")
	@ResponseBody
	public JsonResult<Admin> updateMessage(@RequestBody Admin subAdmin, HttpServletRequest request){
		try {
			String name = subAdmin.getName();
			String telephone = subAdmin.getTelephone();
			String email = subAdmin.getEmail();
			if(name == null || name.trim().isEmpty()){
				return new JsonResult<>("姓名不能为空");
			}
			
			//session获取登录名
			String loginName = (String) request.getSession().getAttribute("adminName");
			Admin admin = adminService.queryByLoginName(loginName);
			if(name.equals(admin.getName()) && telephone.equals(admin.getTelephone()) && email.equals(admin.getEmail())){
				return new JsonResult<>("您并未对个人信息作出修改");
			}
			admin.setName(name);
			admin.setTelephone(telephone);
			admin.setEmail(email);
			adminService.updateAdmin(admin);
			return new JsonResult<Admin>(JsonResult.SUCCESS,"个人信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Admin>("个人信息修改失败");
		}
	}
	
	
}








