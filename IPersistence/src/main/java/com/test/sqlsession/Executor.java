package com.test.sqlsession;

import com.test.pojo.Configuration;
import com.test.pojo.MappedStatement;

import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws Exception;
}
