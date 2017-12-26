package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    void save(User user);

    public void delete(int id);

    public User get(int id);

    void update(User user);

    List<User> list();

    //根据用户名和密码查询用户
    //注解的两个参数会自动封装成map集合，括号内即为键
    public void findUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

    public User getUser(@Param("name") String name, @Param("password") String password);


}
