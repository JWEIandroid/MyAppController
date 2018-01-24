package ssm.mapper;

import ssm.model.Buyrecord;
import ssm.model.shuohuomsg;

import java.util.List;

public interface ShuoHuoMsgMapper extends BaseMapper<shuohuomsg>{


    void save(shuohuomsg shuohuomsg);

    void update(shuohuomsg shuohuomsg);

    void deletewith2id(int goodid,int userid);

    shuohuomsg query(int goodid,int userid);
}
