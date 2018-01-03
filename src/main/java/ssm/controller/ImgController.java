package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.User;
import ssm.model.goods;
import ssm.model.imgs;
import ssm.service.GoodsService;
import ssm.service.ImgsService;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userimg/")
public class ImgController extends BaseController<imgs> {

    @Autowired
    ImgsService imgsService;
    @Autowired
    UserService userService;

    //查询全部用户头像
    @ResponseBody
    @RequestMapping(value = "result_imgs", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getAllUser() {
        List<imgs> cs = imgsService.list();
        if (cs == null) {
            return imgsService.errorRespMap(respMap, "error");
        } else {
            return imgsService.successRespMap(respMap, "success", cs);
        }

    }

    //根据用户ID查询用户头像
    @ResponseBody
    @RequestMapping(value = "getimgbyid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getGoodsWithID(int userid) {

        imgs img = imgsService.get(userid);
        if (img == null) {
            return imgsService.errorRespMap(respMap, "error");
        } else {
            return imgsService.successRespMap(respMap, "success", img);
        }

    }

    //添加用户头像
    @ResponseBody
    @RequestMapping(value = "addimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map save(imgs img) {

        if (!checkParams(img)) {
            return imgsService.errorRespMap(respMap, "params not illegal");
        }

        User user = userService.getuserById(img.getUserid());
        if (user != null) {
            return userService.errorRespMap(respMap, "this user has existed in db");
        }

        imgsService.save(img);
        return imgsService.successRespMap(respMap, "success", "update success");

    }


    //更新用户头像
    @ResponseBody
    @RequestMapping(value = "updateimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map update(imgs img) {

        if (!checkParams(img)) {
            return userService.errorRespMap(respMap, "params not illegal");
        }
        User user = userService.getuserById(img.getUserid());
        if (user == null) {
            return userService.errorRespMap(respMap, "this user doed not has exist in db");
        }
        imgsService.update(img);
        return imgsService.successRespMap(respMap, "success", "update success");

    }

    //删除用户头像
    @ResponseBody
    @RequestMapping(value = "resetimgs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map delete(imgs img) {

        if (!checkParams(img)) {
            return imgsService.errorRespMap(respMap, "params not illegal");
        }
        User user = userService.getuserById(img.getUserid());
        if (user == null) {
            return userService.errorRespMap(respMap, "this user doed not has exist in db");
        }
        imgsService.delete(img.getUserid());
        return imgsService.successRespMap(respMap, "success", "update success");

    }



    private boolean checkParams(imgs i) {

        boolean res = true;
        int userid = i.getUserid();
        String img = i.getImg();
        String type = i.getType();

        if (img == null || img.equals("")) {
            return res = false;
        }
        if (userid == 0) {
            return res = false;
        }
        if (type == null || type.equals("")) {
            return res = false;
        }
        return res;

    }

}

