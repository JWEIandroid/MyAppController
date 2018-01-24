package ssm.service;

import ssm.model.salerecord;
import ssm.model.shuohuomsg;

public interface SaleRecordService extends BaseService<salerecord>{



    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(int goodid,int userid);

    salerecord query(int goodid, int userid);


}
