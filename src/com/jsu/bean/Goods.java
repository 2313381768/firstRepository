package com.jsu.bean;

import java.util.Date;
import java.util.List;

public class Goods {
private int id;
private String name;
private int state;
private double price;
private int priority;
private String describe;
private int classesId;
private Date upTime;
private double discount;
private int looks;
private int SellerId;
private List<String> pictrues;
    public Goods(int id, String name, int state, double price, int priority, String describe, int classesId, Date upTime, double discount, int looks, List<String> pictrues) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.price = price;
        this.priority = priority;
        this.describe = describe;
        this.classesId = classesId;
        this.upTime = upTime;
        this.discount = discount;
        this.looks = looks;
        this.pictrues = pictrues;
    }

    public Goods() {
    }

    public int getSellerId() {
        return SellerId;
    }

    public void setSellerId(int sellerId) {
        SellerId = sellerId;
    }

    public List<String> getPictrues() {
        return pictrues;
    }

    public void setPictrues(List<String> pictrues) {
        this.pictrues = pictrues;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getLooks() {
        return looks;
    }

    public void setLooks(int looks) {
        this.looks = looks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
