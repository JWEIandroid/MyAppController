package ssm.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ssm.model.User;
import ssm.model.goods;
import ssm.service.UserService;
import utils.FileUploadUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    UserService userService;

    /**
     * 根据用户名密码登录,返回token和用户id
     * 登录成功，更新用户token
     * @param name     用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map login(String name, String password, HttpServletResponse response) {

        Map<String,String> result = new HashMap<String, String>();
        User user = userService.getUser(name, password);

        if (user == null) {
            return userService.errorRespMap(respMap,"用户不存在");
        } else {
            StringBuilder sb = new StringBuilder("");
            //生成token
            long time = System.currentTimeMillis();
            sb.append(user.getName() + user.getPassword() + time);
            user.setToken(sb.toString());
            user.setUpdate_time(""+time);
            userService.update(user);
            //返回token和userid
            result.put("token",user.getToken());
            result.put("id",user.getId()+"");
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

        if (token != null && !token.equals("") && id != 0){
              User user = userService.getUser_token(token, id);
              if (user ==null){
                  return  userService.errorRespMap(respMap,"用户不存在");
              }else{
                  return  userService.successRespMap(respMap,"200",user);
              }
        }else {
            return  userService.errorRespMap(respMap,"参数非法");
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
        User user1 = userService.getuser_id(id);
        if (user1 == null) {
            System.out.println("失败");
            return userService.errorRespMap(respMap, "用户不存在");
        }
        userService.delete(id);
        User user2 = userService.getuser_id(id);
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
    public Map update(@ModelAttribute User user) {

        User user1 = userService.getuser_id(user.getId());
        if (user1 == null) {
            return userService.errorRespMap(respMap, "user not exist in db");
        }
        if (!checkParams(user)) {
            return userService.errorRespMap(respMap, "params not illegal");
        }
        userService.update(user);
        return userService.successRespMap(respMap, "success", user);

    }


    //获取全部用户
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

    @ResponseBody
    @RequestMapping("imgss")
    public void getImg(HttpServletResponse response){

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream("E:\\imgs\\"+"5.JPEG");
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        FileInputStream in;
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        try {
//            //图片读取路径
//            in=new FileInputStream("E:\\imgs\\"+"1.jpg");
//            int i=in.available();
//            byte[]data=new byte[i];
//            in.read(data);
//            in.close();
//
//            //写图片
//            OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
//            outputStream.write(data);
//            outputStream.flush();
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    /**
     * 用户上传头像
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "upload/{id}" , method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateHeadImg(@RequestParam("file") MultipartFile[] file , @PathVariable("id") int id) {
        //判断file数组不能为空并且长度大于0
        if(file != null && file.length > 0){
            //循环获取file数组中得文件
            for(int i = 0;i < file.length;i++){
                MultipartFile f = file[i];
                //保存文件
                FileUploadUtil fileUploadUtil = new FileUploadUtil();
                fileUploadUtil.saveHeadFile(f,id);
            }
            //上传成功
            return userService.successRespMap(respMap ,"200" ,"");
        }
        //参数为空则返回错误
        return userService.errorRespMap(respMap,"100");

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


        if (name == null || name.equals("")) {
            return res = false;
        }
        return res;

    }
}
