package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ManagerMapper;
import ssm.model.manager;
import ssm.service.ManagerService;

import java.util.List;


@Service
public class ManagerServiceImpl extends BaseServiceImpl<manager> implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public void save(manager manager) {
        managerMapper.save(manager);
    }

    @Override
    public void update(manager manager) {
        managerMapper.update(manager);
    }

    @Override
    public void delete(int id) {
        managerMapper.delete(id);

    }

    @Override
    public manager get(int id) {
        return managerMapper.get(id);

    }

    @Override
    public List<manager> list() {
        return managerMapper.list();
    }

    //后台登陆接口调用
    public manager getByAccountNPassword(String account, String password) {
        return managerMapper.getByAccountNPassword(account, password);
    }
}
