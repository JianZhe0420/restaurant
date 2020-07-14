package com.resta.resta.pojo;

import java.io.Serializable;

public class Dishtype implements Serializable {


    private Integer dishtypeid;
    private String dishtypename;


    public Integer getDishtypeid() {
        return dishtypeid;
    }

    public void setDishtypeid(Integer dishtypeid) {
        this.dishtypeid = dishtypeid;
    }

    public String getDishtypename() {
        return dishtypename;
    }

    public void setDishtypename(String dishtypename) {
        this.dishtypename = dishtypename;
    }


    @Override
    public String toString() {
        return "Dishtype{" +
                "dishtypeid=" + dishtypeid +
                ", dishtypename='" + dishtypename + '\'' +
                '}';
    }
}

