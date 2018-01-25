package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.salerecord;
import ssm.model.shuohuomsg;

public interface SaleRecordService extends BaseService<salerecord>{



    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(@Param("goodsid") int goodsid,@Param("userid") int userid);

    salerecord query(@Param("goodsid") int goodsid, int userid);


}
