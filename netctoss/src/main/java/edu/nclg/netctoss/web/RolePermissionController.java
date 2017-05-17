package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.RolePermission;
import edu.nclg.netctoss.service.RolePermissionService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 角色管理控制层
 * @author pjz
 *
 */
@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController implements Serializable {

	private static final long serialVersionUID = -6851045144019503682L;
	
	public static int roleId; //保存创建角色时，返回的角色ID
	
	@Resource
	private RolePermissionService rolePermissionService;
	
	//查询所有角色权限
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<RolePermission>> queryAll(){
		try {
			List<RolePermission> list = rolePermissionService.queryRolePermission(); //角色权限表中信息
			
			
			List<RolePermission> roles = new ArrayList<RolePermission>();	//存储为（角色ID，角色名称，对应的权限集合）
			Set<Integer> roleIds = new HashSet<Integer>();	//角色权限表中所有的角色ID
			
			for(RolePermission r:list){
				roleIds.add(r.getRoleId());
			}
			
			for(Integer i:roleIds){ //获得roles
				RolePermission role = new RolePermission();
				String permissionName = "";
				
				for(RolePermission rp:list){ //获得role
					if(i == rp.getRoleId()){
						permissionName = permissionName + "、" +rp.getPermissionName();
						role.setRoleId(i);
						role.setRoleName(rp.getRoleName());
					}
				role.setPermissionName(permissionName);
				}
			roles.add(role);	
			}
			
			
			return new JsonResult<List<RolePermission>>(roles);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<RolePermission>>("查询所有角色权限失败");
		}
	}
	
	//根据角色ID查询角色名
	@RequestMapping("/queryRoleByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<RolePermission> queryRoleByRoleId(@PathVariable int roleId){
		try {
			RolePermission role = rolePermissionService.queryRoleByRoleId(roleId);
			return new JsonResult<RolePermission>(role);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<RolePermission>("查询角色失败");
		}
	}
	
	//根据角色ID查询角色权限信息
	@RequestMapping("/queryRolePermissionByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<List<RolePermission>> queryRolePermissionByRoleId(@PathVariable int roleId){
		try {
			List<RolePermission> list = rolePermissionService.queryRolePermissionByRoleId(roleId);
			return new JsonResult<List<RolePermission>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<RolePermission>>("查询所有角色权限失败");
		}
	}
	
	//增加角色
	@RequestMapping("/addRole")
	@ResponseBody
	public JsonResult<Object> addRole(@RequestBody RolePermission rolePermission){ //角色名称，权限ID
		try {
			if(rolePermission.getRoleName().trim().isEmpty()){
				return new JsonResult<Object>("角色名不能为空");
			}
			//判断该角色是否存在
			RolePermission role = rolePermissionService.queryRoleByRoleName(rolePermission.getRoleName());
			if(role != null){
				return new JsonResult<Object>("该角色已经存在");
			}
			//在角色表中创建角色
			rolePermissionService.addRole(rolePermission);	//返回角色ID到rolePermission中
			roleId = rolePermission.getRoleId();
			System.out.println("controller中---roleId===="+rolePermission.getRoleId());
			return new JsonResult<Object>(JsonResult.SUCCESS,"角色创建成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("角色创建失败");
		}
	}
	
	//增加权限
	@RequestMapping("/addPermission")
	@ResponseBody
	public JsonResult<Object> addRolePermission(@RequestBody RolePermission rolePermission){ //角色名称，权限ID
		try {
			if(rolePermission.getRoleName().trim().isEmpty()){
				return new JsonResult<Object>("角色名不能为空");
			}
			rolePermissionService.addRolePermission(roleId, rolePermission.getPermissionId());
			return new JsonResult<Object>(JsonResult.SUCCESS,"权限添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("权限添加失败");
		}
	}
	
	//删除角色权限
	@RequestMapping("/deleteRolePermission/{roleId}")
	@ResponseBody
	public JsonResult<Object> deleteRolePermission(@PathVariable int roleId){
		try {
			rolePermissionService.deleteRole(roleId);
			rolePermissionService.deleteRolePermission(roleId);
			return new JsonResult<Object>(JsonResult.SUCCESS,"角色删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("角色删除失败");
		}
	}
	
	/*
	 * 删除权限
	 * 对role_Permission表进行操作
	 */
	@RequestMapping("/deletePermission/{roleId}")
	@ResponseBody
	public JsonResult<Object> deletePermission(@PathVariable int roleId){
		try {
			rolePermissionService.deleteRolePermission(roleId);
			return new JsonResult<Object>(JsonResult.SUCCESS,"权限删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("权限删除失败");
		}
	}
	
	/*
	 * 修改角色权限
	 * 对role_Permission表进行操作
	 * @parm rolePermission(roleId,roleName,permissionId)
	 */
	@RequestMapping("/updateRolePermission")
	@ResponseBody
	public JsonResult<Object> updateRolePermission(@RequestBody RolePermission rolePermission){
		try {
			
			if(rolePermission == null){
				return new JsonResult<Object>("角色权限信息不能为空");
			}
			
			//是否需要修改角色名称
			RolePermission role = rolePermissionService.queryRoleByRoleId(rolePermission.getRoleId());
			if(!role.getRoleName().equals(rolePermission.getRoleName())){
				//修改角色名称
				rolePermissionService.updateRole(rolePermission);
			}
			//重新创建当前角色权限(js中已经调用过删除方法)
			rolePermissionService.addRolePermission(rolePermission.getRoleId(), rolePermission.getPermissionId());
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"角色权限修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("角色权限修改失败");
		}
	}
	
	
}
