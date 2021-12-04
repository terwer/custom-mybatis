package com.test.sqlsession;

import com.test.pojo.Configuration;
import com.test.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 01:51
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {

        SimpleExecutor simpleExecutor = new SimpleExecutor();

        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<E> query = simpleExecutor.query(configuration, mappedStatement, params);

        return query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {

        // 使用JDK动态态代理为DAO生成代理对象并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                } else {
                    Object object = selectOne(statementId, args);
                    return object;
                }
            }
        });

        return (T) proxyInstance;
    }
}
