package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ReportRecordMapper;
import ssm.model.reportrecord;
import ssm.service.ReportRecordService;

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

    public void deletewith2id(int goodid, int userid) {

        reportRecordMapper.deletewith2id(goodid, userid);

    }

    public reportrecord query(int goodid, int userid) {
        return reportRecordMapper.query(goodid, userid);
    }
}
