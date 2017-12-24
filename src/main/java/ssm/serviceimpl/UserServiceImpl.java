package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ssm.mapper.UserMapper;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list() {
        return userMapper.list();
    }

    public void login(String name, String password) {
        userMapper.findUserByNameAndPwd(name, password);
    }

    public User getUser(String name, String password) {

        User user = userMapper.getUser(name, password);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public User register(User user) {

        System.out.println("userImpl:"+user.getName());
        userMapper.addUser(
                user.getName(),
                user.getPassword(),
                user.getAdress(),
                user.getTel(),
                user.getSex(),
                user.getDescription(),
                user.getToken()
        );
        return user;
    }


}
