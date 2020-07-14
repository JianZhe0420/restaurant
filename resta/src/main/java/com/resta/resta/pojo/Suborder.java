package com.resta.resta.pojo;

import java.io.Serializable;
import java.util.Date;

public class Suborder implements Serializable {
    private Integer suborderid;
    private String suborderdish;
    private Integer orderid;
    private String suborderstatus;
    private Double subordermoney;
    private Integer ordercount;
    private String subordermoneytype;
    private Date subordertime;
    private String subordertype;

    public String getSubordertimestring() {
        return subordertimestring;
    }

    public void setSubordertimestring(String subordertimestring) {
        this.subordertimestring = subordertimestring;
    }

    private String subordertimestring;

    public Integer getSuborderid() {
        return suborderid;
    }

    public void setSuborderid(Integer suborderid) {
        this.suborderid = suborderid;
    }

    public String getSuborderdish() {
        return suborderdish;
    }

    public void setSuborderdish(String suborderdish) {
        this.suborderdish = suborderdish;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getSuborderstatus() {
        return suborderstatus;
    }

    public void setSuborderstatus(String suborderstatus) {
        this.suborderstatus = suborderstatus;
    }

    public Double getSubordermoney() {
        return subordermoney;
    }

    public void setSubordermoney(Double subordermoney) {
        this.subordermoney = subordermoney;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

    public String getSubordermoneytype() {
        return subordermoneytype;
    }

    public void setSubordermoneytype(String subordermoneytype) {
        this.subordermoneytype = subordermoneytype;
    }

    public Date getSubordertime() {
        return subordertime;
    }

    public void setSubordertime(Date subordertime) {
        this.subordertime = subordertime;
    }

    public String getSubordertype() {
        return subordertype;
    }

    public void setSubordertype(String subordertype) {
        this.subordertype = subordertype;
    }

    @Override
    public String toString() {
        return "Suborder{" +
                "suborderid=" + suborderid +
                ", suborderdish='" + suborderdish + '\'' +
                ", orderid=" + orderid +
                ", suborderstatus='" + suborderstatus + '\'' +
                ", subordermoney=" + subordermoney +
                ", ordercount=" + ordercount +
                ", subordermoneytype='" + subordermoneytype + '\'' +
                ", subordertime=" + subordertime +
                ", subordertype='" + subordertype + '\'' +
                '}';
    }
}
