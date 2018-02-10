package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.MessageBoard;
import ssm.model.User;
import ssm.service.MessageBoardService;
import ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("message/")
public class MessageBoardController extends BaseController<MessageBoard> {


    @Autowired
    MessageBoardService messageBoardService;
    @Autowired
    UserService userService;

    //根据两个用户id查询全部聊天记录
    @ResponseBody
    @RequestMapping("getallmessagewith2id")
    public Map getAllMessageWith2Id(MessageBoard messageBoard) {

        User user = userService.getuserById(messageBoard.getUserid());
        User receiver = userService.getuserById(messageBoard.getReceiverid());
        List<MessageBoard> result = new ArrayList<MessageBoard>();
        if (user == null || receiver == null) {
            return messageBoardService.successRespMap(respMap, "参数错误", new ArrayList<MessageBoard>());
        }
        result = messageBoardService.select(messageBoard);
        for (MessageBoard messageBoard1:result){
            messageBoard1.setUser(user);
            messageBoard1.setReceiver(receiver);
        }
        if (result.size() < 1) {
            return messageBoardService.successRespMap(respMap, "没有数据", new ArrayList<MessageBoard>());
        }
        return messageBoardService.successRespMap(respMap, "success", result);
    }

    //根据receiverid查询全部用户信息
    @ResponseBody
    @RequestMapping("getuserwithreceiverid")
    public Map getUsersWithReceiverId(MessageBoard messageBoard) {

        List<User> user_data = new ArrayList<User>();
        List<Integer> usersid = messageBoardService.selectWriter(messageBoard);
        if (usersid == null || usersid.size() < 1) {
            return messageBoardService.successRespMap(respMap, "没有数据", new ArrayList<MessageBoard>());
        }
        for (int userid : usersid) {
            User user = userService.getuserById(userid);
            if (user != null) {
                user_data.add(user);
            }
        }
        return messageBoardService.successRespMap(respMap, "共"+user_data.size()+"条数据", user_data);

    }

    //插入一条聊天记录
    @ResponseBody
    @RequestMapping("insertonemessage")
    public Map insertOneMessageRecord(MessageBoard messageBoard) {

        User user1 = userService.getuserById(messageBoard.getUserid());
        User receiver = userService.getuserById(messageBoard.getReceiverid());
        if (user1 == null || receiver == null) {
            return messageBoardService.errorRespMap(respMap, "error---user or receiver is null");
        }
        if (messageBoard.getDate()==null){
            messageBoard.setDate(System.currentTimeMillis()+"");
        }
        messageBoardService.save(messageBoard);
        return messageBoardService.successRespMap(respMap, "success", "");
    }


}
