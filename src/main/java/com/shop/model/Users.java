package com.shop.model;

import java.io.Serializable;
import java.sql.Date;

public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2019290103837738584L;
	
	 private Long id; //�û�ID
	    private String account; //�ֻ��ţ��˺�
	    private Long tenantId; //��Ա��˾ID
	    private String memberName; //��Ա����
	    private String password; //���ܺ������
	    private Byte delFlag; //ɾ����ʾ
	    private Date createTime;
	    private Date updateTime;

	    private Byte gender; //�Ա�
	    private String position; //Ա��ְλ
	    private String mailAddress; //�����ַ
	    private String portrait; //ͷ���ַ
	    private String tenantName;
	    private String telephone; //�̻�
	    private String fax; //����
	    private String qq;
	    private String wechat;


	    private String addressId; //��ַID
	    private String address; //��ϸ��ַ
	    private String invitation;
	    private String jobNumber;
	    private Byte isAdmin;  //�Ƿ����Ա 0-��  1-��    Ĭ��1


	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getPortrait() {
	        return portrait;
	    }

	    public void setPortrait(String portrait) {
	        this.portrait = portrait;
	    }

	    public String getAccount() {
	        return account;
	    }

	    public void setAccount(String account) {
	        this.account = account;
	    }

	    public Long getTenantId() {
	        return tenantId;
	    }

	    public void setTenantId(Long tenantId) {
	        this.tenantId = tenantId;
	    }

	    public String getMemberName() {
	        return memberName;
	    }

	    public void setMemberName(String memberName) {
	        this.memberName = memberName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Byte getDelFlag() {
	        return delFlag;
	    }

	    public void setDelFlag(Byte delFlag) {
	        this.delFlag = delFlag;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

	    public Byte getGender() {
	        return gender;
	    }

	    public void setGender(Byte gender) {
	        this.gender = gender;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public String getMailAddress() {
	        return mailAddress;
	    }

	    public void setMailAddress(String mailAddress) {
	        this.mailAddress = mailAddress;
	    }

	    public String getTelephone() {
	        return telephone;
	    }

	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }

	    public String getFax() {
	        return fax;
	    }

	    public void setFax(String fax) {
	        this.fax = fax;
	    }

	    public String getQq() {
	        return qq;
	    }

	    public void setQq(String qq) {
	        this.qq = qq;
	    }

	    public String getAddressId() {
	        return addressId;
	    }

	    public void setAddressId(String addressId) {
	        this.addressId = addressId;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getTenantName() {
	        return tenantName;
	    }

	    public void setTenantName(String tenantName) {
	        this.tenantName = tenantName;
	    }

	    public String getWechat() {
	        return wechat;
	    }

	    public void setWechat(String wechat) {
	        this.wechat = wechat;
	    }


	    public String getInvitation() {
	        return invitation;
	    }

	    public void setInvitation(String invitation) {
	        this.invitation = invitation;
	    }

	    public String getJobNumber() {
	        return jobNumber;
	    }

	    public void setJobNumber(String jobNumber) {
	        this.jobNumber = jobNumber;
	    }

	    public Byte getIsAdmin() {
	        return isAdmin;
	    }

	    public void setIsAdmin(Byte isAdmin) {
	        this.isAdmin = isAdmin;
	    }

	    @Override
	    public String toString() {
	        return "Users{" +
	                "id=" + id +
	                ", account='" + account + '\'' +
	                ", tenantId=" + tenantId +
	                ", memberName='" + memberName + '\'' +
	                ", password='" + password + '\'' +
	                ", delFlag=" + delFlag +
	                ", createTime=" + createTime +
	                ", updateTime=" + updateTime +
	                ", gender=" + gender +
	                ", position='" + position + '\'' +
	                ", mailAddress='" + mailAddress + '\'' +
	                ", portrait='" + portrait + '\'' +
	                ", tenantName='" + tenantName + '\'' +
	                ", telephone='" + telephone + '\'' +
	                ", fax='" + fax + '\'' +
	                ", qq='" + qq + '\'' +
	                ", wechat='" + wechat + '\'' +
	                ", addressId=" + addressId +
	                ", address='" + address + '\'' +
	                ", invitation='" + invitation + '\'' +
	                ", jobNumber='" + jobNumber + '\'' +
	                ", isAdmin=" + isAdmin +
	                '}';
	    }

}

