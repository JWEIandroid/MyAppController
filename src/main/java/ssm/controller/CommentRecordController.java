package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Comment;
import ssm.model.User;
import ssm.model.goods;
import ssm.service.CommentRecordService;
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

    @ResponseBody
    @RequestMapping("/select")
    public Map QueryComment(Comment comment) {

        User user = userService.getuserById(comment.getUserid());
        goods goods = goodsService.getgoodsByGoodId(comment.getGoodsid());
        User receiver = userService.getuserById(comment.getReceiverid());
        List<Comment> result = new ArrayList<Comment>();

        if (user == null || goods == null || receiver == null) {
            return commentRecordService.successRespMap(respMap, "参数错误", new ArrayList<Comment>());
        }
        switch (comment.getType()) {
            case 0:
                 result = commentRecordService.select(comment);
                break;
            case 1:
//                List<Comment> result = commentRecordService.select(comment);
                break;
            default:
                return commentRecordService.successRespMap(respMap, "类型错误", new ArrayList<Comment>());

        }
        for (Comment comment1 : result) {
            comment1.setUser(user);
            comment1.setGoods(goods);
            comment1.setReceiver(receiver);
        }
        if (result.size() < 1 || result == null) {
            return commentRecordService.successRespMap(respMap, "没有数据", new ArrayList<Comment>());
        }
        return commentRecordService.successRespMap(respMap, "成功", result);
    }

}
