package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ShuoHuoMsgMapper;
import ssm.model.Buyrecord;
import ssm.model.shuohuomsg;
import ssm.service.ShuoHuoMsgService;

@Service
public class ShuoHuoMsgServiceImpl extends BaseServiceImpl<shuohuomsg> implements ShuoHuoMsgService {

    @Autowired
    ShuoHuoMsgMapper shuoHuoMsgMapper;

    @Override
    public void save(shuohuomsg shuohuomsg) {
        shuoHuoMsgMapper.save(shuohuomsg);
    }

    @Override
    public void update(shuohuomsg shuohuomsg) {
        shuoHuoMsgMapper.update(shuohuomsg);
    }

    public void deletewith2id(int goodid, int userid) {

        shuoHuoMsgMapper.deletewith2id(goodid, userid);

    }

    public shuohuomsg query(int goodid, int userid,String date) {
        return shuoHuoMsgMapper.query(goodid, userid,date);
    }


}
