package com.resta.resta.dao;

import com.resta.resta.pojo.Order;
import com.resta.resta.pojo.Step;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Stepdao {

    @Select(" select * from step where stepid=0")
    public Step selectStep();

    @Update("update step set cashstep=1 where stepid=0 ")
    public void updateCash1stepByOrderId();

    @Update("update step set cashstep=0 where stepid=0 ")
    public void updateCash0stepByOrderId();

    @Update("update step set chefstep=1 where stepid=0 ")
    public void updateChef1stepByOrderId();

    @Update("update step set chefstep=0 where stepid=0 ")
    public void updateChef0stepByOrderId();

    @Update("update step set adminstep=1 where stepid=0 ")
    public void updateAdmin1stepByOrderId();

    @Update("update step set adminstep=0 where stepid=0 ")
    public void updateAdmin0stepByOrderId();
}
