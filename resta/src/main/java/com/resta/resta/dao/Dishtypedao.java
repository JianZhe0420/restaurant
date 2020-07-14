package com.resta.resta.dao;

import com.resta.resta.pojo.Dishtype;
import com.resta.resta.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Dishtypedao {

    @Select(" select * from dishtype")
    public List<Dishtype> selectAllDishtype();

    @Select(" select * from dishtype where dishtypename=#{dishtypename}")
    public List<Dishtype> selectDishtypeByDishTypeName(String dishtypename);

    @Delete("delete from dishtype where dishtypeid=#{dishtypeid}")
    public void deleteDishTypeById(Integer dishtypeid);

    @Insert(" insert into dishtype ( dishtypename ) values (#{dishtypename}) ")
    public void add(String dishtypename);
}
