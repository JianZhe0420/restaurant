package com.resta.resta.dao;

import com.resta.resta.pojo.Shift;
import com.resta.resta.pojo.Table;
import com.resta.resta.pojo.Vip;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface Shiftdao {

    @Select(" select * from shift")
    public List<Shift> selectAllShift();

    @Select("select * from shift where shifttimebegin between #{start} and #{end}")
    public List<Shift> selectShiftByTIme(Date start,Date end);

    @Select(" select count(*) from shift")
    public Integer selectAllShiftNum();

    @Select("select * from shift where shiftuser=#{shiftuser}")
    public List<Shift> selectShiftByuserid(Integer shiftuser);

    @Insert(" insert into shift ( shiftuser,shifttimebegin ) values (#{shiftuser},#{shifttimebegin}) ")
    public void addShift(Shift shift);

    @Delete("delete from shift where shiftuser=#{shiftuser}")
    public void deleteShiftByuserid(Integer shiftuser);
}
