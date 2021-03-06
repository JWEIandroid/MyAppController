package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.SaleRecordMapper;
import ssm.model.salerecord;
import ssm.service.SaleRecordService;

import java.util.List;

@Service
public class SaleServiceImpl extends BaseServiceImpl<salerecord> implements SaleRecordService {


    @Autowired
    SaleRecordMapper saleRecordMapper;

    @Override
    public void save(salerecord salerecord) {
        saleRecordMapper.save(salerecord);
    }

    @Override
    public void update(salerecord salerecord) {
        saleRecordMapper.update(salerecord);
    }

    public void deletewith2id(int goodid, int userid) {

        saleRecordMapper.deletewith2id(goodid, userid);

    }

    public salerecord query(int goodid, int userid) {
        return saleRecordMapper.query(goodid, userid);
    }

    public List<salerecord> list_user(int userid) {
        return saleRecordMapper.list_user(userid);
    }

    public List<String> selecttype(int userid) {
        return saleRecordMapper.selecttype(userid);
    }

    public float selectIncomewithtype(String type, int userid) {
        return saleRecordMapper.selectIncomewithtype(type, userid);
    }

    public float selectAll(int userid) {
        return saleRecordMapper.selectAll(userid);
    }


}
