package ssm.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends  BaseController<User>{

    @Autowired
    UserService userService;
//


    @ResponseBody
    @RequestMapping("userlist")
    public String getAllUser() {
        List<User> cs = userService.list();
        if (cs == null) {
            return "error";
        }
        return JSONObject.toJSON(cs).toString();
    }


    @ResponseBody
    @RequestMapping("login")
    public Map login(String name, String password, Model model) {

        User user = userService.getUser(name,password);

        if (user==null){
            System.out.println("null");
            return userService.errorRespMap(respMap,"100");
        }else {
            return userService.successRespMap(respMap,"200","success");
//            User user = userService.getUser(name, password);
//            return JSONObject.toJSON(user).toString();
        }
//        userService.login(name,password);
//        model.addAttribute("msg", "登录成功");
    }


}
