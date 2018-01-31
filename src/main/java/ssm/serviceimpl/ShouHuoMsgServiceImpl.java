package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ShouHuoMsgMapper;
import ssm.model.shouhuomsg;
import ssm.service.ShouHuoMsgService;

@Service
public class ShouHuoMsgServiceImpl extends BaseServiceImpl<shouhuomsg> implements ShouHuoMsgService {

    @Autowired
    ShouHuoMsgMapper shouHuoMsgMapper;


    @Override
    public void save(shouhuomsg shouhuomsg) {
        shouHuoMsgMapper.save(shouhuomsg);
    }

    @Override
    public void update(shouhuomsg shuohuomsg) {
        shouHuoMsgMapper.update(shuohuomsg);
    }

    public void deletewith2id(int goodid, int userid) {

        shouHuoMsgMapper.deletewith2id(goodid, userid);

    }

    public shouhuomsg query(int goodid, int userid, String date) {
        return shouHuoMsgMapper.query(goodid, userid, date);
    }

    public shouhuomsg getbyid(int id) {
        return shouHuoMsgMapper.getbyid(id);
    }


}
