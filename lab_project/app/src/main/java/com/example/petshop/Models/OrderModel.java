package com.example.petshop.Models;

public class OrderModel {
    int image;
    String address,contact,orderno;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public OrderModel(int image, String orderno, String address, String contact) {
        this.image = image;
        this.address = address;
        this.contact = contact;
        this.orderno = orderno;
    }
}
