package ssm.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ssm.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    public static  void main() throws IOException{

//        String resource = "spring-mybatis.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        List<User> list = sqlSession.selectList("listuser");
//        for (User user:list){
//            System.out.println(user.getName());
//        }
        System.out.println("Hello");


    }


}
