package com.resta.resta.dao;

import com.resta.resta.pojo.Dish;
import com.resta.resta.pojo.Dishtype;
import com.resta.resta.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Dishdao {

    @Insert(" insert into dish ( dishname,dishprice,dishstatus,dishtype,dishstock ) values (#{dishname},#{dishprice},#{dishstatus},#{dishtype},#{dishstock}) ")
    public void addDish(Dish dish);

    @Update("update dish set dishname=#{dishname},dishprice=#{dishprice},dishstatus=#{dishstatus},dishtype=#{dishtype},dishstock=#{dishstock} where dishid=#{dishid} ")
    public void update(Dish dish);

    @Update("update dish set dishprice=#{dishprice} where dishtype=0 ")
    public void updateVipDish(Double dishprice);

    @Update("update dish set dishstock=#{dishstock} where dishname=#{dishname}")
    public void updateDishdishStock(Integer dishstock,String dishname);

    @Select(" select dishstock from dish where dishname=#{dishname}")
    public Integer selectDishdishStockByName(String dishname);

    @Select(" select dishprice from dish where dishtype=0")
    public Double selectVipDishPrice();

    @Select(" select * from dish where dishstock>0 and dishtype!=0 and dishstatus!=1")
    public List<Dish> selectAllDish();

    @Select(" select * from dish where dishtype!=0")
    public List<Dish> selectAllAllDish();

    @Select(" select count(*) from dish where dishtype!=0")
    public Integer getDishNum();

    @Delete("delete from dish where dishid=#{dishid} and dishtype!=0")
    public void deleteDishById(Integer dishid);

    @Delete("delete from dish where dishtype=#{dishtype} and dishtype!=0")
    public void deleteDishByDishTypeId(Integer dishtype);

    @Select(" select * from dish where dishtype=#{dishtype} and dishtype!=0")
    public List<Dish> selectAllDishByDishType(Integer dishtype);

    @Select(" select * from dish where dishname like #{dishname} and dishtype!=0 and dishstatus!=1")
    public List<Dish> selectAllDishLikeDishNmae(String dishname);

    @Select(" select * from dish where dishname=#{dishname} and dishtype!=0")
    public List<Dish> selectAllDishByDishNmae(String dishname);

    @Select(" select * from dish where dishid like #{dishid} and dishtype!=0")
    public List<Dish> selectAllDishLikeDishId(String dishid);

    @Select(" select * from dish where dishid=#{dishid} and dishtype!=0")
    public Dish selectDishByDishId(Integer dishid);

}
