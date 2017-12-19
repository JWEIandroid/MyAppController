package ssm.mapper;

import ssm.model.User;

import java.util.List;

public interface UserMapper {

    public int add(User user);

    public void delete(int id);

    public User get(int id);

    public int update(User category);

    public List<User> list();

    public int count();

}
