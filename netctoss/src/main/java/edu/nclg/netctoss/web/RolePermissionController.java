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
 * ��ɫ������Ʋ�
 * @author pjz
 *
 */
@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController implements Serializable {

	private static final long serialVersionUID = -6851045144019503682L;
	
	public static int roleId; //���洴����ɫʱ�����صĽ�ɫID
	
	@Resource
	private RolePermissionService rolePermissionService;
	
	//��ѯ���н�ɫȨ��
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<RolePermission>> queryAll(){
		try {
			List<RolePermission> list = rolePermissionService.queryRolePermission(); //��ɫȨ�ޱ�����Ϣ
			
			
			List<RolePermission> roles = new ArrayList<RolePermission>();	//�洢Ϊ����ɫID����ɫ���ƣ���Ӧ��Ȩ�޼��ϣ�
			Set<Integer> roleIds = new HashSet<Integer>();	//��ɫȨ�ޱ������еĽ�ɫID
			
			for(RolePermission r:list){
				roleIds.add(r.getRoleId());
			}
			
			for(Integer i:roleIds){ //���roles
				RolePermission role = new RolePermission();
				String permissionName = "";
				
				for(RolePermission rp:list){ //���role
					if(i == rp.getRoleId()){
						permissionName = permissionName + "��" +rp.getPermissionName();
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
			return new JsonResult<List<RolePermission>>("��ѯ���н�ɫȨ��ʧ��");
		}
	}
	
	//���ݽ�ɫID��ѯ��ɫ��
	@RequestMapping("/queryRoleByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<RolePermission> queryRoleByRoleId(@PathVariable int roleId){
		try {
			RolePermission role = rolePermissionService.queryRoleByRoleId(roleId);
			return new JsonResult<RolePermission>(role);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<RolePermission>("��ѯ��ɫʧ��");
		}
	}
	
	//���ݽ�ɫID��ѯ��ɫȨ����Ϣ
	@RequestMapping("/queryRolePermissionByRoleId/{roleId}")
	@ResponseBody
	public JsonResult<List<RolePermission>> queryRolePermissionByRoleId(@PathVariable int roleId){
		try {
			List<RolePermission> list = rolePermissionService.queryRolePermissionByRoleId(roleId);
			return new JsonResult<List<RolePermission>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<RolePermission>>("��ѯ���н�ɫȨ��ʧ��");
		}
	}
	
	//���ӽ�ɫ
	@RequestMapping("/addRole")
	@ResponseBody
	public JsonResult<Object> addRole(@RequestBody RolePermission rolePermission){ //��ɫ���ƣ�Ȩ��ID
		try {
			if(rolePermission.getRoleName().trim().isEmpty()){
				return new JsonResult<Object>("��ɫ������Ϊ��");
			}
			//�жϸý�ɫ�Ƿ����
			RolePermission role = rolePermissionService.queryRoleByRoleName(rolePermission.getRoleName());
			if(role != null){
				return new JsonResult<Object>("�ý�ɫ�Ѿ�����");
			}
			//�ڽ�ɫ���д�����ɫ
			rolePermissionService.addRole(rolePermission);	//���ؽ�ɫID��rolePermission��
			roleId = rolePermission.getRoleId();
			System.out.println("controller��---roleId===="+rolePermission.getRoleId());
			return new JsonResult<Object>(JsonResult.SUCCESS,"��ɫ�����ɹ�");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("��ɫ����ʧ��");
		}
	}
	
	//����Ȩ��
	@RequestMapping("/addPermission")
	@ResponseBody
	public JsonResult<Object> addRolePermission(@RequestBody RolePermission rolePermission){ //��ɫ���ƣ�Ȩ��ID
		try {
			if(rolePermission.getRoleName().trim().isEmpty()){
				return new JsonResult<Object>("��ɫ������Ϊ��");
			}
			rolePermissionService.addRolePermission(roleId, rolePermission.getPermissionId());
			return new JsonResult<Object>(JsonResult.SUCCESS,"Ȩ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("Ȩ�����ʧ��");
		}
	}
	
	//ɾ����ɫȨ��
	@RequestMapping("/deleteRolePermission/{roleId}")
	@ResponseBody
	public JsonResult<Object> deleteRolePermission(@PathVariable int roleId){
		try {
			rolePermissionService.deleteRole(roleId);
			rolePermissionService.deleteRolePermission(roleId);
			return new JsonResult<Object>(JsonResult.SUCCESS,"��ɫɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("��ɫɾ��ʧ��");
		}
	}
	
	/*
	 * ɾ��Ȩ��
	 * ��role_Permission����в���
	 */
	@RequestMapping("/deletePermission/{roleId}")
	@ResponseBody
	public JsonResult<Object> deletePermission(@PathVariable int roleId){
		try {
			rolePermissionService.deleteRolePermission(roleId);
			return new JsonResult<Object>(JsonResult.SUCCESS,"Ȩ��ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("Ȩ��ɾ��ʧ��");
		}
	}
	
	/*
	 * �޸Ľ�ɫȨ��
	 * ��role_Permission����в���
	 * @parm rolePermission(roleId,roleName,permissionId)
	 */
	@RequestMapping("/updateRolePermission")
	@ResponseBody
	public JsonResult<Object> updateRolePermission(@RequestBody RolePermission rolePermission){
		try {
			
			if(rolePermission == null){
				return new JsonResult<Object>("��ɫȨ����Ϣ����Ϊ��");
			}
			
			//�Ƿ���Ҫ�޸Ľ�ɫ����
			RolePermission role = rolePermissionService.queryRoleByRoleId(rolePermission.getRoleId());
			if(!role.getRoleName().equals(rolePermission.getRoleName())){
				//�޸Ľ�ɫ����
				rolePermissionService.updateRole(rolePermission);
			}
			//���´�����ǰ��ɫȨ��(js���Ѿ����ù�ɾ������)
			rolePermissionService.addRolePermission(rolePermission.getRoleId(), rolePermission.getPermissionId());
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"��ɫȨ���޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("��ɫȨ���޸�ʧ��");
		}
	}
	
	
}
