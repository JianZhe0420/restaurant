package com.resta.resta.dao;

import com.resta.resta.pojo.Dish;
import com.resta.resta.pojo.Dishtype;
import com.resta.resta.pojo.Table;
import com.resta.resta.pojo.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface Tabledao {

    @Select(" select * from table1")
    public List<Table> selectAllTable();

    @Select(" select count(*) from table1")
    public Integer selectAllTableNum();

    @Select(" select * from table1 where tablemsg=1")
    public List<Table> selectAllCanUseTable();

    @Select(" select tableid from table1 order by tableid desc limit 0,1")
    public Integer deleteLastTable();

    @Select(" select * from table1 where tableid=#{tableid}")
    public Table selectTableByid(Integer tableid);

    @Update("update table1 set tablemsg=#{tablemsg} where tableid=#{tableid}")
    public void update(Table table);

    @Update("update table1 set tablemsg=#{tablemsg}")
    public void upMsgdate(Integer tablemsg);

    @Update("update table1 set tablemsg=2 where tableid=#{tableid}")
    public void change2Table(Integer tableid);

    @Update("update table1 set tablemsg=1 where tableid=#{tableid}")
    public void change1Table(Integer tableid);

    @Insert(" insert into table1 ( tableid,tablemsg ) values (#{tableid},#{tablemsg}) ")
    public void addTable(Table table);

    @Delete("delete from table1 where tableid=#{tableid}")
    public void deleteTableById(Integer tableid);
}
