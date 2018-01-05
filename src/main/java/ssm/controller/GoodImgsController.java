package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 根据商品id返回商品图片
     *
     * @param goodid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getImgByGoodid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getImgsWithID(int goodid) {
        List<String> list = goodsImgService.getImgByGoodid(goodid);
        System.out.println("返回结果条数：" + list.size());
        if (list.size() > 0) {
            return goodsImgService.successRespMap(respMap, "success", list);
        } else {
            return goodsImgService.errorRespMap(respMap, "error");
        }
    }


    @ResponseBody
    @RequestMapping(value = "getone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getOne(int goodid) {
        goodimgs goodimgs = goodsImgService.get(goodid);
        if (goodimgs != null) {
            return goodsImgService.successRespMap(respMap, "success", goodimgs);
        } else {
            return goodsImgService.errorRespMap(respMap, "error");
        }
    }


    /**
     * 为商品添加商品图片
     *
     * @param goodimg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map addimgs(goodimgs goodimg) {

        if (goodimg.getGoodid() == 0 || goodimg.getUrl() == null || goodimg.getUrl().equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.save(goodimg);
            return goodsImgService.successRespMap(respMap, "success", goodimg.getUrl());
        }
    }


    /**
     * 更新商品
     *
     * @param goodimg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updateimgs(goodimgs goodimg) {

        if (goodimg.getGoodid() == 0 || ("" + goodimg.getGoodid()).equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.update(goodimg);
            return goodsImgService.successRespMap(respMap, "success", goodimg.getUrl());
        }
    }


    /**
     * 删除商品
     *
     * @param goodid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deleteimgs(int goodid) {

        if (goodid == 0 || ("" + goodid).equals("")) {
            return goodsImgService.errorRespMap(respMap, "params not illegal");
        } else {
            goodsImgService.delete(goodid);
            return goodsImgService.successRespMap(respMap, "success", goodid);
        }
    }


}
