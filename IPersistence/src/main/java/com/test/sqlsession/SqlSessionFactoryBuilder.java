package com.test.sqlsession;

import com.test.config.XmlConfigBuilder;
import com.test.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:52
 */
public class SqlSessionFactoryBuilder {

    public com.test.sqlsession.SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {

        // 1、使用dom4j解析文件，将解析的内容封装到configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 2、创建SqlSessionFactory
        com.test.sqlsession.SqlSessionFactory sqlSessionFactory = new com.test.sqlsession.DefaultSqlSessionFactory(configuration);

        return sqlSessionFactory;
    }

}
