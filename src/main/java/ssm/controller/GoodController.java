package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.goods;
import ssm.service.GoodsService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/goods")
public class GoodController extends BaseController<goods> {

    @Autowired
    GoodsService goodsService;

    @ResponseBody
    @RequestMapping(value = "result_goods",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Map getAllUser() {
        List<goods> cs = goodsService.list();
        if (cs == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", cs);
        }
    }

    @ResponseBody
    @RequestMapping(value = "getwithid",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map getGoodsWithID(int id) {
        goods goods = goodsService.get(id);
        if (goods == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }

    @ResponseBody
    @RequestMapping(value = "delwithid",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map deleteWithID(int id) {
        goodsService.delete(id);
        goods goods = goodsService.get(id);
        if (goods != null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }

    @ResponseBody
    @RequestMapping(value = "updategood",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map updategoods(goods good) {

        if (!checkParams(good)) {
            return goodsService.errorRespMap(respMap, "params not illegal");
        }
        goodsService.update(good);
        return goodsService.successRespMap(respMap, "success", good);
    }


    @ResponseBody
    @RequestMapping(value = "addgoods",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
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
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }


    @ResponseBody
    @RequestMapping(value = "getgoodbyname",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map getgoodByname(goods good) {
        if (!checkParams(good)) {
            return goodsService.errorRespMap(respMap, "params not illegal");
        }
        List<goods> goods = goodsService.getgoodlikename(good.getName());
        if (goods.size()<=0) {
            return goodsService.errorRespMap(respMap, "this good isn't exist in db");
        }else {
            return goodsService.successRespMap(respMap, "success", goods);
        }
    }


    private boolean checkParams(goods good) {

        boolean res = true;
        String name = good.getName();
        String type = good.getType();
        float price_before = good.getPrice_before();
        float price_sale = good.getPrice_sale();
        boolean status = good.getStatus();
        String description = good.getDescription();
        int express = good.getExpress();

        if (name==null || name.equals("")){
            return  res =false;
        }
        return res;

    }

}
