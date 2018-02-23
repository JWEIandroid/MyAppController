package ssm.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import ssm.model.Buyrecord;

import java.util.List;

public interface BuyRecordService extends BaseService<Buyrecord> {


    void save(Buyrecord buyrecord);

    void delete(int id);

    List<Buyrecord> list_user(int userid);

    List<Buyrecord> list();

    Buyrecord selectone(int userid, String date);

    List<String> selecttype(int userid);

    float selectpaywithtype(@Param("type") String type, @Param("userid") int userid);

    float selectAll(@Param("userid") int userid);

}
