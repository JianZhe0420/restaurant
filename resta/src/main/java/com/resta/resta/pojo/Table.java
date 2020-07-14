package com.resta.resta.pojo;

import java.io.Serializable;

public class Table implements Serializable {
    private Integer tableid;
    private Integer tablemsg;

    public Integer getTableid() {
        return tableid;
    }

    public void setTableid(Integer tableid) {
        this.tableid = tableid;
    }

    public Integer getTablemsg() {
        return tablemsg;
    }

    public void setTablemsg(Integer tablemsg) {
        this.tablemsg = tablemsg;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableid=" + tableid +
                ", tablemsg=" + tablemsg +
                '}';
    }
}
