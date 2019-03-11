package com.wu.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOut;
    private String hotelName;
    private Integer houseCount;
    private Integer price;
    private String contactName;
    private String phone;
    private String mail;
    private Integer userId;
    private String houseName;
    private Integer houseId;
    private Date orderTime;

    private Integer starRow;
    private Integer pageSize;

    private Integer status;
    private String orderNum;

    public Order() {
    }

    public Order(Integer id, Date checkIn, Date checkOut, String hotelName, Integer houseCount, Integer price, String contactName, String phone, String mail, Integer userId, String houseName, Integer houseId, Date orderTime) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotelName = hotelName;
        this.houseCount = houseCount;
        this.price = price;
        this.contactName = contactName;
        this.phone = phone;
        this.mail = mail;
        this.userId = userId;
        this.houseName = houseName;
        this.houseId = houseId;
        this.orderTime = orderTime;
    }

    public Order(Date checkIn, Date checkOut, String hotelName, Integer houseCount, Integer price, String contactName, String phone, String mail, Integer userId, String houseName, Integer houseId, Date orderTime,Integer status,String orderNum) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotelName = hotelName;
        this.houseCount = houseCount;
        this.price = price;
        this.contactName = contactName;
        this.phone = phone;
        this.mail = mail;
        this.userId = userId;
        this.houseName = houseName;
        this.houseId = houseId;
        this.orderTime = orderTime;
        this.status = status;
        this.orderNum = orderNum;
    }


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public Integer getStarRow() {
        return starRow;
    }

    public void setStarRow(Integer starRow) {
        this.starRow = starRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", hotelName='" + hotelName + '\'' +
                ", houseCount=" + houseCount +
                ", price=" + price +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", userId=" + userId +
                ", houseName='" + houseName + '\'' +
                ", houseId=" + houseId +
                ", orderTime=" + orderTime +
                ", starRow=" + starRow +
                ", pageSize=" + pageSize +
                ", status=" + status +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }
}
