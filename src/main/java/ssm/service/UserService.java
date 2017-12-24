package ssm.service;

import org.springframework.web.bind.annotation.ModelAttribute;
import ssm.model.User;
import java.util.List;

public interface UserService extends BaseService<User>{

    List<User> list();
    //用户登录
    void login(String name,String password);

    User getUser(String name,String password);

    User register(User user);
}
