package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.Buyrecord;
import ssm.model.shuohuomsg;

public interface ShuoHuoMsgService extends BaseService<shuohuomsg>{

    void save(shuohuomsg shuohuomsg);

    void update(shuohuomsg shuohuomsg);

    void deletewith2id(@Param("goodsid") int goodsid,@Param("userid") int userid);

    shuohuomsg query(@Param("goodsid") int goodsid,@Param("userid") int userid,@Param("date")String date);


}
