package com.resta.resta.pojo;

import java.io.Serializable;
import java.util.Date;

public class Shift implements Serializable {
    private Integer shiftid;

    private Integer shiftuser;

    private Date shifttimebegin;

    private String shifttimebeginstring;

    public String getShifttimebeginstring() {
        return shifttimebeginstring;
    }

    public void setShifttimebeginstring(String shifttimebeginstring) {
        this.shifttimebeginstring = shifttimebeginstring;
    }

    public Integer getShiftid() {

        return shiftid;
    }

    public void setShiftid(Integer shiftid) {
        this.shiftid = shiftid;
    }

    public Integer getShiftuser() {
        return shiftuser;
    }

    public void setShiftuser(Integer shiftuser) {
        this.shiftuser = shiftuser;
    }

    public Date getShifttimebegin() {
        return shifttimebegin;
    }

    public void setShifttimebegin(Date shifttimebegin) {
        this.shifttimebegin = shifttimebegin;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "shiftid=" + shiftid +
                ", shiftuser=" + shiftuser +
                ", shifttimebegin=" + shifttimebegin +
                '}';
    }
}
