package com.example.wewashadmin.Adapters;

public class DisplayOrders {


    public String orderId, name,address,area,phone,dateBooked,expectedPickupTime,noOfClothes,status,key;
    public Integer progress;



    public DisplayOrders(String orderId, String name, String address, String area, String phone, String dateBooked, String expectedPickupTime, String noOfClothes, String status, Integer progress, String key) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.area = area;
        this.phone = phone;
        this.dateBooked = dateBooked;
        this.expectedPickupTime = expectedPickupTime;
        this.noOfClothes = noOfClothes;
        this.status = status;
        this.progress = progress;
        this.key = key;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getExpectedPickupTime() {
        return expectedPickupTime;
    }

    public void setExpectedPickupTime(String expectedPickupTime) {
        this.expectedPickupTime = expectedPickupTime;
    }

    public String getNoOfClothes() {
        return noOfClothes;
    }

    public void setNoOfClothes(String noOfClothes) {
        this.noOfClothes = noOfClothes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}