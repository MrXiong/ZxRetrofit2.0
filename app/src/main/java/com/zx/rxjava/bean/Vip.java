package com.zx.rxjava.bean;


import java.io.Serializable;

/**
 * Created by zx on 2016/5/12.
 * retrofit内部我们使用的是Gson的convert，gson的convert区分大小写（必须和后台一致）
 * 解决方案：
 * 1、 @SerializedName通过这个注解可以重命名(Gson)
 * 2、采用其他解析方法，比如fastjson，jackson,其中只有fastjson是不区分大小写的，大写会自动转成小写
 */
public class Vip implements Serializable {

    /**
     * CustomerID : 65831ab4-f00a-46cb-9569-cfb5883d8e52
     * CustomerName : 15896347895
     * LoginName : 15896347895
     * TotalIntegral : 50
     * RemainIntegral : 50
     * Phone : 15896347895
     * CustomerClassID :
     * CustomerClassName :
     * Status : 1
     */
    public static final String VIP = "vip";
    public static final String VIP_ID = "vip_id";
    private String customerID;
    private String customerName;
    private String loginName;
    private String totalIntegral;
    private String remainIntegral;
    private String phone;
    private String customerClassID;
    private String customerClassName;
    private int status;
    private String orgName;
    private int imgId;
    private boolean vstatus;
    private String address;
    private String createTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean getVstatus() {
        return vstatus;
    }

    public void setVstatus(boolean vstatus) {
        this.vstatus = vstatus;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(String totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getRemainIntegral() {
        return remainIntegral;
    }

    public void setRemainIntegral(String remainIntegral) {
        this.remainIntegral = remainIntegral;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerClassID() {
        return customerClassID;
    }

    public void setCustomerClassID(String customerClassID) {
        this.customerClassID = customerClassID;
    }

    public String getCustomerClassName() {
        return customerClassName;
    }

    public void setCustomerClassName(String customerClassName) {
        this.customerClassName = customerClassName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if(status == 0) {
            setVstatus(true);
        }
        if(status == 1) {
            setVstatus(false);
        }
    }
}
