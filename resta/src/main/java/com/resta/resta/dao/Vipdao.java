package com.resta.resta.dao;

import com.resta.resta.pojo.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Vipdao {
    @Select("select * from vip where vipname=#{vipname}")
    public Vip getVipByName(String vipname);

    @Select("select * from vip")
    public List<Vip> selectAllVip();

    @Select("select count(*) from vip")
    public Integer selectAllVipNum();

    @Select("select * from vip where viptel=#{viptel}")
    public List<Vip> getVipByTel(Integer viptel);

    @Update("update vip set vipip=#{vipip},vipname=#{vipname},viptel=#{viptel}")
    public void updateVip(Vip vip);

    @Insert(" insert into vip ( vipname,viptel ) values (#{vipname},#{viptel}) ")
    public void addVip(Vip vip);

    @Delete("delete from vip where vipip=#{vipip}")
    public void deleteVipByIp(Integer vipip);

}
