package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.goodimgs;
import ssm.service.GoodsImgService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goodimgs")
public class GoodImgsController extends BaseController<goodimgs> {

    @Autowired
    GoodsImgService goodsImgService;

    @ResponseBody
    @RequestMapping("getimgs")
    public Map getImgsWithID(int goodid) {
        System.out.println("return...");
        List<String> list = goodsImgService.getimgs(goodid);
        System.out.println("返回结果条数：" + list.size());
        if (list.size() > 0) {
            return goodsImgService.successRespMap(respMap, "success", list);
        } else {
            return goodsImgService.errorRespMap(respMap, "error");
        }
    }

    @ResponseBody
    @RequestMapping("getone")
    public Map getOne(int goodid) {
        goodimgs goodimgs = goodsImgService.get(goodid);
        if (goodimgs != null) {
            return goodsImgService.successRespMap(respMap, "success", goodimgs);
        } else {
            return goodsImgService.errorRespMap(respMap, "error");
        }
    }


    @ResponseBody
    @RequestMapping("addimgs")
    public Map addimgs(goodimgs goodimg) {

        if (goodimg.getGoodid() == 0 || ("" + goodimg.getGoodid()).equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.save(goodimg);
            return goodsImgService.successRespMap(respMap, "success", goodimg.getUrl());
        }
    }

    @ResponseBody
    @RequestMapping("updateimgs")
    public Map updateimgs(goodimgs goodimg) {

        if (goodimg.getGoodid() == 0 || ("" + goodimg.getGoodid()).equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.update(goodimg);
            return goodsImgService.successRespMap(respMap, "success", goodimg.getUrl());
        }
    }

    @ResponseBody
    @RequestMapping("deleteimgs")
    public Map deleteimgs(int goodid) {

        if (goodid == 0 || ("" + goodid).equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.delete(goodid);
            return goodsImgService.successRespMap(respMap, "success", goodid);
        }
    }


}
