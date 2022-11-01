package com.example.fitmefriend;

public class Pants {
    private String pColor;
    private String pCategory;
    private  int pImageResourceId;

    public Pants() {
        this.pColor = "";
        this.pCategory = "";
        this.pImageResourceId = 0;
    }

    public Pants(String pColor, String pCategory, int pImageResourceId) {
        this.pColor = pColor;
        this.pCategory = pCategory;
        this.pImageResourceId = pImageResourceId;
    }

    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public int getpImageResourceId() {
        return pImageResourceId;
    }

    public void setpImageResourceId(int pImageResourceId) {
        this.pImageResourceId = pImageResourceId;
    }
}
