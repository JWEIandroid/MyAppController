package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.manager;

import java.util.List;

public interface ManagerMapper extends BaseMapper<manager>{

    void save(manager manager);

    void update(manager manager);

    void delete(int id);

    manager get(int id);

    List<manager> list();

    manager getByAccountNPassword(@Param("account") String account, @Param("password") String password);
}
