package com.test;

import com.test.dao.IUserDao;
import com.test.dao.UserDaoImpl;
import com.test.pojo.User;
import org.junit.Test;

import java.util.List;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:26
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        IUserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setId(2);
        user.setUsername("张月");
        User user2 = userDao.findByCondition(user);
        System.out.println(user2);

        System.out.println();

    }

    @Test
    public void test2() throws Exception {
        IUserDao userDao = new UserDaoImpl();

        List<User> users = userDao.findAll();
        for (User user3 : users) {
            System.out.println(user3);
        }
    }
}
