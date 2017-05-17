package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * �˵��굥ʵ��
 * ��������û�ÿһ�ε�¼����Ϣ���û����һ�������ĵ�¼֮�󱣴�
 * @author pjz
 *
 */
public class BillDetail implements Serializable {

	private static final long serialVersionUID = -2971553844989846589L;
	
	private int billDetailId;	//�˵��굥ID
	private String unixHost;	//������IP
	private int costId;			//�ʷ�����ID
	private Date inTime;		//����ʱ��
	private Date outTime;		//�ǳ�ʱ��
	private int duration;		//ʱ�����ǳ�ʱ��-����ʱ�̣�
	private double charge;		//���ã����ε�¼���ã�
	private String costName;	//�ʷ�����
	
	public BillDetail(){
		super();
	}

	public BillDetail(int billDetailId, String unixHost, int costId, Date inTime, Date outTime, int duration,
			double charge, String costName) {
		super();
		this.billDetailId = billDetailId;
		this.unixHost = unixHost;
		this.costId = costId;
		this.inTime = inTime;
		this.outTime = outTime;
		this.duration = duration;
		this.charge = charge;
		this.costName = costName;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public int getCostId() {
		return costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	@Override
	public String toString() {
		return "BillDetail [billDetailId=" + billDetailId + ", unixHost=" + unixHost + ", costId=" + costId
				+ ", inTime=" + inTime + ", outTime=" + outTime + ", duration=" + duration + ", charge=" + charge
				+ ", costName=" + costName + "]";
	}
	
	
	
}
