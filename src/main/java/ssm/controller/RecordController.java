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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/record")
public class RecordController extends BaseController<Buyrecord> {

    @Autowired
    BuyRecordService buyRecordService;
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
                    User user1  = userService.getuserById(reportrecord.getUserid());
                    goods goods = goodsService.getgoodsByGoodId(reportrecord.getGoodsid());
                    goods.setUser(user1);
                    goods.setImgurl(goodsImgService.getImgByGoodid(reportrecord.getGoodsid()));
                    if (goods != null & user1 != null ) {
                        reportrecord.setUser(user1);
                        reportrecord.setGoods(goods);
                    }
                    RecordResponse recordResponse1 = new RecordResponse();
                    recordResponse1.setReportrecord(reportrecord);
                    recordResponseList.add(recordResponse1);
                }
                return reportRecordService.successRespMap(respMap, "有" + list_reported.size() + "条数据", recordResponseList);
            case 3:
                PageHelper.startPage(pagenum,7);
                List<ForkRecord> list_fork = forkRecordService.selectByUserid(userid);
                if (list_fork.size() <= 0) {
                    recordResponseList.add(new RecordResponse());
                    return forkRecordService.successRespMap(respMap, "没有数据", recordResponseList);
                }

                for (ForkRecord forkRecord:list_fork){
                    RecordResponse recordResponse1 = new RecordResponse();
                    User user_fork = userService.getuserById(forkRecord.getUserid());
                    goods goods_fork = goodsService.getgoodsByGoodId(forkRecord.getGoodsid());
                    goods_fork.setUser(user_fork);
                    goods_fork.setImgurl(goodsImgService.getImgByGoodid(goods_fork.getId()));
                    if (user_fork!=null &&goods_fork!=null){
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




    //收藏记录的操作

    /**
     * 添加一条收藏记录
     * @param forkRecord
     * @return
     */
    public Map insertOneForkRecord(ForkRecord forkRecord){

        User user = userService.getuserById(forkRecord.getUserid());
        goods goods = goodsService.getgoodsByGoodId(forkRecord.getGoodsid());
        if (user==null||goods==null){
            return goodsService.errorRespMap(respMap,"insert failed");
        }
        forkRecordService.save(forkRecord);
        return forkRecordService.successRespMap(respMap,"insert success","");
    }


}
