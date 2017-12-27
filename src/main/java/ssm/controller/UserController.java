package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.User;
import ssm.model.goods;
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
    @RequestMapping(value="userlist",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
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
    @RequestMapping(value="login",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map login(String name, String password) {

        User user = userService.getUser(name, password);

        if (user == null) {
            System.out.println("null");
            return userService.errorRespMap(respMap, "100");
        } else {
            return userService.successRespMap(respMap, "200", "success");
        }
    }

    //注册用户
    @ResponseBody
    @RequestMapping(value = "register",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map save(User user) {

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

    //删除用户
    @ResponseBody
    @RequestMapping(value = "deleteuser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map delete(int id) {
        System.out.println("删除结果为...");
        User user1 = userService.getuser_id(id);
        if (user1==null){
            System.out.println("失败");
            return userService.errorRespMap(respMap, "用户不存在");
        }
        userService.delete(id);
        User user2 = userService.getuser_id(id);
        if (user2==null){
            System.out.println("成功");
            return userService.successRespMap(respMap, "删除成功", id);
        }else{
            System.out.println("--失败");
            return userService.errorRespMap(respMap, "删除失败");
        }

    }

    @ResponseBody
    @RequestMapping(value = "updateuser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Map update(@ModelAttribute User user) {

        User user1 = userService.getuser_id(user.getId());
        if (user1==null){
            return userService.errorRespMap(respMap, "user not exist in db");
        }
        if (!checkParams(user)) {
            return userService.errorRespMap(respMap, "params not illegal");
        }
        userService.update(user);
        return userService.successRespMap(respMap, "success", user);

    }

    //检查参数是否正确
    private boolean checkParams(User user) {

        boolean res = true;
        String name = user.getName();
        String tel = user.getTel();
        String password = user.getPassword();
        String token = user.getToken();
        String adress = user.getAdress();
        String description = user.getDescription();
        String sex = user.getSex();


        if (name==null || name.equals("")){
            return  res =false;
        }
        return res;

    }


}
