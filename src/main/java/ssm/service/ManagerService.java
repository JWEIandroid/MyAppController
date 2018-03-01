package ssm.service;

import ssm.model.manager;

import java.util.List;

public interface ManagerService extends BaseService<manager>{

    void save(manager manager);

    void update(manager manager);

    void delete(int id);

    manager get(int id);

    List<manager> list();
}
