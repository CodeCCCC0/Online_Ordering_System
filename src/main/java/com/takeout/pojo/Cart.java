package com.takeout.pojo;

public class Cart {
    private int id;
    private int foodId;
    private String specification;
    private int amount;
    private String uname;

    public Cart() {}

    public Cart(int id, int foodId, String specification, int amount, String uname) {
        this.id = id;
        this.foodId = foodId;
        this.specification = specification;
        this.amount = amount;
        this.uname = uname;
    }

    public int getId() {
        return id;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", specification='" + specification + '\'' +
                ", amount=" + amount +
                ", uname='" + uname + '\'' +
                '}';
    }
}
