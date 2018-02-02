package ssm.mapper;

import ssm.model.ForkRecord;

import java.util.List;

public interface ForkRecordMapper extends  BaseMapper<ForkRecord>{

    void save(ForkRecord forkRecord);
    void delete(ForkRecord forkRecord);
    void update(ForkRecord forkRecord);
    ForkRecord selectone(ForkRecord forkRecord);
    List<ForkRecord> selectByUserid(int userid);
}
