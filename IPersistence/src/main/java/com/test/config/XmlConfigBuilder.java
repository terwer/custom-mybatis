package com.test.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.test.io.Resources;
import com.test.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:56
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        // 使用dom4j对xml文件进行解析
        Document document = new SAXReader().read(in);

        // <configuration>
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");

        Properties properties = new Properties();

        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");

            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass((String) properties.get("driverClass"));
        comboPooledDataSource.setJdbcUrl((String) properties.get("jdbcUrl"));
        comboPooledDataSource.setUser((String) properties.get("username"));
        comboPooledDataSource.setPassword((String) properties.get("password"));

        configuration.setDataSource(comboPooledDataSource);


        // mapper.xml解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");

            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);

            xmlMapperBuilder.parseConfig(resourceAsStream);
        }

        return configuration;
    }
}
