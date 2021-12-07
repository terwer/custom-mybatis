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
 * 完善：请完善自定义持久层框架IPersistence，在现有代码基础上添加、修改及删除功能。
 *
 * 题目分析
 * 实现思路
 * 代码讲解
 */

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:26
 */
public class IPersistenceTest {

    /**
     * 根据条件查询单个
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        user.setUsername("唐有炜");
        System.out.println(user);
    }

    /**
     * 查询所有
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();

        for (User user3 : users) {
            System.out.println(user3);
        }
    }
}
