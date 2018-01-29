package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ReportRecordMapper;
import ssm.model.reportrecord;
import ssm.service.ReportRecordService;

import java.util.List;

@Service
public class ReportRecordImpl extends BaseServiceImpl<reportrecord> implements ReportRecordService {

    @Autowired
    ReportRecordMapper reportRecordMapper;

    @Override
    public void save(reportrecord reportrecord) {
        reportRecordMapper.save(reportrecord);
    }

    @Override
    public void update(reportrecord reportrecord) {
        reportRecordMapper.update(reportrecord);
    }

    public void deletewith2id(int userid, int goodsid) {

        reportRecordMapper.deletewith2id(userid, goodsid);

    }

    public reportrecord query(int userid, int goodsid) {
        return reportRecordMapper.query(userid, goodsid);
    }

    public List<reportrecord> list_user(int userid) {
        return reportRecordMapper.list_user(userid);
    }
}
