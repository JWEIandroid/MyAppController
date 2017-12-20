package ssm.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;
//
//    @RequestMapping("userlist")
//    public ModelAndView listCategory(){
//        ModelAndView mav = new ModelAndView();
//        cs= userService.list();
//
//        // 放入转发参数
//        mav.addObject("cs", listAllUser());
//        // 放入jsp路径
//        mav.setViewName("userlist");
//        return mav;
//    }

    @ResponseBody
    @RequestMapping("userlist")
    public String getAllUser(){

        List<User> cs = userService.list();
        return JSONObject.toJSON(cs).toString();

    }


}
