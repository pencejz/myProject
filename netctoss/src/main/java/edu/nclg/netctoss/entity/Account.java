package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 账务账号实体类
 * @author pjz
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -5642655265939474795L;
	
	private int accountId;			//账务账号ID
	private String loginName;		//登录名
	private String loginPassword;	//登录密码
	private int status;				//状态(0-开通,1-暂停)
	private Date createDate;		//创建日期
	private Date pauseDate;			//暂停日期
	private String realName;		//真实姓名
	private String idCard;			//身份证
	private int gender;				//性别(0-男，1-女)
	private String occupation;		//职业
	private String telephone;		//电话号码
	private String email;			//邮箱
	private String mailaddress;		//通信地址
	private String zipCode;			//邮编
	private String qq;
	private Date lastLoginTime;		//最近登录时间
	
	public Account() {
		super();
	}
	public Account(int accountId, String loginName, String loginPassword, int status,
			Date createDate, Date pauseDate, String realName, String idCard, int gender,
			String occupation, String telephone, String email, String mailaddress, String zipCode, String qq,
			Date lastLoginTime) {
		super();
		this.accountId = accountId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.status = status;
		this.createDate = createDate;
		this.pauseDate = pauseDate;
		this.realName = realName;
		this.idCard = idCard;
		this.gender = gender;
		this.occupation = occupation;
		this.telephone = telephone;
		this.email = email;
		this.mailaddress = mailaddress;
		this.zipCode = zipCode;
		this.qq = qq;
		this.lastLoginTime = lastLoginTime;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPauseDate() {
		return pauseDate;
	}
	public void setPauseDate(Date pauseDate) {
		this.pauseDate = pauseDate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", loginName=" + loginName + ", loginPassword=" + loginPassword
				+ ", status=" + status + ", createDate=" + createDate + ", pauseDate=" + pauseDate + ", realName="
				+ realName + ", idCard=" + idCard + ", gender=" + gender + ", occupation=" + occupation + ", telephone="
				+ telephone + ", email=" + email + ", mailaddress=" + mailaddress + ", zipCode=" + zipCode + ", qq="
				+ qq + ", lastLoginTime=" + lastLoginTime + "]";
	}
	

}
