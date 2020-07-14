package com.resta.resta.controller;

import com.resta.resta.dao.*;
import com.resta.resta.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class Cashcontroller {

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

    @RequestMapping("/selectDishByType")
    public String selectDishByType(String id, Map map){
        List<Dish> typedishlist= dishdao.selectAllDishByDishType(Integer.valueOf(id));
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        if (canusetablelist.size()==0){
            Table table=new Table();
            table.setTableid(0);
            table.setTablemsg(1);
            canusetablelist.add(table);
        }
        map.put("tablenum",canusetablelist.get(0).getTableid());
        map.put("typedishlist",typedishlist);
        return "showdishFrame";
    }
    @RequestMapping("/addvip")
    public String addvip(Map map,String userid){
        map.put("userid",userid);
        map.put("vipp",dishdao.selectVipDishPrice());
        return "addvipinfoFrame";
    }

    @RequestMapping("/addvipinfo")
    public String addvipinfo(Map map,String vipname,String moneytype,String userid,Integer viptel){
        System.out.println("禁/////////////////////////////////////");
        Vip vip=new Vip();
        vip.setVipname(vipname);
        vip.setViptel(viptel);
        vipdao.addVip(vip);
//        获取会员价格
        Double price=dishdao.selectVipDishPrice();
//        存会员大订单
        Order order=new Order();
        order.setOrderprice(price);
        Date date = new Date();
        order.setOrdertime(date);
        order.setOrderstatus("确认完成");
        order.setOrdertype("会员");
        order.setOrderaddress(" ");
        order.setOrderuser(Integer.parseInt(userid));
        order.setOrderoffname(" ");
        order.setOrdermoneytype(moneytype);
        order.setOrdertablenum(0);
        orderdao.addOrder(order);
//        获取大订单id
        Integer suborderorderid=orderdao.selectThisId();
//        存会员小订单
        Suborder suborder=new Suborder();
        suborder.setSuborderdish("会员");
        suborder.setOrderid(suborderorderid);
        suborder.setSuborderstatus("确认完成");
        suborder.setSubordermoney(price);
        suborder.setOrdercount(1);
        suborder.setSubordermoneytype(moneytype);
        suborder.setSubordertime(date);
        suborder.setSubordertype("会员");
        suborderdao.addSubOrder(suborder);
//
        List<Dish> typedishlist= dishdao.selectAllDish();
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        if (canusetablelist.size()==0){
            Table table=new Table();
            table.setTableid(0);
            table.setTablemsg(1);
            canusetablelist.add(table);
        }
        map.put("tablenum",canusetablelist.get(0).getTableid());
        map.put("typedishlist",typedishlist);
        return "showdishFrame";
    }

    @RequestMapping("/tablenuminfo")
    public String tablenuminfo(String tablenum,Map map){
        List<Table> tablelist=tabledao.selectAllTable();
        map.put("tablelist",tablelist);
        int tableid=Integer.parseInt(tablenum);
        map.put("tableid",tableid);
        return "tablenumFrame";
    }
    @ResponseBody
    @RequestMapping("/notablenuminfo")
    public String notablenuminfo(){
        return "已满客，请等待清桌！";
    }
    @RequestMapping("/changetablenum")
    public String changetablenum(Map map){
        List<Table> tablelist=tabledao.selectAllTable();
        List<String> list=new ArrayList<>();
        map.put("tablelist",tablelist);
        return "changetablenumFrame";
    }
    @RequestMapping("/changetablenumback")
    public String changetablenumback(Map map,Integer[] inlist){
        List list= Arrays.asList(inlist);
        for (Object changetableid:list){
            int id=Integer.parseInt(changetableid.toString());
            Table table=new Table();
            table.setTableid(id);
            table.setTablemsg(1);
            tabledao.update(table);
        }
        List<Dish> typedishlist= dishdao.selectAllDish();
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        if (canusetablelist.size()==0){
            Table table=new Table();
            table.setTableid(0);
            table.setTablemsg(1);
            canusetablelist.add(table);
        }
        map.put("tablenum",canusetablelist.get(0).getTableid());
        map.put("typedishlist",typedishlist);
        return "showdishFrame";
    }
    @RequestMapping("/selectaSomeDish")
    public String selectaSomeDish(String search,Map map){
        String searchnow="%"+search+"%";
        List<Dish> somedishlist= dishdao.selectAllDishLikeDishNmae(searchnow);
        map.put("somedishlist",somedishlist);
        return "somedishFrame";
    }

    @RequestMapping("/selectAllDish")
    public String selectAllDish(Map map){
        List<Dish> typedishlist= dishdao.selectAllDish();
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        if (canusetablelist.size()==0){
            Table table=new Table();
            table.setTableid(0);
            table.setTablemsg(1);
            canusetablelist.add(table);
        }
        map.put("tablenum",canusetablelist.get(0).getTableid());
        map.put("typedishlist",typedishlist);
        return "showdishFrame";
    }

    @RequestMapping("/vipajax")
    public @ResponseBody
    String dishajax(@RequestBody String vipnameortel){
        Vip vip;
        try {
            vip=vipdao.getVipByName(vipnameortel);
            Integer viptel=Integer.parseInt(vipnameortel);
            if (vipdao.getVipByTel(viptel).size()!=0){
                return "in";
            }
            else {
                return "notin";
            }

        } catch (NumberFormatException e) {
            return "notin";
        }
    }


    @RequestMapping("/viptelajax")
    public @ResponseBody
    String viptelajax(@RequestBody String viptel){
        Vip vip;
        try {
            if (vipdao.getVipByTel(Integer.parseInt(viptel)).size()!=0){
                return "in";
            }
            else {
                return "notin";
            }

        } catch (NumberFormatException e) {
            return "notin";
        }
    }

    @RequestMapping("/cangetablenewnumajax")
    public @ResponseBody
    String cangetablenewnumajax(){
        System.out.println("测试运行++++");
        try {
            List<Table> canusetablelist=tabledao.selectAllCanUseTable();
            if (canusetablelist.size()==0){
                System.out.println("测试运行++++-----kong");
//                Table table=new Table();
//                table.setTableid(0);
//                table.setTablemsg(1);
//                canusetablelist.add(table);
                return "无";
            }
            else {
                return canusetablelist.get(0).getTableid().toString();
            }
        } catch (NumberFormatException e) {
            return "无";
        }
    }

    @RequestMapping("/cashhome")
    public String cashhome(Map<String,Object> map){
        Off vipoff=offdao.getVipOff();
        Integer offnum=offdao.getCashAllOffWithOffStatusIsOpenNum();
        List<Off> offList=offdao.getCashAllOffWithOffStatusIsOpen();
        List<Dishtype> typelist= dishtypedao.selectAllDishtype();
        List<Dish> typedishlist= dishdao.selectAllDish();
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        map.put("vipoff",vipoff);
        map.put("offnum",offnum);
        map.put("offList1",offList);
        System.out.println("拿到了+++++++++++++++++"+offList);
        if (canusetablelist.size()==0){
            Table table=new Table();
            table.setTableid(0);
            table.setTablemsg(1);
            canusetablelist.add(table);
        }
        map.put("typelist",typelist);
        map.put("typedishlist",typedishlist);
        map.put("canusetable",canusetablelist.get(0));
        stepdao.updateAdmin0stepByOrderId();
        return "cashhome";
    }

    @RequestMapping("/cheakchefstepajax")
    public @ResponseBody String cheakchefstepajax(@RequestBody String xxxx){
        if (stepdao.selectStep().getChefstep()==1){
            return "成功";
        }
        else {return "失败";}
    }

    @RequestMapping("/cheakadminstepajax")
    public @ResponseBody String cheakadminstepajax(@RequestBody String xxxx){
        if (stepdao.selectStep().getAdminstep()==1){
            return "成功";
        }
        else {return "失败";}
    }

    @RequestMapping("/suborderisokcash")
    public @ResponseBody String suborderisokcash(@RequestBody String vvvv){
        suborderdao.updateRightBySubOrderId(Integer.parseInt(vvvv));
        return "小订单完成确认";
    }
    @RequestMapping("/suborderisnocash")
    public @ResponseBody String suborderisnocash(@RequestBody String vvvv){
        suborderdao.updateNoRightBySubOrderId(Integer.parseInt(vvvv));
        return "小订单出错确认";
    }
    @RequestMapping("/orderisokcash")
    public @ResponseBody String orderisokcash(@RequestBody String vvvv){
        orderdao.updateRightByOrderId(Integer.parseInt(vvvv));
        Order order=orderdao.selectOrderByOrderId(Integer.parseInt(vvvv));
        tabledao.change1Table(order.getOrderid());
        return "大订单完成确认";
    }
    @RequestMapping("/intheordernow")
    public String intheordernow(Map map){
        List<Order> orderlist=orderdao.selectAllInOrderWithoutVip();
        List<Suborder> suborderlist=suborderdao.selectAllSuborderWithoutVip();
        map.put("orderlist",orderlist);
        System.out.println("进入1245679"+orderlist);
        map.put("suborderlist",suborderlist);
        stepdao.updateChef0stepByOrderId();
        return "intheordernow";
    }
    @RequestMapping("/orderonsubmit")
    public String orderonsubmit(Map map,String orderprice,String orderaddress,String ordertype,String orderuser,
                                String orderoffname,String ordermoneytype,String ordertable,
                                @RequestParam("suborderdish") List<String> suborderdish,
                                @RequestParam("subordermoney") List<String> subordermoney){
        System.out.println("订单");
//        存大订单
        Order order=new Order();
        order.setOrderprice(Double.parseDouble(orderprice));
        Date date = new Date();
        order.setOrdertime(date);
        order.setOrderstatus("未完成");
        order.setOrdertype(ordertype);
        order.setOrderaddress(orderaddress);
        order.setOrderuser(Integer.parseInt(orderuser));
        order.setOrderoffname(orderoffname);
        order.setOrdermoneytype(ordermoneytype);
        System.out.println("测试堂食**********************"+(ordertype.equals("堂食")));
        if (ordertype.equals("堂食")){
            System.out.println("测试堂食**********************是");
            Table table=new Table();
            table.setTableid(Integer.parseInt(ordertable));
            table.setTablemsg(2);
            order.setOrdertablenum(Integer.parseInt(ordertable));
            tabledao.update(table);
            //        改变桌案号
            tabledao.change2Table(Integer.parseInt(ordertable));
        }
        orderdao.addOrder(order);
//        获得大订单id
        Integer suborderorderid=orderdao.selectThisId();
//        循环小订单
        for (int i=0;i<suborderdish.size();i++){
            Suborder suborder=new Suborder();
            suborder.setSuborderdish(suborderdish.get(i));
            suborder.setOrderid(suborderorderid);
            suborder.setSuborderstatus("未完成");
            suborder.setSubordermoney(Double.parseDouble(subordermoney.get(i)));
            suborder.setOrdercount(suborderdish.size());
            suborder.setSubordermoneytype(ordermoneytype);
            suborder.setSubordertime(date);
            suborder.setSubordertype(ordertype);
            if (Double.parseDouble(subordermoney.get(i))!=0.00){
                dishdao.updateDishdishStock(dishdao.selectDishdishStockByName(suborderdish.get(i))-1,suborderdish.get(i));
            }
            suborderdao.addSubOrder(suborder);
        }
//        改变cash步伐
        stepdao.updateCash1stepByOrderId();
//
//        刷新
//
        Off vipoff=offdao.getVipOff();
        Integer offnum=offdao.getCashAllOffWithOffStatusIsOpenNum();
        List<Off> offList=offdao.getCashAllOffWithOffStatusIsOpen();
        List<Dishtype> typelist= dishtypedao.selectAllDishtype();
        List<Dish> typedishlist= dishdao.selectAllDish();
        List<Table> canusetablelist=tabledao.selectAllCanUseTable();
        map.put("userid",orderuser);
        map.put("vipoff",vipoff);
        map.put("offnum",offnum);
        map.put("offList1",offList);
        System.out.println("拿到了+++++++++++++++++"+offList);
        if (canusetablelist.size()==0){
            Table table1=new Table();
            table1.setTableid(0);
            table1.setTablemsg(1);
            canusetablelist.add(table1);
        }
        map.put("typelist",typelist);
        map.put("typedishlist",typedishlist);
        map.put("canusetable",canusetablelist.get(0));
        stepdao.updateAdmin0stepByOrderId();
        return "cashhome";
    }
}
