package ssm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.goods;
import ssm.service.GoodsImgService;
import ssm.service.GoodsService;
import ssm.service.UserService;

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
     * 根据用户id获取商品
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
        goods goods = goodsService.getbyname(good.getName());
        if (goods != null) {
            return goodsService.errorRespMap(respMap, "this good name has exist in db");
        }
        goodsService.save(good);
        goods good_save = goodsService.getbyname(good.getName());
        if (good_save == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {

            //更新商品时间
            good_save.setCreate_time(System.currentTimeMillis()+"");
            good_save.setUpdate_time(System.currentTimeMillis() + "");
            goodsService.update(good_save);


            //更新用户发布记录



            return goodsService.successRespMap(respMap, "success", good_save);
        }
    }

    /**
     * 根据商品名字获取商品
     *
     * @param good
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "getgoodbyname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getgoodByname(goods good) {
        if (!checkParams(good)) {
            return goodsService.errorRespMap(respMap, "params not illegal");
        }
        List<goods> goods = goodsService.getgoodlikename(good.getName());
        if (goods.size() <= 0) {
            return goodsService.errorRespMap(respMap, "this good isn't exist in db");
        } else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
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
