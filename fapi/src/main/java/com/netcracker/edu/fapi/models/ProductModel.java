package com.netcracker.edu.fapi.models;

public class ProductModel {
    private int id;
    private String company;
    private String description;
    private String image;

    public ProductModel() {
    }

    public ProductModel(int id, String company, String description, String image) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
