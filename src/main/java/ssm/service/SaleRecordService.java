package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.salerecord;

import java.util.List;

public interface SaleRecordService extends BaseService<salerecord>{



    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(@Param("goodsid") int goodsid,@Param("userid") int userid);

    salerecord query(@Param("goodsid") int goodsid, int userid);


    List<salerecord> list_user(@Param("userid")int userid);

    List<String> selecttype(int userid);

    float selectIncomewithtype(@Param("type") String type, @Param("userid") int userid);

    float selectAll(@Param("userid") int userid);

}
