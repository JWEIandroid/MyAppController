package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.UserMapper;
import ssm.model.User;
import ssm.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
private UserMapper userMapper;

    public List<User> list() {
        return userMapper.list();
    }
}
