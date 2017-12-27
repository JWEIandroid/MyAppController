package ssm.service;

import ssm.model.User;
import java.util.List;

public interface UserService extends BaseService<User>{

    List<User> list();

    User getuser_id(int id);

    void login(String name,String password);

    void save(User user);

    void delete(int id);

    void update(User user);

    User getUser(String name,String password);



}
