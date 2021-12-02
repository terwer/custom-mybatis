package com.test.io;

import java.io.InputStream;

/**
 * @author terwer
 * @Description
 * @create 2021-12-01 00:06
 */
public class Resources {

    public static InputStream getResourceAsStream(String path) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }

}
