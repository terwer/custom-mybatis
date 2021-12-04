package com.test.sqlsession;

import com.test.pojo.Configuration;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 01:46
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
