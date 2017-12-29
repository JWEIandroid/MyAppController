package ssm.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.mapper.UserMapper;
import ssm.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class MybatisTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void testAdd(){
//        userMapper.save(user);
    }



    @Test
    public void testList(){

        System.out.println(userMapper);
        List<User> userMappers = userMapper.list();
        for (User user:userMappers){
            System.out.print("用户：");
            System.out.print(user.getId()+"电话：");
            System.out.println(user.getTel());
        }

    }



}
