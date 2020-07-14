package com.resta.resta.pojo;

import java.io.Serializable;

public class Step implements Serializable {
    private Integer stepid;
    private Integer cashstep;
    private Integer chefstep;
    private Integer adminstep;

    public Integer getStepid() {
        return stepid;
    }

    public void setStepid(Integer stepid) {
        this.stepid = stepid;
    }

    public Integer getCashstep() {
        return cashstep;
    }

    public void setCashstep(Integer cashstep) {
        this.cashstep = cashstep;
    }

    public Integer getChefstep() {
        return chefstep;
    }

    public void setChefstep(Integer chefstep) {
        this.chefstep = chefstep;
    }

    public Integer getAdminstep() {
        return adminstep;
    }

    public void setAdminstep(Integer adminstep) {
        this.adminstep = adminstep;
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepid=" + stepid +
                ", cashstep=" + cashstep +
                ", chefstep=" + chefstep +
                ", adminstep=" + adminstep +
                '}';
    }
}
