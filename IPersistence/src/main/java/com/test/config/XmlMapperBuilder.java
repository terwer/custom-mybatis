package com.test.config;

import com.test.pojo.Configuration;
import com.test.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
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

        // 这里只实现了select标签，还需要解析其他标签
        List<String> tags = new ArrayList<>();
        tags.add("select");
        tags.add("insert");
        tags.add("update");
        tags.add("delete");
        List<Element> list = getElementList(rootElement, tags);// rootElement.selectNodes("//select");

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

    // 这里封装一下
    private List<Element> getElementList(Element rootElement, List<String> tags) {
        // 这里只实现了select标签，还需要解析其他标签
        List<Element> list = new ArrayList<>();

        for (String tag : tags) {
            List<Element> item = rootElement.selectNodes("//" + tag);
            list.addAll(item);
        }

        return list;
    }

}
