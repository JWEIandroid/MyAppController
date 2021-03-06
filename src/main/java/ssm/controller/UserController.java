package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import constant.Constant;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ssm.model.User;
import ssm.model.manager;
import ssm.service.ManagerService;
import ssm.service.UserService;
import utils.FileUploadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    UserService userService;

    /**
     * 根据用户名密码登录,返回token和用户id
     * 登录成功，更新用户token
     *
     * @param tel      用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map login(String tel, String password, HttpServletResponse response) {

        Map<String, String> result = new HashMap<String, String>();
        User user = userService.getUserByPhone(tel, password);

        if (user == null) {
            return userService.errorRespMap(respMap, "用户不存在");
        } else {
            StringBuilder sb = new StringBuilder("");
            //生成token
            long time = System.currentTimeMillis();
            sb.append(user.getTel() + user.getPassword() + time);
            user.setToken(sb.toString());
            user.setUpdate_time("" + time);
            userService.update(user);
            //返回token和userid
            result.put("token", user.getToken());
            result.put("id", user.getId() + "");
            return userService.successRespMap(respMap, "登陆成功", result);
        }
    }

    /**
     * 根据用户ID和token获取一个用户
     *
     * @param token
     * @param id    用户id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getUserMsg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getUser_logined(String token, int id) {

        if (token != null && !token.equals("") && id != 0) {
            User user = userService.getUser_token(token, id);
            if (user == null) {
                return userService.errorRespMap(respMap, "用户不存在");
            } else {
                return userService.successRespMap(respMap, "200", user);
            }
        } else {
            return userService.errorRespMap(respMap, "参数非法");
        }

    }


    /***
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map save(User user) {

        if (userService.getUserByPhoneNum(user.getTel()) != null) {
            System.out.println("用户存在--注册失败");
            return userService.errorRespMap(respMap, "用户已存在");
        }
        user.setCreate_time(System.currentTimeMillis() + "");
        user.setUpdate_time(System.currentTimeMillis() + "");
        user.setHeadimg("file/download/?filename=normal.png&type=0");
        userService.save(user);
        return userService.successRespMap(respMap, "注册成功", user);

    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map delete(int id) {
        System.out.println("删除结果为...");
        User user1 = userService.getuserById(id);
        if (user1 == null) {
            System.out.println("失败");
            return userService.errorRespMap(respMap, "用户不存在");
        }
        userService.delete(id);
        User user2 = userService.getuserById(id);
        if (user2 == null) {
            System.out.println("成功");
            return userService.successRespMap(respMap, "删除成功", id);
        } else {
            System.out.println("--失败");
            return userService.errorRespMap(respMap, "删除失败");
        }

    }


    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map update(User user) {

        User user1 = userService.getuserById(user.getId());
        if (user1 == null) {
            return userService.successRespMap(respMap, "用户不存在", new User());
        }

        String new_name = user.getName();
        String new_password = user.getPassword();
        String new_adress = user.getAdress();
        String new_sex = user.getSex();
        String new_desc = user.getDescription();
        String new_tel = user.getTel();


        if (new_name != null) {
            user1.setName(new_name);
        }
        if (new_password != null) {
            user1.setPassword(new_password);
        }
        if (new_adress != null) {
            user1.setAdress(new_adress);
        }
        if (new_desc != null) {
            user1.setDescription(new_desc);
        }
        if (new_sex != null) {
            user1.setSex(new_sex);
        }

        if (new_tel != null) {
            user1.setTel(new_tel);
        }

        userService.update(user1);
        return userService.successRespMap(respMap, "success", user1);

    }


    /**
     * 获取全部用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getAllUser() {
        List<User> cs = userService.list();
        if (cs == null) {
            return userService.errorRespMap(respMap, "error");
        } else {
            return userService.successRespMap(respMap, "success", cs);
        }
    }


    /**
     * 查询图片
     * 文件下载功能
     *
     * @param filename
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void getImg(@Param("filename") String filename, HttpServletResponse response) {

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(Constant.IMG_USER_HOME + filename);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 重新设置密码
     * @param oldpsd
     * @param newpsd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetpsd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map resetpsd(@Param("oldpsd") String oldpsd, @Param("newpsd") String newpsd, @Param("id") int id) {

        User user1 = userService.getuserById(id);

        if (user1 == null) {
            return userService.errorRespMap(respMap, "用户不存在");
        }


        if (user1 != null & user1.getPassword().equals(oldpsd)) {
            user1.setPassword(newpsd);
            user1.setUpdate_time(System.currentTimeMillis() + "");
            userService.update(user1);
            user1 = userService.getuserById(id);
            return userService.successRespMap(respMap, "修改成功", user1);
        } else {
            return userService.errorRespMap(respMap, "修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "QueryUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getUserById(@Param("userid") int userid) {
        User user = userService.getuserById(userid);
        if (user == null) {
            return userService.errorRespMap(respMap, "用户不存在");
        }
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

        res &= (name != null || !name.equals(""));
        res &= (sex.equals("男") || sex.equals("女") || sex.equals("") || sex == null);

        return res;

    }


    @Autowired
    ManagerService managerServicel;

    //===========================================管理员的增删改查=========================================
    @ResponseBody
    @RequestMapping(value = "saveManager", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map saveManager(manager manager) {

        if (manager.getAccount() != null && manager.getPassword() != null) {
            managerServicel.save(manager);
            return managerServicel.successRespMap(respMap, "保存成功", "");
        }
        return managerServicel.errorRespMap(respMap, "保存失败");
    }


    @ResponseBody
    @RequestMapping(value = "delManager", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map delManager(int id) {

        manager manager = managerServicel.get(id);
        if (manager != null) {
            managerServicel.delete(id);
            return managerServicel.successRespMap(respMap, "删除成功", "");
        }
        return managerServicel.errorRespMap(respMap, "删除失败 管理员不存在");
    }

    @ResponseBody
    @RequestMapping(value = "ListManager", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map ListManager() {

        List<manager> result = managerServicel.list();
        if (result != null && result.size() > 0) {
            return managerServicel.successRespMap(respMap, "success", result);
        }
        result = new ArrayList<manager>();
        return managerServicel.successRespMap(respMap, "failed", result);
    }

    @ResponseBody
    @RequestMapping(value = "UpdateManager", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map UpdateManager(manager manager) {


        manager manager1 = managerServicel.get(manager.getId());
        if (manager != null) {
            if (manager.getAccount() != null) {
                manager1.setAccount(manager.getAccount());
            }
            if (manager.getPassword() != null) {
                manager1.setPassword(manager.getPassword());
            }
            managerServicel.update(manager1);
            return managerServicel.successRespMap(respMap, "更新成功", "");
        } else {
            return managerServicel.errorRespMap(respMap, "更新失败 管理员不存在");
        }
    }

    @ResponseBody
    @RequestMapping(value = "QueryManager", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map QueryManager(@Param("userid") int userid) {
        manager manager = managerServicel.get(userid);
        if (manager == null) {
            return managerServicel.errorRespMap(respMap, "用户不存在");
        }
        return managerServicel.successRespMap(respMap, "success", manager);
    }


    //    ==================================后台系统登陆接口
    @ResponseBody
    @RequestMapping("managerlogin")
    public Map managerlogin(@Param("account") String account, @Param("password") String password) {

        manager manager_result = managerServicel.getByAccountNPassword(account, password);
        if (manager_result == null) {
            return managerServicel.errorRespMap(respMap, "管理员不存在");
        } else {
            return managerServicel.successRespMap(respMap, "登陆成功", manager_result);
        }

    }


}
