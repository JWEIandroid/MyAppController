package ssm.mapper;

import ssm.model.reportrecord;
import ssm.model.salerecord;
import java.util.List;

public interface ReportRecordMapper extends  BaseMapper<reportrecord>{

    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(int goodsid,int userid);

    reportrecord query(int goodsid, int userid);

    List<reportrecord> list_user(int userid);

}
