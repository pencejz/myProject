package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * �˵���ϸʵ��
 * �����û�ÿ���µ��˵���ϸ
 * @author pjz
 *
 */
public class BillItem implements Serializable {

	private static final long serialVersionUID = -437240306360919377L;
	
	private int billItemId;			//�˵���ϸID
	private int accountId;			//�����˺�ID
	private int costId;				//�ʷ�����ID
	private String osUsername;		//OS�����¼�˺�
	private String unixHost;		//������IP
	private String duration;		//һ��ҵ���˺ŵ�¼��ʱ��
	private double businessCharge;	//һ��ҵ���˺ŵ�¼�ķ���
	private Date date;				//�����˵��굥���е�һ���ǳ�ʱ�䣬�Ա��˵�������ȡʱ��
	private String costName;		//�ʷ���������
	
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
