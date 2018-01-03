package ssm.service;

import ssm.model.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<User> list();

    User getuserById(int id);
//登录
    void login(String tel, String password);

    void save(User user);

    void delete(int id);
//更新用户信息
    void update(User user);
//根据用户手机和密码查找用户信息
    User getUserByPhone(String tel, String password);
//根据token和id查找用户信息
    User getUser_token(String token, int id);


}
