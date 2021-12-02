package com.test.sqlsession;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 01:07
 */
public interface SqlSessionFactory {
    public SqlSession openSession();
}
