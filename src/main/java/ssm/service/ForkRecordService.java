package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.ForkRecord;

import java.util.List;

public interface ForkRecordService extends BaseService<ForkRecord> {

    void save(ForkRecord forkRecord);

    void delete(ForkRecord forkRecord);

    void update(ForkRecord forkRecord);

    ForkRecord selectone(ForkRecord forkRecord);

    List<ForkRecord> selectByUserid(@Param("userid") int userid);


}
