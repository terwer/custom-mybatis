package com.test.config;

import com.test.pojo.Configuration;
import com.test.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 01:28
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseConfig(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        // <mapper>
        Element rootElement = document.getRootElement();

        List<Element> list = rootElement.selectNodes("//select");

        String namespace = rootElement.attributeValue("namespace");

        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sqlText = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sqlText);

            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }

    }
}
