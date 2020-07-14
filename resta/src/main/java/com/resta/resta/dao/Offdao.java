package com.resta.resta.dao;

import com.resta.resta.pojo.Off;
import com.resta.resta.pojo.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Offdao {

    @Select("select * from off where offstatus==1")
    public List<Off> getAllOffWithOffStatusIsOpen();

    @Select("select * from off")
    public List<Off> selectAllOff();

    @Select("select * from off where offid=#{offid}")
    public Off selectOffByid(Integer offid);

    @Select("select * from off where offstatus=1")
    public List<Off> selectAll1Off();

    @Select("select * from off where offstatus=0")
    public List<Off> selectAll0Off();

    @Select("select count(*) from off")
    public Integer selectOffNum();

    @Select("select * from off where offstatus=1 and offType!=3")
    public List<Off> getCashAllOffWithOffStatusIsOpen();

    @Select("select * from off where offType=3")
    public Off getVipOff();

    @Select("select count(*) from off where offstatus=1 and offType!=3")
    public Integer getCashAllOffWithOffStatusIsOpenNum();

    @Update("update off set offType=#{offType},offmax=#{offmax},oofsub=#{offmax},offsub=#{offsub},offrate=#{offrate},offstatus=#{offstatus} where offname=#{offname}")
    public void updateOff(Off off);

    @Update("update off set offrate=#{offrate}")
    public void updateVipOff(Double offrate);

    @Update("update off set offstatus=#{offstatus} where offid=#{offid}")
    public void updateOffStatus(Integer offstatus,Integer offid);

    @Insert(" insert into off ( offname,offType,offmax,offsub,offrate,offstatus ) values (#{offname},#{offType},#{offmax},#{offsub},#{offrate},#{offstatus}) ")
    public void addOff(Off off);

    @Delete("delete from off where offid=#{offid}")
    public void deleteOffById(Integer offid);
}
