package ssm.controller;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.*;
import ssm.service.*;
import sun.rmi.runtime.Log;

import java.text.NumberFormat;
import java.util.*;


@Controller
@RequestMapping("/record")
public class RecordController extends BaseController<Buyrecord> {

    @Autowired
    BuyRecordService buyRecordService;/**/
    @Autowired
    ReportRecordService reportRecordService;
    @Autowired
    SaleRecordService saleRecordService;

    @Autowired
    GoodsImgService goodsImgService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ShouHuoMsgService shouHuoMsgService;
    @Autowired
    ForkRecordService forkRecordService;


    /**
     * 获取用户全部购买记录
     *
     * @param userid 用户id
     * @param type   查询记录种类   0：购买记录  1：卖出记录  2：发布记录 3：收藏记录
     * @return 记录
     */
    @ResponseBody
    @RequestMapping("getrecords")
    public Map getRecordsWithUserid(int userid, int type, int pagenum) {

        //判断用户是否存在
        User user = userService.getuserById(userid);
        if (user == null) {
            return userService.errorRespMap(respMap, "用户不存在");
        }
        List<RecordResponse> recordResponseList = new ArrayList<RecordResponse>();

        //根据种类查询记录
        switch (type) {

            case 0:
                RecordResponse recordResponse = new RecordResponse();
                PageHelper.startPage(pagenum, 7);
                List<Buyrecord> list_buy = buyRecordService.list_user(userid);

                if (list_buy.size() < 1) {
                    recordResponseList.add(new RecordResponse());
                    return buyRecordService.successRespMap(respMap, "没有数据", recordResponseList);
                }
                for (Buyrecord buyrecord : list_buy) {
                    User user1 = userService.getuserById(buyrecord.getUserid());
                    goods goods = goodsService.getgoodsByGoodId(buyrecord.getGoodsid());
                    goods.setUser(user1);
                    goods.setImgurl(goodsImgService.getImgByGoodid(buyrecord.getGoodsid()));
                    shouhuomsg shouhuomsg = shouHuoMsgService.getbyid(buyrecord.getShouhuomsgid());
                    if (user1 != null & goods != null & shouhuomsg != null) {
                        buyrecord.setUser(user1);
                        buyrecord.setShouhuomsg(shouhuomsg);
                        buyrecord.setGoods(goods);
                    }
                    RecordResponse recordResponse1 = new RecordResponse();
                    recordResponse1.setBuyrecord(buyrecord);
                    recordResponseList.add(recordResponse1);
                }
                return buyRecordService.successRespMap(respMap, "有" + list_buy.size() + "条数据", recordResponseList);

            case 1:
                PageHelper.startPage(pagenum, 7);
                List<salerecord> list_sale = saleRecordService.list_user(userid);
                if (list_sale.size() < 1) {
                    recordResponseList.add(new RecordResponse());
                    return saleRecordService.successRespMap(respMap, "没有数据", recordResponseList);
                }

                for (salerecord salerecord : list_sale) {
                    User user_shop = userService.getuserById(salerecord.getUserid());
                    User user_sale = userService.getuserById(salerecord.getUser_sale_id());
                    shouhuomsg shuohuomsg = shouHuoMsgService.getbyid(salerecord.getShouhuomsgid());
                    goods goods = goodsService.getgoodsByGoodId(salerecord.getGoodsid());
                    goods.setUser(user_shop);
                    goods.setImgurl(goodsImgService.getImgByGoodid(salerecord.getGoodsid()));
                    if (user_shop != null & user_sale != null & shuohuomsg != null) {
                        salerecord.setUser(user_shop);
                        salerecord.setUser_sale(user_sale);
                        salerecord.setShouhuomsg(shuohuomsg);
                        salerecord.setGoods(goods);
                    }
                    RecordResponse recordResponse2 = new RecordResponse();
                    recordResponse2.setSalerecord(salerecord);
                    recordResponseList.add(recordResponse2);
                }
                return saleRecordService.successRespMap(respMap, "有" + list_sale.size() + "条数据", recordResponseList);

            case 2:
                PageHelper.startPage(pagenum, 7);
                List<reportrecord> list_reported = reportRecordService.list_user(userid);
                if (list_reported.size() <= 0) {
                    recordResponseList.add(new RecordResponse());
                    return reportRecordService.successRespMap(respMap, "没有数据", recordResponseList);
                }

                for (reportrecord reportrecord : list_reported) {
                    User user1 = userService.getuserById(reportrecord.getUserid());
                    goods goods = goodsService.getgoodsByGoodId(reportrecord.getGoodsid());
                    goods.setUser(user1);
                    goods.setImgurl(goodsImgService.getImgByGoodid(reportrecord.getGoodsid()));
                    if (goods != null & user1 != null) {
                        reportrecord.setUser(user1);
                        reportrecord.setGoods(goods);
                    }
                    RecordResponse recordResponse1 = new RecordResponse();
                    recordResponse1.setReportrecord(reportrecord);
                    recordResponseList.add(recordResponse1);
                }
                return reportRecordService.successRespMap(respMap, "有" + list_reported.size() + "条数据", recordResponseList);
            case 3:
                PageHelper.startPage(pagenum, 7);
                List<ForkRecord> list_fork = forkRecordService.selectByUserid(userid);
                if (list_fork.size() <= 0) {
                    recordResponseList.add(new RecordResponse());
                    return forkRecordService.successRespMap(respMap, "没有数据", recordResponseList);
                }

                for (ForkRecord forkRecord : list_fork) {
                    RecordResponse recordResponse1 = new RecordResponse();
                    User user_fork = userService.getuserById(forkRecord.getUserid());
                    goods goods_fork = goodsService.getgoodsByGoodId(forkRecord.getGoodsid());
                    goods_fork.setUser(user_fork);
                    goods_fork.setImgurl(goodsImgService.getImgByGoodid(goods_fork.getId()));
                    if (user_fork != null && goods_fork != null) {
                        forkRecord.setUser(user_fork);
                        forkRecord.setGoods(goods_fork);
                    }
                    recordResponse1.setForkRecord(forkRecord);
                    recordResponseList.add(recordResponse1);
                }

                return forkRecordService.successRespMap(respMap, "有" + list_fork.size() + "条数据", recordResponseList);
            default:
                return forkRecordService.errorRespMap(respMap, "Type Errors");

        }
    }


    /**
     * 删除某个用户的全部购买记录
     *
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping("delrecords")
    public Map delRecords(int userid) {

        User user = userService.getuserById(userid);
        if (user == null) {
            return buyRecordService.errorRespMap(respMap, "用户不存在");
        }

        List<Buyrecord> list = buyRecordService.list_user(userid);
        if (list.size() <= 0) {
            return buyRecordService.errorRespMap(respMap, "没有数据");
        }
        buyRecordService.delete(userid);
        return buyRecordService.successRespMap(respMap, "删除" + list.size() + "条数据", "success");

    }


    /**
     * 查询用户的一条购买记录
     *
     * @param userid
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map selectone(@Param("userid") int userid, @Param("date") String date) {


        Buyrecord buyrecord1 = buyRecordService.selectone(userid, date);

        if (buyrecord1 == null) {
            return buyRecordService.errorRespMap(respMap, "记录不存在");
        }
        return buyRecordService.successRespMap(respMap, "success", buyrecord1);


    }


    /**
     * 添加一条购买记录
     *
     * @param buyrecord
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addrecords")
    public Map addrecords(Buyrecord buyrecord) {

        Buyrecord buyrecord1 = buyRecordService.selectone(buyrecord.getUserid(), buyrecord.getDate());

        if (buyrecord1 != null) {
            return buyRecordService.errorRespMap(respMap, " 记录已经存在");
        }
        buyRecordService.save(buyrecord);
        return buyRecordService.successRespMap(respMap, "success", buyrecord);
    }


    /**
     * 添加一条收藏记录
     *
     * @param forkRecord
     * @return
     */
    public Map insertOneForkRecord(ForkRecord forkRecord) {

        User user = userService.getuserById(forkRecord.getUserid());
        goods goods = goodsService.getgoodsByGoodId(forkRecord.getGoodsid());
        if (user == null || goods == null) {
            return goodsService.errorRespMap(respMap, "insert failed");
        }
        forkRecordService.save(forkRecord);
        return forkRecordService.successRespMap(respMap, "insert success", "");
    }


    /**
     * 查询用户账单
     *
     * @param querytype 查询类别：  0：支出  1：收入
     * @param userid    用户ID
     * @return Bill 账单实体
     */
    @ResponseBody
    @RequestMapping(value = "bill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map queryBill(@Param("querytype") int querytype, @Param("userid") int userid) {

        switch (querytype) {
            case 0:
                //查询购买记录全部种类
                List<String> list_type = buyRecordService.selecttype(userid);
                if (list_type == null || list_type.size() <= 0) {
                    return buyRecordService.successRespMap(respMap, "type count is null", new ArrayList<Bill>());
                }
                //去重
                Set set = new HashSet();
                List<String> list_type_new = new ArrayList<String>();
                for (String type : list_type) {
                    if (set.add(type)) {
                        list_type_new.add(type);
                    }
                }

                List<Bill> billList = new ArrayList<Bill>();
                float pay_all = buyRecordService.selectAll(userid);
                System.out.println("用户消费总额：" + pay_all);

                for (String type : list_type_new) {
                    float pay_type_all = buyRecordService.selectpaywithtype(type, userid);
                    System.out.println(type + "类购买总额：" + pay_type_all);

                    NumberFormat numberFormat = NumberFormat.getInstance();
                    String result = numberFormat.format(pay_type_all / pay_all * 100);
                    float percent_pay = Float.parseFloat(result);
                    System.out.println(type + "类占比：" + percent_pay);
                    Bill bill = new Bill();
                    bill.setPercent(percent_pay);i
                    bill.setType(type);
                    bill.setCount(pay_type_all);
                    billList.add(bill);
                }
                return buyRecordService.successRespMap(respMap, "共" + billList.size() + "条数据", billList);
            case 1:
                //查询卖出记录全部种类
                List<String> list_type_sale = saleRecordService.selecttype(userid);
                if (list_type_sale == null || list_type_sale.size() <= 0) {
                    return saleRecordService.successRespMap(respMap, "type count is null", new ArrayList<Bill>());
                }
                //去重
                Set set_sale = new HashSet();
                List<String> list_type_sale_new = new ArrayList<String>();
                for (String type : list_type_sale) {
                    if (set_sale.add(type)) {
                        list_type_sale_new.add(type);
                    }
                }

                List<Bill> billList_sale = new ArrayList<Bill>();
                float income_all = saleRecordService.selectAll(userid);
                System.out.println("用户收入总额：" + income_all);

                for (String type : list_type_sale_new) {
                    float income_type_all = saleRecordService.selectIncomewithtype(type, userid);
                    System.out.println(type + "类收入总额：" + income_type_all);

                    NumberFormat numberFormat = NumberFormat.getInstance();
                    String result = numberFormat.format(income_type_all / income_all * 100);
                    float percent_pay = Float.parseFloat(result);
                    System.out.println(type + "类占比：" + percent_pay);
                    Bill bill = new Bill();
                    bill.setPercent(percent_pay);
                    bill.setType(type);
                    bill.setCount(income_type_all);
                    billList_sale.add(bill);
                }
                return buyRecordService.successRespMap(respMap, "共" + billList_sale.size() + "条数据", billList_sale);
            default:
                return null;
        }

    }

}
