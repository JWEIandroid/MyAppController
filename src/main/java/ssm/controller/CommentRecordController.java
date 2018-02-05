package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Comment;
import ssm.model.MessageBoard;
import ssm.model.User;
import ssm.model.goods;
import ssm.service.CommentRecordService;
import ssm.service.GoodsImgService;
import ssm.service.GoodsService;
import ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("comment")
public class CommentRecordController extends BaseController<Comment> {

    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CommentRecordService commentRecordService;
    @Autowired
    GoodsImgService goodsImgService;


    /**
     * 根据用户id，receiverid和商品id查询评论记录
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/select")
    public Map QueryComment(Comment comment) {

        User user = userService.getuserById(comment.getUserid());
        goods goods = goodsService.getgoodsByGoodId(comment.getGoodsid());
        User receiver = userService.getuserById(comment.getReceiverid());
        List<Comment> result;

        if (user == null || goods == null || receiver == null) {
            return commentRecordService.successRespMap(respMap, "参数错误", new ArrayList<Comment>());
        }

        goods.setImgurl(goodsImgService.getImgByGoodid(comment.getGoodsid()));
        result = commentRecordService.select(comment);

        for (Comment comment1 : result) {
            comment1.setUser(user);
            comment1.setGoods(goods);
            comment1.setReceiver(receiver);
        }
        if (result.size() < 1) {
            return commentRecordService.successRespMap(respMap, "没有数据", new ArrayList<Comment>());
        }
        return commentRecordService.successRespMap(respMap, "成功", result);
    }

    /**
     * 删除一条评论
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletebyuseridanddate")
    public Map deleteByUseridandDate(Comment comment) {

        User user1 = userService.getuserById(comment.getUserid());
        String date = comment.getDate();

        if (user1 == null || date == null) {
            return commentRecordService.errorRespMap(respMap, "错误");
        }
        commentRecordService.deleteByUseridandDate(comment);
        return commentRecordService.successRespMap(respMap, "成功", result);
    }

    /**
     * 保存一条评论
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public Map save(Comment comment) {

        String time_now = System.currentTimeMillis() + "";
        User user = userService.getuserById(comment.getUserid());
        goods goods = goodsService.getgoodsByGoodId(comment.getGoodsid());
        User receiver = userService.getuserById(comment.getReceiverid());

        if (user == null || receiver == null || goods == null) {
            return commentRecordService.errorRespMap(respMap, "error");
        }
        if (comment.getDate() == null) {
            comment.setDate(time_now);
        }
        commentRecordService.save(comment);
        return commentRecordService.successRespMap(respMap, "成功", "");
    }

    /**
     * 查看一个商品的全部评论
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("getcommmentByGoodsId")
    public Map selectByGoodsId(Comment comment) {

        goods goods = goodsService.getgoodsByGoodId(comment.getGoodsid());
        if (goods == null) {
            return goodsService.successRespMap(respMap, "good is null", new ArrayList<Comment>());
        }
        List<Comment> result = commentRecordService.selectByGoodsId(comment);
        for (Comment comment1:result){
            User user = userService.getuserById(comment1.getUserid());
            if (user!=null){
                comment1.setUser(user);
            }
        }
        if (result == null || result.size() < 1) {
            return commentRecordService.successRespMap(respMap, "error--cause by result is null or no data", new ArrayList<Comment>());
        }
        return goodsService.successRespMap(respMap, "success", result);

    }


}
