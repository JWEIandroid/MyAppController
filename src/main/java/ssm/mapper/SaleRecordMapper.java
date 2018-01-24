package ssm.mapper;

import ssm.model.salerecord;
import ssm.model.shuohuomsg;

public interface SaleRecordMapper extends  BaseMapper<salerecord>{

    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(int goodid,int userid);

    salerecord query(int goodid, int userid);

}
