package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Buyrecord;

import java.util.List;

public interface BuyRecordMapper extends BaseMapper<Buyrecord>{


    void save(Buyrecord buyrecord);

    void update(Buyrecord buyrecord);

    void delete(int id);

    Buyrecord get(int id);

    List<Buyrecord> list();

    List<Buyrecord> list_user(int userid);

    Buyrecord selectone(@Param("userid") int userid, @Param("date") String date);

    List<String> selecttype(int userid);

    float selectpaywithtype(@Param("type") String type,@Param("userid") int userid);

    float selectAll(@Param("userid") int userid);


}
