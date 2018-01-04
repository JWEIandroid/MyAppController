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
import ssm.service.UserService;
import utils.FileUploadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
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

        if (userService.getUserByPhone(user.getTel(), user.getPassword()) == null) {
            user.setCreate_time(System.currentTimeMillis() + "");
            user.setUpdate_time(System.currentTimeMillis() + "");
            user.setHeadimg("http://" + Constant.IP + ":8080/user/imgss?filename=" + "1.jpg");
            userService.save(user);
            return userService.successRespMap(respMap, "添加用户成功", user);
        } else {
            System.out.println("用户存在--注册失败");
            return userService.errorRespMap(respMap, "用户已经存在");
        }
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
    public Map update(@ModelAttribute User user) {

        User user1 = userService.getuserById(user.getId());
        if (user1 == null) {
            return userService.errorRespMap(respMap, "user not exist in db");
        }
        userService.update(user);
        return userService.successRespMap(respMap, "success", user);

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
    @RequestMapping(value = "imgss", method = RequestMethod.GET)
    public void getImg(@Param("filename") String filename, HttpServletResponse response) {

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(Constant.IMG_HOME + filename);
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
        if (user1 != null & user1.getPassword().equals(oldpsd)) {
            user1.setPassword(newpsd);
            userService.update(user1);
            user1 = userService.getuserById(id);
            return userService.successRespMap(respMap, "修改成功", user1);
        } else {
            return userService.errorRespMap(respMap, "修改失败");
        }
    }

    /**
     * 文件上传功能
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("id") int id) throws IOException {
//        String path = request.getSession().getServletContext().getRealPath("upload");
        String path = Constant.IMG_HOME;
        String fileName = file.getOriginalFilename();
        StringBuilder sb = new StringBuilder(fileName);
        long now = System.currentTimeMillis();
        String deal_name = now + sb.substring(sb.indexOf("."), sb.length());
        System.out.println("name:" + deal_name);

        File dir = new File(path, deal_name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);

        User user = userService.getuserById(id);
        if (user != null) {
            user.setHeadimg(Constant.STORE_HOME + "user/imgss/?filename=" + deal_name);
            user.setUpdate_time(now+"");
            userService.update(user);
            return userService.successRespMap(respMap, "success", deal_name);
        } else {
            return userService.merrorRespMap(respMap, "");

        }

    }

    @ResponseBody
    @RequestMapping("/upload2"  )
    public Map<String,Object> upload2(HttpServletRequest request) throws IllegalStateException, IOException {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "demoUpload" + file.getOriginalFilename();
                        StringBuilder sb = new StringBuilder(fileName);
                        long now = System.currentTimeMillis();
                        String deal_name = now + sb.substring(sb.indexOf("."), sb.length());
                        //定义上传路径
                        String path = Constant.IMG_HOME;
                        File dir = new File(path,deal_name);
                        if (!dir.exists()){
                            dir.mkdirs();
                        }
                        file.transferTo(dir);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return userService.successRespMap(respMap,"success","ok");
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


}
