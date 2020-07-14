package com.resta.resta;

import com.resta.resta.dao.*;
import com.resta.resta.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class RestaApplicationTests {


    @Autowired
    DataSource dataSource;
    @Autowired
    Userdao userdao;
    @Autowired
    Dishdao dishdao;

    @Autowired
    Dishtypedao dishtypedao;
    @Autowired
    Tabledao tabledao;

    @Autowired
    Offdao offdao;

    @Autowired
    Suborderdao suborderdao;

    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }


    @Test
    void test()throws SQLException {
List<Suborder> asd=suborderdao.selectAllSuborder();
//        suborderdao.updateBySuborderId(1);
//        user.setUsername("saf*******");
//        user.setUserpassword("123");
//        user.setUsertype(3);
//        user.setUsertele("123456");
//        userdao.add(user);
     System.out.println("测试一下1234678911"+asd);

    }

    @Test
    void test3()throws SQLException {

        System.out.println("更改小订单");
         int nownowsuborderid1=2;
        int nownoworderid=suborderdao.selectThisOrderId(nownowsuborderid1);
        List<Suborder> suborderlist=suborderdao.selectAllThisOrderidSuborder(nownoworderid);
        for (Suborder sub:suborderlist){
            suborderdao.updateCount((sub.getOrdercount()-1),sub.getSuborderid());
        }
        System.out.println(nownowsuborderid1);
        suborderdao.updateOkBySuborderId(nownowsuborderid1);

    }



    @Test
    void test1()throws SQLException {

        dishdao.deleteDishByDishTypeId(1);
        System.out.println("测试类型11111111111");
        dishtypedao.deleteDishTypeById(1);
        System.out.println("测试类型"+dishtypedao.selectAllDishtype());

    }

    @Test
    void test2()throws SQLException {

//        List<Table> canusetablelist=tabledao.selectAllCanUseTable();

        System.out.println("测试ajax");
        int i=0;
//        user.setUsername("saf*******");
//        user.setUserpassword("123");
//        user.setUsertype(3);
//        user.setUsertele("123456");
//        userdao.add(user);
//        for(Table table:canusetablelist) {
//            System.out.println("测试类型123"+table);
//
//        }
        Table table=new Table();
        table.setTableid(1);
        table.setTablemsg(1);
        tabledao.update(table);
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        System.out.println("测试ajax"+canusetablelist);



    }

}
