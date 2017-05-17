package edu.nclg.netctoss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ҵ���˺���Ϣʵ����
 * @author pjz
 *
 */
public class Business implements Serializable {

	private static final long serialVersionUID = 7347659287879179919L;

	private int businessId; //ҵ���˺�ID
	private int accountId; //�����˺�ID
	private int costId; //�ʷ�����ID
	private String unixHost; //������IP
	private String osUserName; //OS�˻���(�����¼��)
	private String loginPassword; //OS�˻���¼����
	private int status; //OS�˻�ʹ��״̬(0-��ͨ��1-��ͣ������ʱĬ�Ͽ�ͨ)
	private Date createDate; //OS�˻���������
	private Date pauseDate; //��ͣ����
	
	private String idCard; //�����˺����֤
	private String realName; //�����˺���ʵ����
	private String name; //�ʷ���������
	
	private int flag = -1;	//�ʷ������޸ı�־��Ĭ��Ϊ-1��һ���ʷ��������޸ģ��ͽ��޸ĵ��ʷ�����ID����flag
							//�µ�ͳһ�жϸ�ֵ�Ƿ�Ϊ-1��������ǣ��ͶԸ�ҵ���˺ŵ��ʷ����ͽ��и���
	
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
