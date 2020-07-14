package com.resta.resta.controller;


import com.resta.resta.dao.*;
import com.resta.resta.pojo.Order;
import com.resta.resta.pojo.Step;
import com.resta.resta.pojo.Suborder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class Chefcontroller {

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
    @RequestMapping("/chefhome")
    public String chefhome(Map map){
        System.out.println("后厨");
        List<Suborder> suborderlist=suborderdao.selectAllOutSuborderWithoutVip();
        System.out.println("管理"+suborderlist);
        List<Order> orderlist=orderdao.selectAllOutOrderWithoutVip();
        List<Suborder> allsuborderlist=suborderdao.selectAllSuborder();
        stepdao.updateCash0stepByOrderId();
        System.out.println("管理123"+suborderlist);
        System.out.println("管理"+suborderlist);
        map.put("suborderlist",suborderlist);
        map.put("orderlist",orderlist);
        map.put("allsuborderlist",allsuborderlist);
        stepdao.updateCash0stepByOrderId();
        return "chefhome";
    }

    @RequestMapping("/suborderchildFrame")
    public String suborderchildFrame(Map map){
        List<Suborder> suborderlist=suborderdao.selectAllOutSuborderWithoutVip();
        List<Order> orderlist=orderdao.selectAllOutOrderWithoutVip();
        List<Suborder> allsuborderlist=suborderdao.selectAllSuborder();
        map.put("suborderlist",suborderlist);
        map.put("orderlist",orderlist);
        map.put("allsuborderlist",allsuborderlist);
        return "suborderchildFrame";
    }
    @RequestMapping("/bigokorderajax")
    public @ResponseBody
    String bigokorderajax(@RequestBody String nownowsuborderid){
        int nownowsuborderid1=Integer.parseInt(nownowsuborderid);
        suborderdao.updateOkOkByOrderId(nownowsuborderid1);
        orderdao.updateOkByOrderId(nownowsuborderid1);
        stepdao.updateChef1stepByOrderId();
        stepdao.updateChef1stepByOrderId();
        return "bigokorder更新成功";
    }
    @RequestMapping("/bignoorderajax")
    public @ResponseBody String bignoorderajax(@RequestBody String nownowsuborderid){
        int nownowsuborderid1=Integer.parseInt(nownowsuborderid);
        suborderdao.updateNoByOrderId(nownowsuborderid1);
        orderdao.updateNoByOrderId(nownowsuborderid1);
        stepdao.updateChef1stepByOrderId();
        stepdao.updateCash1stepByOrderId();
        return "bignoorder更新成功";
    }
    @RequestMapping("/oksuborderajax")
    public @ResponseBody String oksuborderajax(@RequestBody String nownowsuborderid){
        int nownowsuborderid1=Integer.parseInt(nownowsuborderid);
        int nownoworderid=suborderdao.selectThisOrderId(nownowsuborderid1);
        List<Suborder> suborderlist=suborderdao.selectAllThisOrderidSuborder(nownoworderid);
        for (Suborder sub:suborderlist){
            suborderdao.updateCount((sub.getOrdercount()-1),sub.getSuborderid());
        }
        suborderdao.updateOkBySuborderId(nownowsuborderid1);
        stepdao.updateChef1stepByOrderId();
        stepdao.updateChef1stepByOrderId();
        return "sub更新成功";
    }
    @RequestMapping("/nosuborderajax")
    public @ResponseBody String nosuborderajax(@RequestBody String nownowsuborderid){
        int nownowsuborderid1=Integer.parseInt(nownowsuborderid);
        int nownoworderid=suborderdao.selectThisOrderId(nownowsuborderid1);
        List<Suborder> suborderlist=suborderdao.selectAllThisOrderidSuborder(nownoworderid);
        for (Suborder sub:suborderlist){
            suborderdao.updateCount((sub.getOrdercount()-1),sub.getSuborderid());
        }
        suborderdao.updateNoBySuborderId(nownowsuborderid1);
        return "sub更新成功";
    }
    @RequestMapping("/okorderajax")
    public @ResponseBody String okorderajax(@RequestBody String nownoworderid){
        System.out.println(nownoworderid);
        Integer nownoworderid1=Integer.parseInt(nownoworderid);
        orderdao.updateOkByOrderId(nownoworderid1);
        stepdao.updateChef1stepByOrderId();
        return "or更新成功";
    }
    @RequestMapping("/noorderajax")
    public @ResponseBody String noorderajax(@RequestBody String nownoworderid){
        Integer nownoworderid1=Integer.parseInt(nownoworderid);
        orderdao.updateNoByOrderId(nownoworderid1);
        stepdao.updateChef1stepByOrderId();
        return "or更新成功";
    }

    @ResponseBody
    @RequestMapping("/cheakcashstepajax")
    public String cheakcashstepajax(@RequestBody String xxxx){
        Step step=stepdao.selectStep();
        if (step.getCashstep()==1){
            return "成功";
        }
        else{
            return "失败";
        }
    }
}
