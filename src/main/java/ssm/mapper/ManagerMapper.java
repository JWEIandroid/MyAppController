package ssm.mapper;

import ssm.model.manager;

import java.util.List;

public interface ManagerMapper extends BaseMapper<manager>{

    void save(manager manager);

    void update(manager manager);

    void delete(int id);

    manager get(int id);

    List<manager> list();
}
