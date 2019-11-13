package com.example.wewashadmin.Adapters;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayOrders {
    public String orderId, name,address,area,phone,date,Time,Clothes;

    public DisplayOrders(String orderId, String name, String address, String area, String phone, String date, String time, String clothes) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.area = area;
        this.phone = phone;
        this.date = date;
        this.Time = time;
        this.Clothes = clothes;
    }

    public DisplayOrders(){

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getClothes() {
        return Clothes;
    }

    public void setClothes(String clothes) {
        this.Clothes = clothes;
    }



}
