package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.goods;
import ssm.service.GoodsService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/goods")
public class GoodController extends BaseController<goods>{

    @Autowired
    GoodsService goodsService;

    @ResponseBody
    @RequestMapping("result_goods")
    public Map getAllUser(){
        List<goods> cs = goodsService.list();
        if (cs == null) {
            return goodsService.errorRespMap(respMap, "error");
        } else {
            return goodsService.successRespMap(respMap, "success", cs);
        }
    }






}
