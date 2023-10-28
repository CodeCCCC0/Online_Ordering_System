package com.takeout.pojo;

public class Order {
    private int orderId;
    private String uname;
    private int foodId;
    private String specification;
    private int amount;
    private double totalPrice;
    private String address;
    private String tel;
    private String date;
    private String orderStatus;

    public Order() {}

    public Order(int orderId, String uname, int foodId, String specification, int amount, double totalPrice, String address, String tel, String date, String orderStatus) {
        this.orderId = orderId;
        this.uname = uname;
        this.foodId = foodId;
        this.specification = specification;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.address = address;
        this.tel = tel;
        this.date = date;
        this.orderStatus = orderStatus;
    }

    public Order(String uname, int foodId, String specification, int amount, double totalPrice, String address, String tel, String date, String orderStatus) {
        this.uname = uname;
        this.foodId = foodId;
        this.specification = specification;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.address = address;
        this.tel = tel;
        this.date = date;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", uname='" + uname + '\'' +
                ", foodId=" + foodId +
                ", specification='" + specification + '\'' +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", date='" + date + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
