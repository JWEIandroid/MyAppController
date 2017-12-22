package ssm.service;

import ssm.model.User;
import java.util.List;

public interface UserService extends BaseService<User>{

    List<User> list();

    //用户登录
    void login(String name,String password);

    User getUser(String name,String password);
}
