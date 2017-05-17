package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务账号信息实体类
 * @author pjz
 *
 */
public class Business implements Serializable {

	private static final long serialVersionUID = 7347659287879179919L;

	private int businessId; //业务账号ID
	private int accountId; //账务账号ID
	private int costId; //资费类型ID
	private String unixHost; //服务器IP
	private String osUserName; //OS账户名(宽带登录名)
	private String loginPassword; //OS账户登录密码
	private int status; //OS账户使用状态(0-开通，1-暂停。创建时默认开通)
	private Date createDate; //OS账户创建日期
	private Date pauseDate; //暂停日期
	
	private String idCard; //账务账号身份证
	private String realName; //账务账号真实姓名
	private String name; //资费类型名称
	
	private int flag = -1;	//资费类型修改标志，默认为-1，一旦资费类型有修改，就将修改的资费类型ID存入flag
							//月底统一判断该值是否为-1，如果不是，就对该业务账号的资费类型进行更改
	
	public Business() {
		super();
	}

	public Business(int businessId, int accountId, int costId, String unixHost, String osUserName, String loginPassword,
			int status, Date createDate, Date pauseDate, String idCard, String realName, String name, int flag) {
		super();
		this.businessId = businessId;
		this.accountId = accountId;
		this.costId = costId;
		this.unixHost = unixHost;
		this.osUserName = osUserName;
		this.loginPassword = loginPassword;
		this.status = status;
		this.createDate = createDate;
		this.pauseDate = pauseDate;
		this.idCard = idCard;
		this.realName = realName;
		this.name = name;
		this.flag = flag;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCostId() {
		return costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getOsUserName() {
		return osUserName;
	}

	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", accountId=" + accountId + ", costId=" + costId + ", unixHost="
				+ unixHost + ", osUserName=" + osUserName + ", loginPassword=" + loginPassword + ", status=" + status
				+ ", createDate=" + createDate + ", pauseDate=" + pauseDate + ", idCard=" + idCard + ", realName="
				+ realName + ", name=" + name + ", flag=" + flag + "]";
	}
	
	
	
	
}
