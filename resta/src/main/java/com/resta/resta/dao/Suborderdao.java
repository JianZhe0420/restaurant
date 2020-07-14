package com.resta.resta.dao;

import com.resta.resta.pojo.Order;
import com.resta.resta.pojo.Suborder;
import com.resta.resta.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface Suborderdao {



    @Select(" select * from suborder where subordertype!='会员'")
    public List<Suborder> selectAllSuborder();

    @Select(" select * from suborder")
    public List<Suborder> selectAllAllSuborder();

    @Select(" select count(*) from suborder")
    public Integer selectAllSuborderNum();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='现金' and suborderstatus='确认完成'")
    public Double selectAllCXianSuborderMoney();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='微信' and suborderstatus='确认完成'")
    public Double selectAllCWeiSuborderNum();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='支付宝' and suborderstatus='确认完成'")
    public Double selectAllCZhiSuborderNum();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='现金' and suborderstatus='确认出错'")
    public Double selectAllXianSuborderMoney();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='微信' and suborderstatus='确认出错'")
    public Double selectAllWeiSuborderNum();

    @Select(" select sum(subordermoney) from suborder where subordermoneytype='支付宝' and suborderstatus='确认出错'")
    public Double selectAllZhiSuborderNum();

    @Select("select * from suborder where (subordertime between #{start} and #{end}) and suborderdish=#{suborderdish}")
    public List<Suborder> selectSuborderByTImeAndName(Date start, Date end,String suborderdish);

    @Select("select count(*) from suborder where (subordertime between #{start} and #{end}) and suborderdish=#{suborderdish}")
    public Integer selectSuborderNumByTImeAndName(Date start, Date end,String suborderdish);

   @Select("select sum(subordermoney) from suborder where (subordertime between #{start} and #{end}) and subordermoneytype='现金' and suborderstatus='确认出错'")
    public Double selectXianSuborderByTIme(Date start, Date end);

    @Select("select sum(subordermoney) from suborder where (subordertime between #{start} and #{end}) and subordermoneytype='微信' and suborderstatus='确认出错'")
    public Double selectWeiSuborderByTIme(Date start, Date end);

    @Select("select sum(subordermoney) from suborder where (subordertime between #{start} and #{end}) and subordermoneytype='支付宝' and suborderstatus='确认出错'")
    public Double selectZhiSuborderByTIme(Date start, Date end);

    @Select(" select orderid from suborder where suborderid=#{suborderid}")
    public Integer selectThisOrderId(Integer suborderid);

    @Select(" select sum(subordermoney) from suborder where orderid=#{orderid} and subordermoneytype='现金' and suborderstatus='确认出错'")
    public Double selectXianByUser(Integer orderid);

    @Select(" select sum(subordermoney) from suborder where orderid=#{orderid} and subordermoneytype='微信' and suborderstatus='确认出错'")
    public Double selectWeiByUser(Integer orderid);

    @Select(" select sum(subordermoney) from suborder where orderid=#{orderid} and subordermoneytype='支付宝' and suborderstatus='确认出错'")
    public Double selectZhiByUser(Integer orderid);

    @Select(" select * from suborder where orderid=#{orderid}")
    public List<Suborder> selectAllThisOrderidSuborder(Integer orderid);

    @Update("update suborder set ordercount=#{ordercount} where suborderid=#{suborderid} ")
    public void updateCount(Integer ordercount,Integer suborderid);

    @Select(" select * from suborder where subordertype!='会员'")
    public List<Suborder> selectAllSuborderWithoutVip();

    @Select(" select * from suborder where subordertype!='会员' and suborderstatus='未完成'")
    public List<Suborder> selectAllOutSuborderWithoutVip();

    @Select(" select * from suborder where subordertype!='会员' and suborderstatus='完成'")
    public List<Suborder> selectAllInSuborderWithoutVip();

    @Select(" select * from suborder where subordertype!='会员' and suborderstatus='确认完成'")
    public List<Suborder> selectAllRightSuborderWithoutVip();

    @Delete("delete from suborder where suborderid = #{suborderid}")
    public void deleteBySuborderId(Integer suborderid);

    @Update("update suborder set suborderstatus='完成' where suborderid=#{suborderid} ")
    public void updateOkBySuborderId(Integer suborderid);

    @Update("update suborder set suborderstatus='出错' where suborderid=#{suborderid} ")
    public void updateNoBySuborderId(Integer suborderid);

    @Update("update suborder set suborderstatus='完成',ordercount=0 where orderid=#{orderid} ")
    public void updateOkByOrderId(Integer orderid);

    @Update("update suborder set suborderstatus='完成',ordercount=0 where orderid=#{orderid} and suborderstatus='未完成'")
    public void updateOkOkByOrderId(Integer orderid);

    @Update("update suborder set suborderstatus='出错',ordercount=0 where orderid=#{orderid} ")
    public void updateNoByOrderId(Integer  orderid);

    @Update("update suborder set suborderstatus='确认完成' where suborderid=#{suborderid} ")
    public void updateRightBySubOrderId(Integer  suborderid);

    @Update("update suborder set suborderstatus='确认出错' where suborderid=#{suborderid} ")
    public void updateNoRightBySubOrderId(Integer  suborderid);

    @Insert(" insert into suborder ( suborderdish,orderid,suborderstatus,subordermoney,ordercount,subordermoneytype,subordertime,subordertype ) values (#{suborderdish},#{orderid},#{suborderstatus},#{subordermoney},#{ordercount},#{subordermoneytype},#{subordertime},#{subordertype}) ")
    public void addSubOrder(Suborder suborder);
}
