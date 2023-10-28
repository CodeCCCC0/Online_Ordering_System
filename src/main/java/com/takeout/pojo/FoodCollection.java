package com.takeout.pojo;

public class FoodCollection {
    private int id;
    private int foodId;
    private String foodName;
    private String uname;

    public FoodCollection() {}

    public FoodCollection(int id, int foodId, String foodName, String uname) {
        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.uname = uname;
    }

    public FoodCollection(int foodId, String foodName, String uname) {
        this.foodId = foodId;
        this.foodName = foodName;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
