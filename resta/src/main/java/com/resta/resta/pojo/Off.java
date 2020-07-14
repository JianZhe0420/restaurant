package com.resta.resta.pojo;

import java.io.Serializable;

public class Off implements Serializable {
    private Integer offid;
    private String offname;
    private Integer offType;
    private Double offmax;
    private Double offsub;
    private Double offrate;
    private Integer offstatus;

    public Integer getOffid() {
        return offid;
    }

    public void setOffid(Integer offid) {
        this.offid = offid;
    }

    public String getOffname() {
        return offname;
    }

    public void setOffname(String offname) {
        this.offname = offname;
    }

    public Integer getOffType() {
        return offType;
    }

    public void setOffType(Integer offType) {
        this.offType = offType;
    }

    public Double getOffmax() {
        return offmax;
    }

    public void setOffmax(Double offmax) {
        this.offmax = offmax;
    }

    public Double getOofsub() {
        return offsub;
    }

    public void setOofsub(Double oofsub) {
        this.offsub = oofsub;
    }

    public Double getOffrate() {
        return offrate;
    }

    public void setOffrate(Double offrate) {
        this.offrate = offrate;
    }

    public Integer getOffstatus() {
        return offstatus;
    }

    public void setOffstatus(Integer offstaus) {
        this.offstatus = offstaus;
    }


    @Override
    public String toString() {
        return "Off{" +
                "offid=" + offid +
                ", offname='" + offname + '\'' +
                ", offType=" + offType +
                ", offmax=" + offmax +
                ", offsub=" + offsub +
                ", offrate=" + offrate +
                ", offstatus=" + offstatus +
                '}';
    }
}

