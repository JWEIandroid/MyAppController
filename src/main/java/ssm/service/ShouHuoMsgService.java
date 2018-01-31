package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.shouhuomsg;

public interface ShouHuoMsgService extends BaseService<shouhuomsg>{

    void save(shouhuomsg shouhuomsg);

    void update(shouhuomsg shouhuomsg);

    void deletewith2id(@Param("goodsid") int goodsid,@Param("userid") int userid);

    shouhuomsg query(@Param("goodsid") int goodsid,@Param("userid") int userid,@Param("date")String date);

    shouhuomsg getbyid(@Param("id")int id);


}
