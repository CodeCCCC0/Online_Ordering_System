package com.takeout.pojo;

public class Food {
    private int foodId;
    private String image;
    private String foodName;
    private String ingredients;
    private String specification;
    private String classification;
    private double price;
    private int salesVolume;
    private int status;

    public Food() {}

    public Food(String image,
                String foodName,
                String ingredients,
                String specification,
                String classification,
                double price,
                int salesVolume,
                int status) {
        this.image = image;
        this.foodName = foodName;
        this.ingredients = ingredients;
        this.specification = specification;
        this.classification = classification;
        this.price = price;
        this.salesVolume = salesVolume;
        this.status = status;
    }

    public Food(int foodId,
                String image,
                String foodName,
                String ingredients,
                String specification,
                String classification,
                double price,
                int salesVolume,
                int status) {
        this.foodId = foodId;
        this.image = image;
        this.foodName = foodName;
        this.ingredients = ingredients;
        this.specification = specification;
        this.classification = classification;
        this.price = price;
        this.salesVolume = salesVolume;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", image='" + image + '\'' +
                ", foodName='" + foodName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", specification='" + specification + '\'' +
                ", classification='" + classification + '\'' +
                ", price=" + price +
                ", salesVolume=" + salesVolume +
                ", status=" + status +
                '}';
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
