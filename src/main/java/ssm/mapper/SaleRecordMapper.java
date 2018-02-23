package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.salerecord;

import java.util.List;

public interface SaleRecordMapper extends BaseMapper<salerecord> {

    void save(salerecord salerecord1);

    void update(salerecord salerecord1);

    void deletewith2id(int goodid, int userid);

    salerecord query(int goodid, int userid);

    List<salerecord> list_user(int userid);

    List<String> selecttype(int userid);

    float selectIncomewithtype(@Param("type") String type, @Param("userid") int userid);

    float selectAll(@Param("userid") int userid);

}
