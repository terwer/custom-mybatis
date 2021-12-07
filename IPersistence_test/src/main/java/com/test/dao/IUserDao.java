package com.test.dao;

import com.test.pojo.User;

import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws Exception;

    public User findByCondition(User user) throws Exception;

    public boolean addUser(User user) throws Exception;

    public boolean updateUser(User user) throws Exception;

    public boolean deleteUser(User user) throws Exception;

}
