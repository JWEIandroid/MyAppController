package ssm.controller;

import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
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
@RequestMapping("/goods")
public class GoodController extends BaseController<goods> {

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsImgService goodsImgService;
    @Autowired
    UserService userService;
    @Autowired
    ShouHuoMsgService shouHuoMsgService;
    @Autowired
    ReportRecordService reportRecordService;
    @Autowired
    SaleRecordService saleRecordService;
    @Autowired
    BuyRecordService buyRecordService;

    /**
     * 查询所有商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/result_goods", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getAllUser(@Param("pagenum") int pagenum) {

        PageHelper.startPage(pagenum, 10);
        List<goods> cs = goodsService.list();
        PageInfo<goods> pageInfo = new PageInfo<goods>(cs);
        System.out.println("总页数：" + pageInfo.getTotal());

        for (goods good : cs) {

            //如果商品没有图片，将显示默认图片
            List<String> goods_imgurl = goodsImgService.getImgByGoodid(good.getId());
            if (goods_imgurl  == null || goods_imgurl.size() < 1) {
                goods_imgurl = new ArrayList<String>();
                goods_imgurl.add("file/download/?filename=normal.png&type=0");
                good.setImgurl(goods_imgurl);
            } else {
                good.setImgurl(goods_imgurl);
            }

            good.setUser(userService.getuserById(good.getUserid()));
        }


        if (cs == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", cs);
        }
    }


    /**
     * 根据用户id获取用户发布的全部商品
     *
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getwithUserid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getGoodsWithUserID(int userid) {
        List<goods> goods = goodsService.getgoodsByUserid(userid);
        if (goods == null || goods.size() <= 0) {
            return goodsService.errorRespMap(respMap, "null");
        } else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }


    /**
     * 根据商品id获取商品
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getwithgoodid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getGoodsWithID(int id) {
        goods goods = goodsService.get(id);
        if (goods == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }


    /**
     * 根据ID删除商品
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delwithid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deleteWithID(int id) {

        goods good1 = goodsService.getgoodsByGoodId(id);
        if (good1 == null) {
            return goodsService.errorRespMap(respMap, "goods not exist in db");
        }
        goodsService.delete(id);
        return goodsService.successRespMap(respMap, "success", good1);
    }

    /**
     * 更新商品
     *
     * @param good
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updategood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updategoods(goods good) {

        goods good1 = goodsService.getgoodsByGoodId(good.getId());
        if (good1 == null) {
            return goodsService.errorRespMap(respMap, "goods not exist in db");
        }
        goodsService.update(good);
        return goodsService.successRespMap(respMap, "success", good);

    }


    /**
     * 添加商品
     *
     * @param good
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addgoods", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map save(goods good) {

        if (!checkParams(good)) {
            return goodsService.errorRespMap(respMap, "params not illegal");
        }

        //查询数据库是否存在商品,不存在则插入数据
        goods goods = goodsService.getbyname(good.getName());
        if (goods != null) {
            return goodsService.errorRespMap(respMap, "this good name has exist in db");
        }
        goodsService.save(good);


        String time = System.currentTimeMillis() + "";

        //添加一条发布记录
        goods good_save = goodsService.getbyname(good.getName());
        if (good_save == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            //更新商品时间
            good_save.setCreate_time(time);
            good_save.setUpdate_time(time);
            goodsService.update(good_save);

            //更新用户发布记录
            reportrecord reportrecord1 = new reportrecord();
            reportrecord1.setDate(time);
            reportrecord1.setGoodsid(good_save.getId());
            reportrecord1.setUserid(good_save.getUserid());
            reportrecord1.setStatus("在库");
            reportRecordService.save(reportrecord1);
            return goodsService.successRespMap(respMap, "success", good_save);
        }
    }

    /**
     * （模糊查询）查询商品
     *
     * @param good
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "getgoodbyname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getgoodByname(@Param("pagenum") int pagenum, goods good) {
        if (!checkParams(good)) {
            return goodsService.errorRespMap(respMap, "params not illegal");
        }
        PageHelper.startPage(pagenum, 10);
        List<goods> goods = goodsService.getgoodlikename(good.getName());
        for (goods good1 : goods) {
            List<String> list = goodsImgService.getImgByGoodid(good1.getId());
            good1.setImgurl(goodsImgService.getImgByGoodid(good1.getId()));
            good1.setUser(userService.getuserById(good1.getUserid()));
        }
        return goodsService.successRespMap(respMap, "success", goods);


    }


    /***
     * 查询商品(分类查询)
     * @param pagenum
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchbytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map GetGoodWithType(@Param("pagenum") int pagenum, @Param("type") String type) {

        PageHelper.startPage(pagenum, 10);
        List<goods> list = new ArrayList<goods>();
        if (type != null && !type.equals("")) {
            list = goodsService.getgoodwithtype(type);
        }
        if (list == null & list.size() <= 0) {
            return goodsService.errorRespMap(respMap, "数据不存在");
        }

        for (goods good : list) {
            good.setImgurl(goodsImgService.getImgByGoodid(good.getId()));
            good.setUser(userService.getuserById(good.getUserid()));
        }

        return goodsService.successRespMap(respMap, "存在" + list.size() + "条数据", list);

    }

    /**
     * 购买商品
     *
     * @param goods
     * @param purchaser 购买者
     * @return
     */
    @ResponseBody
    @RequestMapping("buy")
    public Map BuyGoods(goods goods, shouhuomsg msg, @Param("purchaser") int purchaser) {

        goods good_query = goodsService.getgoodsByGoodId(goods.getId());
        //确认商品存在
        if (good_query == null) {
            return goodsService.errorRespMap(respMap, "商品不存在");
        }
        //确认用户存在
        User business = userService.getuserById(good_query.getUserid());
        if (business == null) {
            return userService.errorRespMap(respMap, "用户不存在");
        }
        //确认商品在库
        String IsSale = good_query.getStatus();
        if (IsSale.equals("售出")) {
            return goodsService.errorRespMap(respMap, "商品已经卖出");
        }

        String time = System.currentTimeMillis() + "";

        //保存收货信息
        shouhuomsg shuohuomsg1 = new shouhuomsg();
        shuohuomsg1.setGoodsid(good_query.getId());
        shuohuomsg1.setUserid(business.getId());
        shuohuomsg1.setReceiver(msg.getReceiver());
        shuohuomsg1.setAdress(msg.getAdress());
        shuohuomsg1.setTel(msg.getTel());
        shuohuomsg1.setDate(time);
        shouHuoMsgService.save(shuohuomsg1);

        //商品持有者添加一条售出记录
        shouhuomsg shouhuomsg = shouHuoMsgService.query(good_query.getId(), business.getId(), time);
        salerecord sale_record = new salerecord();
        sale_record.setDate(System.currentTimeMillis() + "");
        sale_record.setUserid(business.getId());
        sale_record.setUser_sale_id(purchaser);
        sale_record.setGoodsid(good_query.getId());
        sale_record.setShouhuomsgid(shouhuomsg.getId());
        sale_record.setDate(time);
        saleRecordService.save(sale_record);

        //商品持有者更新发布商品状态 ; 售出
//        reportRecordService.deletewith2id(business.getId(), good_query.getId());
        reportrecord reportrecord1 = reportRecordService.query(business.getId(), good_query.getId());
        if (reportrecord1 == null) {
            return reportRecordService.errorRespMap(respMap, "查询不到这条发布信息");
        }
        reportrecord1.setStatus("售出"); //更新发布状态为：售出
        reportRecordService.update(reportrecord1);
        good_query.setStatus("售出");  //更新商品信息
        goodsService.update(good_query);

        //购买者添加一条购买记录
        User pruchaser = userService.getuserById(purchaser);
        Buyrecord buyrecord = new Buyrecord();
        buyrecord.setDate(System.currentTimeMillis() + "");
        buyrecord.setShouhuomsgid(shouhuomsg.getId());
        buyrecord.setUserid(purchaser);
        buyrecord.setGoodsid(good_query.getId());
        buyRecordService.save(buyrecord);

        return goodsService.successRespMap(respMap, "购买成功", "success");

    }


    private boolean checkParams(goods good) {

        boolean res = true;
        String name = good.getName();
        String type = good.getType();
        float price_before = good.getPrice_before();
        float price_sale = good.getPrice_sale();
        String status = good.getStatus();
        String description = good.getDescription();
        int express = good.getExpress();

        if (name == null || name.equals("")) {
            return res = false;
        }
        return res;

    }

}
