package com.resta.resta.controller;


import com.resta.resta.dao.*;
import com.resta.resta.pojo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Usercontroller {


    @Autowired
    Userdao userdao;

    @Autowired
    Dishtypedao dishtypedao;

    @Autowired
    Dishdao dishdao;

    @Autowired
    Tabledao tabledao;

    @Autowired
    Vipdao vipdao;

    @Autowired
    Shiftdao shiftdao;

    @Autowired
    Offdao offdao;

    @Autowired
    Orderdao orderdao;

    @Autowired
    Suborderdao suborderdao;

    @Autowired
    Stepdao stepdao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/login")
    public String one(){
        return "login";

    }

    @RequestMapping(value="/cashback")
    public String cashback(){
        return "login";
    }


    @RequestMapping("/usercheak")
    public String success(@RequestParam("username") String name, @RequestParam("password") String password,
                          Map<String,Object> map, HttpSession session){
        System.out.println("测试值"+name+password);
        if (userdao.getUserByName(name)==null){
                map.put("msg","用户名不存在");
                return "login";
        }
        User user=userdao.getUserByName(name);
        System.out.println("测试user"+user);
        if(user.getUserpassword(). equals(password)){
            if(user.getUsertype(). equals(3)){
                Shift shift=new Shift();
                Date date = new Date();
                shift.setShiftuser(user.getUserid());
                shift.setShifttimebegin(date);
                shiftdao.addShift(shift);
                return "adminhome";
            }
            else if(user.getUsertype(). equals(2)){
                List<Suborder> suborderlist=suborderdao.selectAllOutSuborderWithoutVip();
                List<Order> orderlist=orderdao.selectAllOutOrderWithoutVip();
                List<Suborder> allsuborderlist=suborderdao.selectAllSuborder();
                stepdao.updateCash0stepByOrderId();
                map.put("suborderlist",suborderlist);
                map.put("orderlist",orderlist);
                map.put("allsuborderlist",allsuborderlist);
                stepdao.updateCash0stepByOrderId();
                Shift shift=new Shift();
                Date date = new Date();
                shift.setShiftuser(user.getUserid());
                shift.setShifttimebegin(date);
                shiftdao.addShift(shift);
                return "chefhome";
            }
            else if(user.getUsertype(). equals(1)){
                Off vipoff=offdao.getVipOff();
                Integer offnum=offdao.getCashAllOffWithOffStatusIsOpenNum();
                List<Off> offList=offdao.getCashAllOffWithOffStatusIsOpen();
                List<Dishtype> typelist= dishtypedao.selectAllDishtype();
                List<Dish> typedishlist= dishdao.selectAllDish();
                List<Table> canusetablelist=tabledao.selectAllCanUseTable();
                if (canusetablelist.size()==0){
                    Table table=new Table();
                    table.setTableid(0);
                    table.setTablemsg(1);
                    canusetablelist.add(table);
                }
                map.put("userid",user.getUserid());
                map.put("vipoff",vipoff);
                map.put("offnum",offnum);
                map.put("offList1",offList);
                map.put("typelist",typelist);
                map.put("typedishlist",typedishlist);
                map.put("canusetable",canusetablelist.get(0));
                Shift shift=new Shift();
                Date date = new Date();
                shift.setShiftuser(user.getUserid());
                shift.setShifttimebegin(date);
                shiftdao.addShift(shift);
                stepdao.updateAdmin0stepByOrderId();
                return "cashhome";

            }
            else if(user.getUsertype(). equals(4)){
                Shift shift=new Shift();
                Date date = new Date();
                shift.setShiftuser(user.getUserid());
                shift.setShifttimebegin(date);
                shiftdao.addShift(shift);
                return "superadminhome";
            }
            else{
                map.put("msg","无此类型用户");
                return "login";

            }


        }
        else{

            map.put("msg","密码错误");
            return "login";
        }
    }



}
