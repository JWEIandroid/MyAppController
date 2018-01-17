package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.UserMapper;
import ssm.model.User;
import ssm.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    //获取全部用户
    public List<User> list() {
        return userMapper.list();
    }

    //根据id获取用户
    public User getuserById(int id) {
        return userMapper.getuser_id(id);
    }

    //根据账号密码查询用户
    public void login(String name, String password) {

        userMapper.findUserByNameAndPwd(name, password);
    }

    //根据账号密码查询用户，返回user
    public User getUserByPhone(String tel, String password) {

       return userMapper.getUserByPhone(tel, password);


    }

    //增加用户
    public void save(User user) {
        userMapper.save(user);
    }

    //删除用户
    public void delete(int id) {
        userMapper.delete(id);
    }


    //更新用户
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    //根据token和id获取一个用户
    public User getUser_token(String token, int id) {
        return userMapper.getUser_token(token, id);
    }

    public User getUserByPhoneNum(String phone) {
        return userMapper.getUserByPhoneNum(phone);
    }


    @Override
    public Map<String, Object> errorRespMap(Map<String, Object> map, String message) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("error_code", "-1");
        map.put("message", message);
        map.put("data", new User());
        return map;
    }

    public Map<String, Object> merrorRespMap(Map<String, Object> map, String message) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("error_code", "-1");
        map.put("message", message);
        map.put("data", "null");
        return map;
    }
}
