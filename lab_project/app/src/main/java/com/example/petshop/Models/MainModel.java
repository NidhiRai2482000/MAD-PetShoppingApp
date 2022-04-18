package com.example.petshop.Models;

public class MainModel {
    String name,price,desc,username;
    int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public MainModel(int image, String name, String price, String desc, String username) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.username= username;
    }

}
