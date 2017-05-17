package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * �ʷ���Ϣʵ����
 * @author pjz
 * ˽�����ԡ���������get/set������toString����
 *
 */
public class Cost implements Serializable {

	private static final long serialVersionUID = 4554179673452541389L;
	
	private int costId;			//�ʷ�ID
	private String name;		//�ʷ�����
	private int baseDuration;	//����ʱ��
	private double baseCost;	//��������
	private double unitCost;	//��λ����
	private int status;			//�ʷ�״̬(0-���ã�1-��ͣ)
	private String descr;		//�ʷ�����
	private Date createTime;	//����ʱ��
	private Date startTime;		//��ʼʱ��
	private int costType;		//�ʷ�����(����0,����1,�ײ�2,��ʱ�շ�3)
	
	public Cost() {
		super();
	}
	public Cost(int costId, String name, int baseDuration, double baseCost, double unitCost, int status,
			String descr, Date createTime, Date startTime, int costType) {
		super();
		this.costId = costId;
		this.name = name;
		this.baseDuration = baseDuration;
		this.baseCost = baseCost;
		this.unitCost = unitCost;
		this.status = status;
		this.descr = descr;
		this.createTime = createTime;
		this.startTime = startTime;
		this.costType = costType;
	}
	
	public int getCostId() {
		return costId;
	}
	public void setCostId(int costId) {
		this.costId = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBaseDuration() {
		return baseDuration;
	}
	public void setBaseDuration(int baseDuration) {
		this.baseDuration = baseDuration;
	}
	public double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getCostType() {
		return costType;
	}
	public void setCostType(int costType) {
		this.costType = costType;
	}
	
	@Override
	public String toString() {
		return "Cost [costId=" + costId + ", name=" + name + ", baseDuration=" + baseDuration + ", baseCost=" + baseCost
				+ ", unitCost=" + unitCost + ", status=" + status + ", descr=" + descr + ", createTime=" + createTime
				+ ", startTime=" + startTime + ", costType=" + costType + "]";
	}
	

}
