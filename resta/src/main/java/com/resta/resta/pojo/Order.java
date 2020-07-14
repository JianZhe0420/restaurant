package com.resta.resta.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer orderid;
    private Double orderprice;
    private Date ordertime;
    private String orderstatus;
    private String ordertype;
    private String orderaddress;
    private Integer orderuser;
    private String orderoffname;
    private String ordermoneytype;
    private String ordertimestring;
    private Integer ordertablenum;

    public Integer getOrdertablenum() {
        return ordertablenum;
    }

    public void setOrdertablenum(Integer ordertablenum) {
        this.ordertablenum = ordertablenum;
    }

    public String getOrdertimestring() {
        return ordertimestring;
    }

    public void setOrdertimestring(String ordertimestring) {
        this.ordertimestring = ordertimestring;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }

    public Integer getOrderuser() {
        return orderuser;
    }

    public void setOrderuser(Integer orderuser) {
        this.orderuser = orderuser;
    }

    public String getOrderoffname() {
        return orderoffname;
    }

    public void setOrderoffname(String orderoffname) {
        this.orderoffname = orderoffname;
    }

    public String getOrdermoneytype() {
        return ordermoneytype;
    }

    public void setOrdermoneytype(String ordermoneytype) {
        this.ordermoneytype = ordermoneytype;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", orderprice=" + orderprice +
                ", ordertime=" + ordertime +
                ", orderstatus='" + orderstatus + '\'' +
                ", ordertype='" + ordertype + '\'' +
                ", orderaddress='" + orderaddress + '\'' +
                ", orderuser=" + orderuser +
                ", orderoffname='" + orderoffname + '\'' +
                ", ordermoneytype='" + ordermoneytype + '\'' +
                ", ordertimestring='" + ordertimestring + '\'' +
                ", ordertablenum=" + ordertablenum +
                '}';
    }
}
