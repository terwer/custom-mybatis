package com.test;

import com.test.dao.IUserDao;
import com.test.io.Resources;
import com.test.pojo.User;
import com.test.sqlsession.SqlSession;
import com.test.sqlsession.SqlSessionFactory;
import com.test.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:26
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        // 这里使用Usermapper2.xml的配置方法
//        IUserDao userDao = new UserDaoImpl();
//
//        User user = new User();
//        user.setId(1);
//        user.setUsername("唐有炜");
//        User user2 = userDao.findByCondition(user);
//        System.out.println(user2);
//
//        System.out.println();
//
//        List<User> users = userDao.findAll();
//        for (User user3 : users) {
//            System.out.println(user3);
//        }

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        user.setUsername("唐有炜");

        User user2 = userDao.findByCondition(user);
        System.out.println(user2);

        List<User> users = userDao.findAll();

        for (User user3 : users) {
            System.out.println(user3);
        }
    }
}
