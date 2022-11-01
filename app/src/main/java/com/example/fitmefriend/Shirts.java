package com.example.fitmefriend;

public class Shirts {
    private String color;
    private String category;
    private  int imageResourceId;

    public Shirts() {
        color = "";
        category = "";
        imageResourceId = 0;
    }

    public Shirts(String color, String category, int imageResourceId) {
        this.color = color;
        this.category = category;
        this.imageResourceId = imageResourceId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
