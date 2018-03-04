package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.MessageBoard;
import ssm.model.User;
import ssm.service.MessageBoardService;
import ssm.service.UserService;

import javax.sound.midi.Receiver;
import java.util.*;


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
        for (MessageBoard messageBoard1 : result) {
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
        List<User> temporary_list = new ArrayList<User>();
        if (usersid == null || usersid.size() < 1) {
            return messageBoardService.successRespMap(respMap, "没有数据", new ArrayList<MessageBoard>());
        }
        for (int userid : usersid) {
            User user = userService.getuserById(userid);
            if (user != null) {
                temporary_list.add(user);
                user_data.add(user);
            }
        }

        Set set = new HashSet();
        List<User> userList = new ArrayList<User>();
        for (User user1 : user_data) {
            if (set.add(user1.getName())) {
                userList.add(user1);
            }
        }

//        for (int i = 0; i < user_data.size(); i++) {
//            if (i == user_data.size() - 1) {
//                break;
//            }
//            if (user_data.get(i).getName().equals(user_data.get(i + 1).getName())) {
//                user_data.remove(i);
//                i--;
//            }
//        }
        //遍历第二次看是否存在相同用户信息
        return messageBoardService.successRespMap(respMap, "共" + userList.size() + "条数据", userList);

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
        if (messageBoard.getDate() == null) {
            messageBoard.setDate(System.currentTimeMillis() + "");
        }
        messageBoardService.save(messageBoard);
        return messageBoardService.successRespMap(respMap, "success", "");
    }




    /**
     * 查询全部留言信息
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map List() {

        List<MessageBoard> result = messageBoardService.list();
        if (result == null || result.size()<=0){
            result = new ArrayList<MessageBoard>();
            return messageBoardService.successRespMap(respMap, "success", result);
        }

        for (MessageBoard messageBoard:result){
            if (messageBoard.getUserid()!=0){
                User user = userService.getuserById(messageBoard.getUserid());
                messageBoard.setUser(user);
            }
            if (messageBoard.getReceiverid()!=0){
                User receiver = userService.getuserById(messageBoard.getReceiverid());
                messageBoard.setReceiver(receiver);
             }
        }
        return messageBoardService.successRespMap(respMap, "success", result);
    }

    /**
     * 更新全部留言信息
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public Map Update(MessageBoard messageBoard) {

        MessageBoard messageBoard1 = messageBoardService.get(messageBoard.getId());
        if (messageBoard1 ==null){
            return messageBoardService.errorRespMap(respMap,"没有数据");
        }
//        if (messageBoard.getReceiverid()!=0){
//            messageBoard.setReceiverid(messageBoard.getReceiverid());
//        }
//        if (messageBoard.getUserid()!=0){
//            messageBoard.setUserid(messageBoard.getUserid());
//        }
        if (messageBoard.getContent()!=null){
            messageBoard1.setContent(messageBoard.getContent());
        }
        messageBoardService.update(messageBoard1);
        return messageBoardService.successRespMap(respMap, "success", "");
    }

    /**
     * 根据留言id删除留言记录
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deletebyid")
    public Map DeleteById(@Param("id") int id){

        if (id==0){
            return messageBoardService.errorRespMap(respMap,"id = 0");
        }
        MessageBoard messageBoard = messageBoardService.get(id);
        if (messageBoard ==null){
            return messageBoardService.errorRespMap(respMap,"查询不到这条数据");
        }
        messageBoardService.deletebyid(id);
        return messageBoardService.successRespMap(respMap,"删除成功","");
    }



    /**
     * 根据留言内容查询留言记录
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping("querybycontent")
    public Map QueryByContent(@Param("content") String content){

        if (content==null){
            return messageBoardService.errorRespMap(respMap,"查询内容不能为空");
        }
        List<MessageBoard> result = messageBoardService.selectmsgbycontent(content);
        if (result == null || result.size()<=0){
            result = new ArrayList<MessageBoard>();
            return messageBoardService.successRespMap(respMap, "success", result);
        }

        for (MessageBoard messageBoard:result){
            if (messageBoard.getUserid()!=0){
                User user = userService.getuserById(messageBoard.getUserid());
                messageBoard.setUser(user);
            }
            if (messageBoard.getReceiverid()!=0){
                User receiver = userService.getuserById(messageBoard.getReceiverid());
                messageBoard.setReceiver(receiver);
            }
        }
        return messageBoardService.successRespMap(respMap, "success", result);
    }





}
