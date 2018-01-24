package ssm.service;

import ssm.model.reportrecord;
import ssm.model.salerecord;
import ssm.model.shuohuomsg;

public interface ReportRecordService extends  BaseService<reportrecord>{


    void save(reportrecord reportrecord1);

    void update(reportrecord reportrecord1);

    void deletewith2id(int goodid,int userid);

    reportrecord query(int goodsid, int userid);



}
