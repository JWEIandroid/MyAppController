package ssm.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ForkRecordMapper;
import ssm.model.ForkRecord;
import ssm.service.ForkRecordService;

import java.util.List;

@Service
public class ForkServiceImpl extends BaseServiceImpl<ForkRecord> implements ForkRecordService {

    @Autowired
    ForkRecordMapper forkRecordMapper;


    @Override
    public void save(ForkRecord forkRecord) {
        forkRecordMapper.save(forkRecord);
    }

    public void delete(ForkRecord forkRecord) {
        forkRecordMapper.delete(forkRecord);
    }

    @Override
    public void update(ForkRecord forkRecord) {
        forkRecordMapper.update(forkRecord);
    }

    public ForkRecord selectone(ForkRecord forkRecord) {
       return forkRecordMapper.selectone(forkRecord);
    }

    public List<ForkRecord> selectByUserid(int userid) {
        return forkRecordMapper.selectByUserid(userid);
    }


}
