package ssm.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    UserService userService;
//


    @ResponseBody
    @RequestMapping("userlist")
    public Map getAllUser() {
        List<User> cs = userService.list();
        if (cs == null) {
            return userService.errorRespMap(respMap,"error");
        }else{
            return userService.successRespMap(respMap,"success",cs);
        }
    }

    //根据用户名密码登录
    @ResponseBody
    @RequestMapping("login")
    public Map login(String name, String password) {

        User user = userService.getUser(name, password);

        if (user == null) {
            System.out.println("null");
            return userService.errorRespMap(respMap, "100");
        } else {
            return userService.successRespMap(respMap, "200", "success");
//            User user = userService.getUser(name, password);
//            return JSONObject.toJSON(user).toString();
        }
    }

    //根据用户名密码登录
    @ResponseBody
    @RequestMapping("register")
    public Map addUser(User user) {

        if (user == null) {
            System.out.println("controller:null");
            return userService.errorRespMap(respMap, "100");
        } else {
            userService.register(user);
            return userService.successRespMap(respMap, "200", "success");
        }
    }


}
