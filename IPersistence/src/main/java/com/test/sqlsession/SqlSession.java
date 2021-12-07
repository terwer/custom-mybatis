package com.test.sqlsession;

import java.util.List;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 01:50
 */
public interface SqlSession {

    // 查询所有
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    public <T> T selectOne(String statementId, Object... params) throws Exception;

    public boolean add(String statementId, Object... params) throws Exception;

    public boolean update(String statementId, Object... params) throws Exception;

    public boolean delete(String statementId, Object... params) throws Exception;

    <T> T getMapper(Class<?> mapperClass);
}
