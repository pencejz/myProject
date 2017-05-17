package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单管理实体
 * 以月为单位，保存所有用户的账单信息
 * @author pjz
 *
 */
public class Bill implements Serializable {
	
	private static final long serialVersionUID = 6113359180697901852L;
	
	private int billId;			//账单ID
	private int accountId;		//账务账号ID
	private String name;		//真实姓名
	private String idcard;		//身份证号码
	private double monthCharge;	//一个用户一个月所有业务账号的总费用
	private Date date;			//日期
	private String yearMonth;	//年月
	private String payment;		//支付方式（预留属性）
	private String payStatus;	//支付状态（预留属性）
	
	private String accountName;	//账务账号名称
	
	public Bill() {
		super();
	}

	public Bill(int billId, int accountId, String name, String idcard, double monthCharge, Date date, String yearMonth,
			String payment, String payStatus, String accountName) {
		super();
		this.billId = billId;
		this.accountId = accountId;
		this.name = name;
		this.idcard = idcard;
		this.monthCharge = monthCharge;
		this.date = date;
		this.yearMonth = yearMonth;
		this.payment = payment;
		this.payStatus = payStatus;
		this.accountName = accountName;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public double getMonthCharge() {
		return monthCharge;
	}

	public void setMonthCharge(double monthCharge) {
		this.monthCharge = monthCharge;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", accountId=" + accountId + ", name=" + name + ", idcard=" + idcard
				+ ", monthCharge=" + monthCharge + ", date=" + date + ", yearMonth=" + yearMonth + ", payment="
				+ payment + ", payStatus=" + payStatus + ", accountName=" + accountName + "]";
	}
	
	
	
	
}
