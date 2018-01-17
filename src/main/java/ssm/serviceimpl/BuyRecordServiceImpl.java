package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.BuyRecordMapper;
import ssm.model.Buyrecord;
import ssm.service.BuyRecordService;

import java.util.List;

@Service
public class BuyRecordServiceImpl extends BaseServiceImpl<Buyrecord> implements BuyRecordService {

    @Autowired
    BuyRecordMapper buyRecordMapper;

    public void save(Buyrecord buyrecord) {

        buyRecordMapper.save(buyrecord);
    }

    public void update(Buyrecord buyrecord) {

        buyRecordMapper.update(buyrecord);

    }

    public List<Buyrecord> list_user(int userid) {
        return buyRecordMapper.list_user(userid);
    }

    public Buyrecord selectone(int userid,String date) {
        return buyRecordMapper.selectone(userid,date);
    }
}
