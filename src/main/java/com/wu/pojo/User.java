package com.wu.pojo;

public class User {
    private Integer id;
    private String userName;
    private String realName;
    private String password;
    private String phoneNum;
    private String mail;
    private String head;


    public User() {
    }


    public User(Integer id, String userName, String realName, String password, String phoneNum, String mail) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.mail = mail;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", mail='" + mail + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
