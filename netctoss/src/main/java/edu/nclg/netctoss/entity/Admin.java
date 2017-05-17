package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 */
public class Admin implements Serializable{
	
	private static final long serialVersionUID = -6081907040852415823L;
	
	private int adminId;	//管理员ID
	private int roleId;		//角色ID
	private String loginName;	//登录名
	private String password; 
	private String name;	//姓名
	private String telephone;
	private String email;
	private Date enrollDate;	//登记时间
	private String roleName;	//角色名称
	
	public Admin() {
		super();
	}

	public Admin(int adminId, int roleId, String loginName, String password, String name, String telephone,
			String email, Date enrollDate, String roleName) {
		super();
		this.adminId = adminId;
		this.roleId = roleId;
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.enrollDate = enrollDate;
		this.roleName = roleName;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", roleId=" + roleId + ", loginName=" + loginName + ", password="
				+ password + ", name=" + name + ", telephone=" + telephone + ", email=" + email + ", enrollDate="
				+ enrollDate + ", roleName=" + roleName + "]";
	}

	
	
	
}
