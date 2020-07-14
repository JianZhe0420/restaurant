package com.resta.resta.pojo;

import java.io.Serializable;

public class Vip implements Serializable {
    private Integer vipip;
    private String vipname;
    private Integer viptel;

    public Integer getVipip() {
        return vipip;
    }

    public void setVipip(Integer vipip) {
        this.vipip = vipip;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public Integer getViptel() {
        return viptel;
    }

    public void setViptel(Integer viptel) {
        this.viptel = viptel;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vipip=" + vipip +
                ", vipname='" + vipname + '\'' +
                ", viptel=" + viptel +
                '}';
    }
}
