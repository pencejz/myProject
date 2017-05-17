package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单明细实体
 * 保存用户每个月的账单明细
 * @author pjz
 *
 */
public class BillItem implements Serializable {

	private static final long serialVersionUID = -437240306360919377L;
	
	private int billItemId;			//账单明细ID
	private int accountId;			//账务账号ID
	private int costId;				//资费类型ID
	private String osUsername;		//OS宽带登录账号
	private String unixHost;		//服务器IP
	private String duration;		//一个业务账号登录的时长
	private double businessCharge;	//一个业务账号登录的费用
	private Date date;				//保存账单详单表中的一个登出时间，以便账单管理表获取时间
	private String costName;		//资费类型名称
	
	public BillItem() {
		super();
	}

	public BillItem(int billItemId, int accountId, int costId, String osUsername, String unixHost, String duration,
			double businessCharge, Date date, String costName) {
		super();
		this.billItemId = billItemId;
		this.accountId = accountId;
		this.costId = costId;
		this.osUsername = osUsername;
		this.unixHost = unixHost;
		this.duration = duration;
		this.businessCharge = businessCharge;
		this.date = date;
		this.costName = costName;
	}

	public int getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(int billItemId) {
		this.billItemId = billItemId;
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

	public String getOsUsername() {
		return osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getBusinessCharge() {
		return businessCharge;
	}

	public void setBusinessCharge(double businessCharge) {
		this.businessCharge = businessCharge;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	@Override
	public String toString() {
		return "BillItem [billItemId=" + billItemId + ", accountId=" + accountId + ", costId=" + costId
				+ ", osUsername=" + osUsername + ", unixHost=" + unixHost + ", duration=" + duration
				+ ", businessCharge=" + businessCharge + ", date=" + date + ", costName=" + costName + "]";
	}
	
	
	
	
}
