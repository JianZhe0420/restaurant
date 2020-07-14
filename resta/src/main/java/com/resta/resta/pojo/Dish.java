package com.resta.resta.pojo;

import java.io.Serializable;

public class Dish implements Serializable {

    private Integer dishid;
    private String dishname;
    private Double dishprice;
    private Integer dishstatus;
    private Integer dishtype;
    private Integer dishstock;


    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public Double getDishprice() {
        return dishprice;
    }

    public void setDishprice(Double dishprice) {
        this.dishprice = dishprice;
    }

    public Integer getDishstatus() {
        return dishstatus;
    }

    public void setDishstatus(Integer dishstatus) {
        this.dishstatus = dishstatus;
    }

    public Integer getDishtype() {
        return dishtype;
    }

    public void setDishtype(Integer dishtype) {
        this.dishtype = dishtype;
    }

    public Integer getDishstock() {
        return dishstock;
    }

    public void setDishstock(Integer dishstock) {
        this.dishstock = dishstock;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "dishid=" + dishid +
                ", dishname='" + dishname + '\'' +
                ", dishprice=" + dishprice +
                ", dishstatus=" + dishstatus +
                ", dishtype=" + dishtype +
                ", dishstock=" + dishstock +
                '}';
    }
}
