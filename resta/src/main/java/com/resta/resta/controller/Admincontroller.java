package com.resta.resta.controller;

import com.resta.resta.dao.*;
import com.resta.resta.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class Admincontroller {

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

    @RequestMapping("/adminhome")
    public String adminhome(){
        return "adminhome";
    }

    @RequestMapping("/useradminFrame")
    public String useradminFrame(Map map){
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "useradminFrame";
    }
    @RequestMapping("/dishadminFrame")
    public String dishadminFrame(Map map){
        List<Dish> dishlist=dishdao.selectAllAllDish();
        Integer dishnum=dishdao.getDishNum();
        List<Dishtype> dishtypeList=dishtypedao.selectAllDishtype();
        System.out.println(dishtypeList);
        map.put("dishtypelist",dishtypeList);
        map.put("dishlist",dishlist);
        map.put("dishnum",dishnum);
        return "dishadminFrame";
    }
    @RequestMapping("/dishtypeadminFrame")
    public String dishtypeadminFrame(Map map){
        List<Dishtype> dishtypelist=dishtypedao.selectAllDishtype();
        map.put("dishtypelist",dishtypelist);
        return "dishtypeadminFrame";
    }
    @RequestMapping("/offadminFrame")
    public String offadminFrame(Map map){
        List<Off> off0list=offdao.selectAll0Off();
        List<Off> off1list=offdao.selectAll1Off();
        Integer offnum=offdao.selectOffNum();
        map.put("off0list",off0list);
        map.put("off1list",off1list);
        map.put("offnum",offnum);
        return "offadminFrame";
    }
    @RequestMapping("/vipadminFrame")
    public String vipadminFrame(Map map){
        List<Vip> viplist=vipdao.selectAllVip();
        Integer vipnum=vipdao.selectAllVipNum();
        Double vipprice=dishdao.selectVipDishPrice();
        map.put("vipprice",vipprice);
        map.put("viplist",viplist);
        map.put("vipnum",vipnum);
        return "vipadminFrame";
    }
    @RequestMapping("/orderorderadminFrame")
    public String orderorderadminFrame(){
        return "orderorderadminFrame";
    }
    @RequestMapping("/subordersaleadminFrame")
    public String subordersaleadminFrame(String starttime,String endtime,String dishname,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        starttime = starttime+" 00:00:00";
        endtime = endtime +" 00:00:00";
        Date dateStart = sdf.parse(starttime);
        Date dateEnd = sdf.parse(endtime);
        List<Suborder> suborderlist=suborderdao.selectSuborderByTImeAndName(dateStart,dateEnd,dishname);
        for (Suborder su:suborderlist){
            su.setSubordertimestring(sdf.format(su.getSubordertime()));
        }
        List<User> userlist=userdao.selectAllUser();
        map.put("num",suborderdao.selectSuborderNumByTImeAndName(dateStart,dateEnd,dishname));
        map.put("userlist",userlist);
        map.put("suborderlist",suborderlist);
        return "subordersaleadminFrame";
    }
    @RequestMapping("/ordertimeadminFrame")
    public String ordertimeadminFrame(String starttime,String endtime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        starttime = starttime+" 00:00:00";
        endtime = endtime +" 00:00:00";
        Date dateStart = sdf.parse(starttime);
        Date dateEnd = sdf.parse(endtime);
        List<Order> orderlist=orderdao.selectOrderByTIme(dateStart,dateEnd);
        for (Order ord:orderlist){
            ord.setOrdertimestring(sdf.format(ord.getOrdertime()));
        }
        List<User> userlist=userdao.selectAllUser();
        Double Cxiannum=orderdao.selectCXianOrderByTIme(dateStart,dateEnd) ;
        if (Cxiannum==null){
            Cxiannum=0.00;
        }
        map.put("Cxiannum",Cxiannum);
        Double Cweinum=orderdao.selectCWeiOrderByTIme(dateStart,dateEnd) ;
        if (Cweinum==null){
            Cweinum=0.00;
        }
        map.put("Cweinum",Cweinum);
        Double Czhinum=orderdao.selectCZhiOrderByTIme(dateStart,dateEnd) ;
        if (Czhinum==null){
            Czhinum=0.00;
        }
        map.put("Czhinum",Czhinum);
        Double Cnum= Cxiannum+Cweinum+Czhinum;
        map.put("Cnum",Cnum);
        Double xiannum=suborderdao.selectXianSuborderByTIme(dateStart,dateEnd) ;
        if (xiannum==null){
            xiannum=0.00;
        }
        map.put("xiannum",xiannum);
        Double weinum=suborderdao.selectWeiSuborderByTIme(dateStart,dateEnd) ;
        if (weinum==null){
            weinum=0.00;
        }
        map.put("weinum",weinum);
        Double zhinum=suborderdao.selectZhiSuborderByTIme(dateStart,dateEnd) ;
        if (zhinum==null){
            zhinum=0.00;
        }
        map.put("zhinum",zhinum);
        Double num= xiannum+weinum+zhinum;
        System.out.println("num"+num);
        map.put("num",num);
        map.put("subxiannum",Cxiannum-xiannum);
        map.put("subweinum",Cweinum-weinum);
        map.put("subzhinum",Czhinum-zhinum);
        map.put("subnum",Cnum-num);
        System.out.println("Cxiannum"+Cxiannum+"Cweinum"+Cweinum+"Czhinum"+Czhinum+"xiannum"+xiannum+"weinum"+weinum+"zhinum"+zhinum+"Cnum"+Cnum+"num"+num);
        map.put("userlist",userlist);
        map.put("orderlist",orderlist);
        return "ordertimeadminFrame";
    }
    @RequestMapping("/orderbyuseradminFrame")
    public String orderbyuseradminFrame(String username,Map map){
        User user=userdao.getUserByName(username);
        List<Order> orderlist=orderdao.selectAllOrderByUserId(user.getUserid());
        for (Order ord:orderlist){
            ord.setOrdertimestring(sdf.format(ord.getOrdertime()));
        }
        Double Cxiannum=orderdao.selectAllOrderCXianByUserId(user.getUserid());
        if (Cxiannum==null){
            Cxiannum=0.00;
        }
        map.put("Cxiannum",Cxiannum);
        Double Cweinum=orderdao.selectAllOrderCWeiByUserId(user.getUserid());
        if (Cweinum==null){
            Cweinum=0.00;
        }
        map.put("Cweinum",Cweinum);
        Double Czhinum=orderdao.selectAllOrderCZhiByUserId(user.getUserid());
        if (Czhinum==null){
            Czhinum=0.00;
        }
        map.put("Czhinum",Czhinum);
        Double Cnum=Cxiannum+Cweinum+Czhinum;
        map.put("Cnum",Cnum);

        List<Order> fororderlist=orderdao.selectAllOrderByUserId(user.getUserid());

        Double xiannum=0.00;
        for (Order or:fororderlist){
            if (suborderdao.selectXianByUser(or.getOrderid())==null){
                xiannum=xiannum+0.00;
            }
            else {
                xiannum=xiannum+suborderdao.selectXianByUser(or.getOrderid());
            }
        }
        map.put("xiannum",xiannum);

        Double weinum=0.00;
        for (Order or:fororderlist){
            if (suborderdao.selectWeiByUser(or.getOrderid())==null){
                weinum=weinum+0.00;
            }
            else {
                weinum=weinum+suborderdao.selectWeiByUser(or.getOrderid());
            }
        }
        map.put("weinum",weinum);

        Double zhinum=0.00;
        for (Order or:fororderlist){
            if (suborderdao.selectZhiByUser(or.getOrderid())==null){
                zhinum=zhinum+0.00;
            }
            else {
                zhinum=zhinum+suborderdao.selectZhiByUser(or.getOrderid());
            }
        }
        map.put("zhinum",zhinum);

        Double num= xiannum+weinum+zhinum;
        map.put("num",num);
        map.put("subxiannum",Cxiannum-xiannum);
        map.put("subweinum",Cweinum-weinum);
        map.put("subzhinum",Czhinum-zhinum);
        map.put("subnum",Cnum-num);

        map.put("orderlist",orderlist);
        return "orderbyuseradminFrame";
    }
    @RequestMapping("/orderadminFrame")
    public String orderadminFrame(Map map){
        List<Order> orderlist=orderdao.selectAllOrder();
        for (Order ord:orderlist){
            ord.setOrdertimestring(sdf.format(ord.getOrdertime()));
        }
        Integer ordernum=orderdao.selectAllOrderNum();
        List<Suborder> suborderlist=suborderdao.selectAllAllSuborder();
        List<User> userlist=userdao.selectAllUser();
        map.put("suborderlist",suborderlist);
        map.put("orderlist",orderlist);
        map.put("ordernum",ordernum);
        map.put("userlist",userlist);
        return "orderadminFrame";
    }
    @RequestMapping("/shiftadminFrame")
    public String shiftadminFrame(Map map){
        List<Shift> shiftlist=shiftdao.selectAllShift();
        Integer shiftnum=shiftdao.selectAllShiftNum();
        List<User> userlist=userdao.selectAllUser();
        for (Shift shi:shiftlist){
            shi.setShifttimebeginstring(sdf.format(shi.getShifttimebegin()));
        }
        map.put("userlist",userlist);
        map.put("shiftlist",shiftlist);
        map.put("shiftnum",shiftnum);
        return "shiftadminFrame";
    }
    @RequestMapping("/shiftshiftadminFrame")
    public String shiftshiftadminFrame(){
        return "shiftshiftadminFrame";
    }
    @RequestMapping("/shiftbytimeadminFrame")
    public String shiftbytimeadminFrame(Map map,String starttime,String endtime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        starttime = starttime+" 00:00:00";
        endtime = endtime +" 00:00:00";
        Date dateStart = sdf.parse(starttime);
        Date dateEnd = sdf.parse(endtime);
        List<Shift> shiftlist=shiftdao.selectShiftByTIme(dateStart,dateEnd);
        List<User> userlist=userdao.selectAllUser();

        for (Shift sh:shiftlist){
            sh.setShifttimebeginstring(sdf.format(sh.getShifttimebegin()));
        }
        map.put("userlist",userlist);
        map.put("shiftlist",shiftlist);
        return "shiftbytimeadminFrame";
    }
    @RequestMapping("/shiftbyusernameadminFrame")
    public String shiftbyusernameadminFrame(Map map,String username){
        User user=userdao.getUserByName(username);
        List<Shift> shiftlist=shiftdao.selectShiftByuserid(user.getUserid());
        for (Shift sh:shiftlist){
            sh.setShifttimebeginstring(sdf.format(sh.getShifttimebegin()));
        }
        map.put("shiftlist",shiftlist);
        return "shiftbyusernameadminFrame";
    }
    @RequestMapping("/tableadminFrame")
    public String tableadminFrame(Map map){
        List<Table> tabkelist=tabledao.selectAllTable();
        Integer tablenum=tabledao.selectAllTableNum();
        map.put("tabkelist",tabkelist);
        map.put("tablenum",tablenum);
        return "tableadminFrame";
    }

    @RequestMapping("/deluseradminajax")
    public @ResponseBody
    String deluseradminajax(@RequestBody String uid){
        int id=Integer.parseInt(uid);
        userdao.deleteUserById(id);
        shiftdao.deleteShiftByuserid(id);
        return "删除成功";
    }
    @RequestMapping("/deldishadminajax")
    public @ResponseBody String deldishadminajax(@RequestBody String did){
        int id=Integer.parseInt(did);
        dishdao.deleteDishById(id);
        stepdao.updateAdmin1stepByOrderId();
        return "删除成功";
    }
    @RequestMapping("/deldishtypeadminajax")
    public @ResponseBody String deldishtypeadminajax(@RequestBody String dtid){
        int id=Integer.parseInt(dtid);
        dishdao.deleteDishByDishTypeId(id);
        dishtypedao.deleteDishTypeById(id);
        stepdao.updateAdmin1stepByOrderId();
        return "删除成功";
    }
    @RequestMapping("/deloffadminajax")
    public @ResponseBody String deloffadminajax(@RequestBody String oid){
        int id=Integer.parseInt(oid);
        offdao.deleteOffById(id);
        stepdao.updateAdmin1stepByOrderId();
        return "删除成功";
    }
    @RequestMapping("/adddishtypeadminajax")
    public @ResponseBody String adddishtypeadminajax(@RequestBody String name){
        if (dishtypedao.selectDishtypeByDishTypeName(name).size()==0){
            dishtypedao.add(name);
            stepdao.updateAdmin1stepByOrderId();
            return "新增成功";
        }
        return "失败";
    }
    @RequestMapping("/deltableadminajax")
    public @ResponseBody String deltableadminajax(@RequestBody String tid){
        tabledao.deleteTableById(tabledao.deleteLastTable());
        stepdao.updateAdmin1stepByOrderId();
        return "删除成功";
    }
    @RequestMapping("/addtableadminajax")
    public @ResponseBody String addtableadminajax(@RequestBody String tablemsg){
        System.out.println("测试2222222222222222");
        System.out.println("测试2222222222222222"+tabledao.deleteLastTable()+1);
        Table table=new Table();
        table.setTableid(tabledao.deleteLastTable()+1);
        table.setTablemsg(1);
        System.out.println("测试2222222222222222"+table);
        tabledao.addTable(table);
        stepdao.updateAdmin1stepByOrderId();
        return "删除成功";
    }
    @RequestMapping("/adduseradminFrame")
    public String adduseradminFrame(){
        return "adduseradminFrame";
    }

    @RequestMapping("/addgiftoffadminFrame")
    public String addgiftoffadminFrame(){
        return "addgiftoffadminFrame";
    }

    @RequestMapping("/addreductionoffadminFrame")
    public String addreductionoffadminFrame(){
        return "addreductionoffadminFrame";
    }

    @RequestMapping("/adddiscountoffadminFrame")
    public String adddiscountoffadminFrame(){
        return "adddiscountoffadminFrame";
    }

    @RequestMapping("/adddishadminFrame")
    public String adddishadminFrame(Map map){
        List<Dish> dishlist=dishdao.selectAllAllDish();
        List<Dishtype> dishtypelist=dishtypedao.selectAllDishtype();
        Integer dishnum=dishdao.getDishNum();
        map.put("dishlist",dishlist);
        map.put("dishtypelist",dishtypelist);
        map.put("dishnum",dishnum);
        return "adddishadminFrame";
    }
    @RequestMapping("/changeuseradminFrame")
    public String changeuseradminFrame(String userid,Map map){
        User user=userdao.getUserById(Integer.parseInt(userid));
        map.put("user",user);
        return "changeuseradminFrame";
    }
    @RequestMapping("/changeadminuseradminFrame")
    public String changeadminuseradminFrame(String userid,Map map){
        User user=userdao.getUserById(Integer.parseInt(userid));
        map.put("user",user);
        return "changeadminuseradminFrame";
    }
    @RequestMapping("/addadminajax")
    public @ResponseBody
    String addadminajax(@RequestBody String adminname){
        if (userdao.getUserByName(adminname)==null){
            return "yes";
        }
        else {
            return "no";
        }
    }
    @RequestMapping("/addadmin")
    public String addadmin(String adminname,String adminpass,String admintel,Map map){
        User user=new User();
        user.setUsertype(3);
        user.setUsertele(admintel);
        user.setUsername(adminname);
        user.setUserpassword(adminpass);
        userdao.add(user);
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "superadminFrame";
    }
    @RequestMapping("/changedishadminFrame")
    public String changedishadminFrame(String dishid,Map map){
        Dish dish=dishdao.selectDishByDishId(Integer.parseInt(dishid));
        List<Dishtype> dishtypelist=dishtypedao.selectAllDishtype();
        map.put("dish",dish);
        map.put("dishtypelist",dishtypelist);
        return "changedishadminFrame";
    }
    @RequestMapping("/superadminFrame")
    public String superadminFrame(Map map){
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "superadminFrame";
    }
    @RequestMapping("/changesuperuseradminFrame")
    public String changesuperuseradminFrame(Map map){
        User user=userdao.getUserByType();
        map.put("superuser",user);
        return "changesuperuseradminFrame";
    }
    @RequestMapping("/changesuper")
    public String changesuper(Map map,String superuserp){
        userdao.updateSuperUser(superuserp);
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "superadminFrame";
    }
    @RequestMapping("/changevipoffajax")
    public String changevipoffajax(@RequestBody String orate,Map map){
        offdao.updateVipOff(Double.parseDouble(orate));
        List<Off> off0list=offdao.selectAll0Off();
        List<Off> off1list=offdao.selectAll1Off();
        Integer offnum=offdao.selectOffNum();
        map.put("off0list",off0list);
        map.put("off1list",off1list);
        map.put("offnum",offnum);
        stepdao.updateAdmin1stepByOrderId();
        return "offadminFrame";
    }
    @ResponseBody
    @RequestMapping("/changeoffstatusajax")
    public String changeoffstatusajax(@RequestBody String oid,Map map){
        Off off=offdao.selectOffByid(Integer.parseInt(oid));
        if (off.getOffstatus()==1){
            offdao.updateOffStatus(0,Integer.parseInt(oid));
            stepdao.updateAdmin1stepByOrderId();
        }
        else {
            offdao.updateOffStatus(1,Integer.parseInt(oid));
            stepdao.updateAdmin1stepByOrderId();
        }
        stepdao.updateAdmin1stepByOrderId();
        return "更改成功";
    }
    @ResponseBody
    @RequestMapping("/changevippriceajax")
    public String changevippriceajax(@RequestBody String vprice,Map map){
        dishdao.updateVipDish(Double.parseDouble(vprice));
        stepdao.updateAdmin1stepByOrderId();
        return "更改成功";
    }
    @ResponseBody
    @RequestMapping("/delvipajax")
    public String delvipajax(@RequestBody String vipip,Map map){
        vipdao.deleteVipByIp(Integer.parseInt(vipip));
        stepdao.updateAdmin1stepByOrderId();
        return "更改成功";
    }

    @RequestMapping("/cheakusernameajax")
    public @ResponseBody
    String cheakusernameajax(@RequestBody String username){
        try {
            if (userdao.getUserByName(username)!=null){
                return "in";
            }
            return "notin";

        } catch (NumberFormatException e) {
            return "notin";
        }
    }

    @RequestMapping("/cheakdishnameajax")
    public @ResponseBody
    String cheakdishnameajax(@RequestBody String dishname){
        try {
            if (dishdao.selectAllDishByDishNmae(dishname).size()!=0){
                return "in";
            }
            if (dishname.equals("会员")){
                return "vip";
            }
            return "notin";

        } catch (NumberFormatException e) {
            return "notin";
        }
    }

    @RequestMapping("/adduser")
    public String adduser(String username,String userpas,String usertel,String usertype,Map map){
        User user=new User();
        user.setUsername(username);
        user.setUserpassword(userpas);
        user.setUsertele(usertel);
        if (usertype.equals("前台")){
            user.setUsertype(1);
        }
        else {user.setUsertype(2);}
        userdao.add(user);
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "useradminFrame";
    }
    @RequestMapping("/adddish")
    public String adddish(String dishname,String dishtype,String dishstock,String dishprice,String dishstatus,Map map){
        Dish dish=new Dish();
        dish.setDishname(dishname);
        dish.setDishprice(Double.parseDouble(dishprice));
        if (dishstatus.equals("上架")){
            dish.setDishstatus(0);
            stepdao.updateAdmin1stepByOrderId();
        }
        else {dish.setDishstatus(1);}
        dish.setDishtype(Integer.parseInt(dishtype));
        dish.setDishstock(Integer.parseInt(dishstock));
        dishdao.addDish(dish);
        List<Dish> dishlist=dishdao.selectAllAllDish();
        List<Dishtype> dishtypelist=dishtypedao.selectAllDishtype();
        Integer dishnum=dishdao.getDishNum();
        map.put("dishlist",dishlist);
        System.out.println(dishtypelist);
        map.put("dishtypelist",dishtypelist);
        map.put("dishnum",dishnum);
        stepdao.updateAdmin1stepByOrderId();
        return "dishadminFrame";
    }
    @RequestMapping("/adddiscount")
    public String adddiscount(Map map,String offname,String offrate,String offmax,String offstatus){
        Off off=new Off();
        off.setOffname(offname);
        off.setOffType(2);
        off.setOffmax(Double.parseDouble(offmax));
        off.setOofsub(0.00);
        off.setOffrate(Double.parseDouble(offrate));
        off.setOffstatus(Integer.parseInt(offstatus));
        offdao.addOff(off);
        List<Off> off0list=offdao.selectAll0Off();
        List<Off> off1list=offdao.selectAll1Off();
        Integer offnum=offdao.selectOffNum();
        map.put("off0list",off0list);
        map.put("off1list",off1list);
        map.put("offnum",offnum);
        stepdao.updateAdmin1stepByOrderId();
        return "offadminFrame";
    }
    @RequestMapping("/addgift")
    public String addgift(Map map,String offname,String offmax,String offstatus){
        Off off=new Off();
        off.setOffname(offname);
        off.setOffType(0);
        off.setOffmax(Double.parseDouble(offmax));
        off.setOofsub(0.00);
        off.setOffrate(1.00);
        off.setOffstatus(Integer.parseInt(offstatus));
        offdao.addOff(off);
        List<Off> off0list=offdao.selectAll0Off();
        List<Off> off1list=offdao.selectAll1Off();
        Integer offnum=offdao.selectOffNum();
        map.put("off0list",off0list);
        map.put("off1list",off1list);
        map.put("offnum",offnum);
        stepdao.updateAdmin1stepByOrderId();
        return "offadminFrame";
    }
    @RequestMapping("/addreduction")
    public String addreduction(Map map,String offname,String offmax,String offsub,String offstatus){
        Off off=new Off();
        off.setOffname(offname);
        off.setOffType(1);
        off.setOffmax(Double.parseDouble(offmax));
        off.setOofsub(Double.parseDouble(offsub));
        off.setOffrate(1.00);
        off.setOffstatus(Integer.parseInt(offstatus));
        offdao.addOff(off);
        List<Off> off0list=offdao.selectAll0Off();
        List<Off> off1list=offdao.selectAll1Off();
        Integer offnum=offdao.selectOffNum();
        map.put("off0list",off0list);
        map.put("off1list",off1list);
        map.put("offnum",offnum);
        stepdao.updateAdmin1stepByOrderId();
        return "offadminFrame";
    }
    @RequestMapping("/changeuser")
    public String changeuser(String userid,String username,String userpas,String usertel,String usertype,Map map){
        User user=new User();
        user.setUserid(Integer.parseInt(userid));
        user.setUsername(username);
        user.setUserpassword(userpas);
        user.setUsertele(usertel);
        if (usertype.equals("前台")){
            user.setUsertype(1);
        }
        else if(usertype.equals("后厨")) {
            user.setUsertype(2);
        }
        else {
            user.setUsertype(3);
        }
        userdao.update(user);
        List<User> userList=userdao.selectAllUser();
        Integer usernum=userdao.getUserNum();
        map.put("userList",userList);
        map.put("usernum",usernum);
        return "useradminFrame";
    }
    @RequestMapping("/changedish")
    public String changedish(String dishid,String dishname,String dishtype,String dishstock,String dishprice,String dishstatus,Map map){
        Dish dish=new Dish();
        dish.setDishid(Integer.parseInt(dishid));
        dish.setDishname(dishname);
        dish.setDishprice(Double.parseDouble(dishprice));
        if (dishstatus.equals("上架")){
            dish.setDishstatus(0);
            stepdao.updateAdmin1stepByOrderId();
        }
        else {dish.setDishstatus(1);}
        dish.setDishtype(Integer.parseInt(dishtype));
        dish.setDishstock(Integer.parseInt(dishstock));
        dishdao.update(dish);
        List<Dish> dishlist=dishdao.selectAllAllDish();
        List<Dishtype> dishtypelist=dishtypedao.selectAllDishtype();
        Integer dishnum=dishdao.getDishNum();
        map.put("dishlist",dishlist);
        map.put("dishtypelist",dishtypelist);
        map.put("dishnum",dishnum);
        stepdao.updateAdmin1stepByOrderId();
        return "dishadminFrame";
    }
}
