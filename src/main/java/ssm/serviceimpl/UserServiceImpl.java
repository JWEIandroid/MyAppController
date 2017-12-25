package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.UserMapper;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;

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


    public void save(User user) {
        userMapper.save(user);
    }


    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}
