package com.takeout.pojo;

public class User {
    private String uname;
    private String pwd;
    private String userImage;
    private String email;
    private String tel;
    private int userType;
    private String createDate;

    public User() {
    }

    public User(String uname, String pwd, String userImage, String email, String tel, int userType, String createDate) {
        this.uname = uname;
        this.pwd = pwd;
        this.userImage = userImage;
        this.email = email;
        this.tel = tel;
        this.userType = userType;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userImage='" + userImage + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userType=" + userType +
                ", createDate='" + createDate +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
