package ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    ShuoHuoMsgService shuoHuoMsgService;
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
            good.setImgurl(goodsImgService.getImgByGoodid(good.getId()));
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

        //添加一条发布记录
        goods good_save = goodsService.getbyname(good.getName());
        if (good_save == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            //更新商品时间
            good_save.setCreate_time(System.currentTimeMillis() + "");
            good_save.setUpdate_time(System.currentTimeMillis() + "");
            goodsService.update(good_save);

            //更新用户发布记录
            reportrecord reportrecord1 = new reportrecord();
            reportrecord1.setDate(System.currentTimeMillis() + "");
            reportrecord1.setGoodsid(good_save.getId());
            reportrecord1.setUserid(good_save.getUserid());
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
        if (goods == null || goods.size() < 1) {
            return goodsService.errorRespMap(respMap, "没有数据");
        } else {
            for (goods good1 : goods) {
                good1.setImgurl(goodsImgService.getImgByGoodid(good1.getId()));
                good1.setUser(userService.getuserById(good1.getUserid()));
            }
            return goodsService.successRespMap(respMap, "success", goods);
        }


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
    public Map BuyGoods(goods goods,shuohuomsg msg, @Param("purchaser") int purchaser) {

        //判断商品状态
        boolean ISEXIST_GOOD = goodsService.getbyname(goods.getName()) != null;

        if (!ISEXIST_GOOD) {
            return goodsService.errorRespMap(respMap, "查询不到该商品");
        }

        goods good_query = goodsService.getbyname(goods.getName());

        //商品持有者添加一条售出记录
        User business = userService.getuserById(good_query.getUserid());

        //保存收货信息
        shuohuomsg shuohuomsg1 = new shuohuomsg();
        shuohuomsg1.setGoodsid(good_query.getId());
        shuohuomsg1.setUserid(business.getId());
        shuohuomsg1.setName(msg.getName());
        shuohuomsg1.setAdress(msg.getAdress());
        shuohuomsg1.setTel(msg.getTel());
        shuoHuoMsgService.save(shuohuomsg1);


        shuohuomsg shuohuomsg = shuoHuoMsgService.query(good_query.getId(), business.getId());
        salerecord sale_record = new salerecord();
        sale_record.setDate(System.currentTimeMillis() + "");
        sale_record.setUserid(business.getId());
        sale_record.setUser_sale_id(purchaser);
        sale_record.setGoodsid(good_query.getId());
        sale_record.setShouhuomsg(shuohuomsg.getId());
        saleRecordService.save(sale_record);

        //商品持有者删除发布记录,商品状态改变
        reportRecordService.deletewith2id(good_query.getId(), business.getId());
        good_query.setStatus("售出");
        goodsService.update(good_query);

        //购买者添加一条购买记录
        User pruchaser = userService.getuserById(purchaser);

        Buyrecord buyrecord = new Buyrecord();
        buyrecord.setDate(System.currentTimeMillis() + "");
        buyrecord.setShuohuomsg(shuohuomsg.getId());
        buyrecord.setUserid(purchaser);
        buyrecord.setGoodsid(goods.getId());
        buyRecordService.save(buyrecord);

        return goodsService.successRespMap(respMap,"购买成功","success");

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
