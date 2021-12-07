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
 *    现有的代码已经实现了mybatis链接数据库以及对数据库的基本操作。只需要添加对应的功能即可。
 *
 * 实现思路
 *
 * 1、mapper方式首先需要在IUserDao接口定义我们需要实现的增删改操作 addUser updateUser deleteUser
 *
 * 2、UserMapper.xml定义好对应的Sql
 *
 * 3、注意：由于mapper方式每次执行的方法最终都是调用代理类的invoke方法，因此，需要修改代理类的invode方法，分别取处理对应逻辑。
 *    出了判断之外，还需要在SqlSession的实现类实现具体的操作供代理方法内部调用。也可以写在接口上
 *    Executor不需要实现，executor的query方法可以通用
 *
 * 4、可以开始测试了
 *
 * 问题1：方法参数
 * 问题2：parseconfig只实现了select标签，还需要解析其他标签
 * 问题3：非查询不能用executeQuery，要用execute
 * 代码讲解
 * 完成
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

    @Test
    public void test3() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(5);
        user.setUsername("xiaoli");
        userDao.addUser(user);
        // 测试成功
    }

    @Test
    public void test4() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(4);
        user.setUsername("haha");
        userDao.updateUser(user);
        // 测试成功
    }

    @Test
    public void test5() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(3);
        userDao.deleteUser(user);
    }
}
