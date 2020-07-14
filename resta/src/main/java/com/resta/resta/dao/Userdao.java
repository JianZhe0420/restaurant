package com.resta.resta.dao;


import com.resta.resta.pojo.User;
import com.resta.resta.pojo.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Userdao {

    @Select("select * from user where userid=#{userid}")
    public User getUserById(Integer userid);

    @Select("select * from user where usertype=4")
    public User getUserByType();

    @Delete("delete from user where userid=#{userid}")
    public void deleteUserById(Integer userid);

    @Select(" select * from user where usertype!=1")
    public List<User> selectAllUserWithoutAdmin();

    @Select(" select * from user")
    public List<User> selectAllUser();

    @Select(" select count(*) from user")
    public Integer getUserNum();

    @Insert(" insert into user ( username,userpassword,usertype,usertele ) values (#{username},#{userpassword},#{usertype},#{usertele}) ")
    public void add(User user);

    @Update("update user set username=#{username},userpassword=#{userpassword},usertype=#{usertype},usertele=#{usertele} where userid=#{userid} ")
    public void update(User user);

    @Select("select * from user where username=#{username}")
    public User getUserByName(String username);

    @Select("select * from user where username=#{username} and userpassword=#{userpassword}")
    public User getUserByNameAP(User user);

    @Update("update user set userpassword=#{userpassword} where usertype=4")
    public void updateSuperUser(String userpassword);


}
