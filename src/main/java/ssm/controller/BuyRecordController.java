package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Buyrecord;
import ssm.model.User;
import ssm.service.BuyRecordService;
import ssm.service.GoodsService;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/record")
public class BuyRecordController extends BaseController<Buyrecord> {

    @Autowired
    BuyRecordService buyRecordService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;


    /**
     * 获取用户全部购买记录
     *
     * @param userid 用户id
     * @return 购买记录
     */
    @ResponseBody
    @RequestMapping("getrecords")
    public Map getRecordsWithUserid(int userid) {

        User user = userService.getuserById(userid);
        if (user == null) {
            return buyRecordService.errorRespMap(respMap, "用户不存在");
        }
        List<Buyrecord> list = buyRecordService.list_user(userid);
        if (list.size() <= 0) {
            return buyRecordService.errorRespMap(respMap, "没有数据");
        }
        return buyRecordService.successRespMap(respMap, "有" + list.size() + "条数据", list);
    }


    /**
     * 删除某个用户的全部购买记录
     *
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping("delrecords")
    public Map delRecords(int userid) {

        User user = userService.getuserById(userid);
        if (user == null) {
            return buyRecordService.errorRespMap(respMap, "用户不存在");
        }

        List<Buyrecord> list = buyRecordService.list_user(userid);
        if (list.size() <= 0) {
            return buyRecordService.errorRespMap(respMap, "没有数据");
        }
        buyRecordService.delete(userid);
        return buyRecordService.successRespMap(respMap, "删除" + list.size() + "条数据", "success");

    }


    /**
     * 查询用户的一条购买记录
     *
     * @param userid
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map selectone(@Param("userid") int userid, @Param("date") String date) {


        Buyrecord buyrecord1 = buyRecordService.selectone(userid, date);

        if (buyrecord1 == null) {
            return buyRecordService.errorRespMap(respMap, "记录不存在");
        }
        return buyRecordService.successRespMap(respMap, "success", buyrecord1);


    }


    /**
     * 添加一条购买记录
     * @param buyrecord
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addrecords")
    public Map addrecords(Buyrecord buyrecord) {

        Buyrecord buyrecord1 = buyRecordService.selectone(buyrecord.getUserid(), buyrecord.getDate());

        if (buyrecord1 != null) {
            return buyRecordService.errorRespMap(respMap, " 记录已经存在");
        }
        buyRecordService.save(buyrecord);
        return buyRecordService.successRespMap(respMap, "success", buyrecord);

    }





}
