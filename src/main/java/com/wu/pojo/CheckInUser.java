package com.wu.pojo;

public class CheckInUser {
    private Integer id;
    private Integer userId;
    private String trueName;
    private String idNum;
    private String phoneNum;
    private Integer orderId;

    public CheckInUser() {
    }

    public CheckInUser(Integer id, Integer userId, String trueName, String idNum, String phoneNum, Integer orderId) {
        this.id = id;
        this.userId = userId;
        this.trueName = trueName;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "CheckInUserMapper{" +
                "id=" + id +
                ", userId=" + userId +
                ", trueName='" + trueName + '\'' +
                ", idNum='" + idNum + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
