package com.test.dao;

import com.test.pojo.User;

import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws Exception;

    public User findByCondition(User user) throws Exception;
}
