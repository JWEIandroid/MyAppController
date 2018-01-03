package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    void save(User user);

    public void delete(int id);

    public User getuser_id(int id);

    void update(User user);

    List<User> list();

    //根据用户名和密码查询用户
    public void findUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

    public User getUserByPhone(@Param("tel") String tel, @Param("password") String password);

    //根据token和id获取一个用户
    public User getUser_token(@Param("token") String token, @Param("id") int id);

    User get(int id);
}
