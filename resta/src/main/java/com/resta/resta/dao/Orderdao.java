package com.resta.resta.dao;

import com.resta.resta.pojo.Order;
import com.resta.resta.pojo.Shift;
import com.resta.resta.pojo.Suborder;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface Orderdao {

    @Insert(" insert into order1 ( orderprice,ordertime,orderstatus,ordertype,orderaddress,orderuser,orderoffname,ordermoneytype,ordertablenum ) values (#{orderprice},#{ordertime},#{orderstatus},#{ordertype},#{orderaddress},#{orderuser},#{orderoffname},#{ordermoneytype},#{ordertablenum}) ")
    public void addOrder(Order order);

    @Select(" select * from order1")
    public List<Order> selectAllOrder();

    @Select(" select sum(orderprice) from order1 where ordermoneytype='现金' ")
    public Double selectAllOrderXian();

    @Select(" select sum(orderprice) from order1 where ordermoneytype='微信' ")
    public Double selectAllOrderWei();

    @Select(" select sum(orderprice) from order1 where ordermoneytype='支付宝' ")
    public Double selectAllOrderZhi();

    @Select("select * from order1 where ordertime between #{start} and #{end}")
    public List<Order> selectOrderByTIme(Date start, Date end);

    @Select("select sum(orderprice) from order1 where (ordertime between #{start} and #{end}) and orderstatus='确认完成' and ordermoneytype='现金'")
    public Double selectCXianOrderByTIme(Date start, Date end);

    @Select("select sum(orderprice) from order1 where (ordertime between #{start} and #{end}) and orderstatus='确认完成' and ordermoneytype='微信'")
    public Double selectCWeiOrderByTIme(Date start, Date end);

    @Select("select sum(orderprice) from order1 where (ordertime between #{start} and #{end}) and orderstatus='确认完成' and ordermoneytype='支付宝'")
    public Double selectCZhiOrderByTIme(Date start, Date end);

    @Select(" select * from order1 where orderid=#{orderid}")
    public Order selectOrderByOrderId(Integer orderid);

    @Select(" select sum(orderprice) from order1 where orderuser=#{orderuser} and orderstatus='确认完成' and ordermoneytype='现金'")
    public Double selectAllOrderCXianByUserId(Integer orderuser);

    @Select(" select sum(orderprice) from order1 where orderuser=#{orderuser} and orderstatus='确认完成' and ordermoneytype='微信'")
    public Double selectAllOrderCWeiByUserId(Integer orderuser);

    @Select(" select sum(orderprice) from order1 where orderuser=#{orderuser} and orderstatus='确认完成' and ordermoneytype='支付宝'")
    public Double selectAllOrderCZhiByUserId(Integer orderuser);

    @Select(" select * from order1 where orderuser=#{orderuser}")
    public List<Order> selectAllOrderByUserId(Integer orderuser);

    @Select(" SELECT orderid FROM order1 ORDER BY orderid DESC LIMIT 0,1")
    public Integer selectThisId();

    @Select(" select count(*) from order1")
    public Integer selectAllOrderNum();

    @Select(" select * from order1 where ordertype!='会员' and orderstatus='未完成'")
    public List<Order> selectAllOutOrderWithoutVip();

    @Select(" select * from order1 where ordertype!='会员' and (orderstatus='完成' or orderstatus='未完成')")
    public List<Order> selectAllInOrderWithoutVip();

    @Delete("delete from order1 where orderid = #{orderid}")
    public void deleteByOrderId(Integer orderid);

    @Update("update order1 set orderstatus='完成' where orderid=#{orderid} ")
    public void updateOkByOrderId(Integer orderid);

    @Update("update order1 set orderstatus='出错' where orderid=#{orderid} ")
    public void updateNoByOrderId(Integer orderid);

    @Update("update order1 set orderstatus='确认完成' where orderid=#{orderid} ")
    public void updateRightByOrderId(Integer  orderid);

}
