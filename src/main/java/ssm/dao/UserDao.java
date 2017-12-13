package ssm.dao;

import ssm.model.User;

public interface UserDao extends BaseDao<User>{


    public  User login(User user);

}
