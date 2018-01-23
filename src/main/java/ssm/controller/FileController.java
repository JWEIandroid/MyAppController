package ssm.controller;


import constant.Constant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ssm.model.User;
import ssm.model.goodimgs;
import ssm.model.goods;
import ssm.service.GoodsImgService;
import ssm.service.GoodsService;
import ssm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController<Object> {


    @Autowired
    UserService userService;
    @Autowired
    GoodsImgService goodsImgService;
    @Autowired
    GoodsService goodsService;

    //指定文件下载路径
    String IMG_DOWNLOADHOME = null;

    /**
     * 查询图片
     * 文件下载功能
     *
     * @param type     0为用户 1为商品
     * @param filename
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void getImg(@Param("filename") String filename, HttpServletResponse response, @Param("type") int type) {


        if (type == 0) {
            IMG_DOWNLOADHOME = Constant.IMG_USER_HOME;
        } else if (type == 1) {
            IMG_DOWNLOADHOME = Constant.IMG_GOOD_HOME;
        } else {
            IMG_DOWNLOADHOME = "E:\\";
            return;
        }

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(IMG_DOWNLOADHOME + filename);
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


    /**
     * 文件上传功能
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("id") int id) throws IOException {

//        String path = request.getSession().getServletContext().getRealPath("upload");
        User user = userService.getuserById(id);
        //判断用户是否为空
        if (user == null) {
            return userService.merrorRespMap(respMap, "user is not exist");
        }
        //用户存在，开始上传图片
        String fileName = file.getOriginalFilename();
        StringBuilder sb = new StringBuilder(fileName);
        long now = System.currentTimeMillis();
        String deal_name = now + sb.substring(sb.indexOf("."), sb.length());
        System.out.println("name:" + deal_name);
        File dir = new File(Constant.IMG_USER_HOME, deal_name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);

        //更新图片地址到数据库
//        user.setHeadimg(Constant.STORE_HOME + "file/download/?filename=" + deal_name + "&type=" + 0);
        user.setHeadimg("file/download/?filename=" + deal_name + "&type=" + 0);
        user.setUpdate_time(now + "");
        userService.update(user);
        return userService.successRespMap(respMap, "success", deal_name);
    }


    /**
     * 多文件上传
     *
     * @param id      商品id或用户
     * @param request
     * @param type    0为用户 1为商品
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/upload2")
    public Map<String, Object> upload2(HttpServletRequest request, @RequestParam("type") int type, @RequestParam("id") int id) throws IllegalStateException, IOException {

        //文件上传路径
        String mpath = null;

        switch (type) {
            case 0:
                break;
            case 1:
                mpath = Constant.IMG_GOOD_HOME;
                //获取商品
                goods goods = goodsService.getgoodsByGoodId(id);
                if (goods == null) {
                    return userService.merrorRespMap(respMap, "商品不存在");
                }
                //商品存在，开始上传图片
                List<String> file_upload = Multifileupload(mpath, request);
                //上传完成，开始更新数据库
                if (file_upload.size() > 0) {

                    for (String filename : file_upload) {
//                        String url = Constant.STORE_HOME + "file/download/?filename=" + filename + "&type=" + type;
                        String url = "file/download/?filename=" + filename + "&type=" + type;
                        System.out.println("多文件上传 名字：" + filename);
                        int goodsid = goods.getId();
                        goodimgs goodimgs = new goodimgs();
                        goodimgs.setGoodid(goodsid);
                        goodimgs.setUrl(url);
                        goodsImgService.save(goodimgs);
                    }
                    return goodsImgService.successRespMap(respMap, "多文件上传成功", file_upload);
                }
            default:
        }

        return goodsImgService.errorRespMap(respMap, "多文件上传失败");
    }


    private List<String> Multifileupload(String mpath, HttpServletRequest request) throws IOException {


        List<String> file_name = new ArrayList<String>();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = file.getOriginalFilename();
                        StringBuilder sb = new StringBuilder(fileName);
                        long now = System.currentTimeMillis();
                        String deal_name = now + sb.substring(sb.indexOf("."), sb.length());
                        file_name.add(deal_name);
                        //定义上传路径
                        String path = mpath;
                        File dir = new File(path, deal_name);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        file.transferTo(dir);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }
            return file_name;
        }
        return null;
    }

}
