package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    //获取全部用户
    @ResponseBody
    @RequestMapping("userlist")
    public Map getAllUser() {
        List<User> cs = userService.list();
        if (cs == null) {
            return userService.errorRespMap(respMap, "error");
        } else {
            return userService.successRespMap(respMap, "success", cs);
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

//    @ResponseBody
//    @RequestMapping("/register")
//    public Map addUser(@ModelAttribute  User user) {
//
//        userService.register(user);
//
//        if (user == null) {
//            System.out.println("controller:null");
//            return userService.errorRespMap(respMap, "100");
//        } else {
//            return userService.successRespMap(respMap, "200", "success");
//        }
//    }


    @ResponseBody
    @RequestMapping("/register")
    public Map save(User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println("插入结果为...");
        User user1 = userService.getUser(user.getName(), user.getPassword());
        if (user1 == null) {
            userService.save(user);
            User user2 = userService.getUser(user.getName(), user.getPassword());

            if (user2 != null) {
                System.out.println("success");
                return userService.successRespMap(respMap, "添加用户成功", user.getName());
            } else {
                System.out.println("添加失败--fail");
                return userService.errorRespMap(respMap, "添加用户失败");
            }

        } else {
            System.out.println("用户存在--fail");
            return userService.errorRespMap(respMap, "用户已经存在");
        }
    }

    @ResponseBody
    @RequestMapping("deleteuser")
    public void delete(int id) {
        System.out.println("删除结果为...");
        userService.delete(id);
        System.out.println("删除用户id为..." + id);

    }

    @ResponseBody
    @RequestMapping("/updateuser")
    public void update(@ModelAttribute User user) {
        System.out.println("更新结果为...");
        userService.update(user);
        System.out.println("更新用户id为..." + user.getId());

    }


}
