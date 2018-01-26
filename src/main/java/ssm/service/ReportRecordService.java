package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.reportrecord;
import ssm.model.salerecord;
import ssm.model.shuohuomsg;

public interface ReportRecordService extends  BaseService<reportrecord>{


    void save(reportrecord reportrecord1);

    void update(reportrecord reportrecord1);

    void deletewith2id(@Param("userid") int userid,@Param("goodsid") int goodsid);

    reportrecord query(@Param("userid") int userid,@Param("goodsid") int goodsid);



}
