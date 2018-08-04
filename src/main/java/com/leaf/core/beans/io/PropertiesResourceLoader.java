package com.leaf.core.beans.io;
/**
 * @program: leaf
 * @description:
 * @author: huiyuhang  github.com/Boombaozi
 * @create: 2018-08
 **/
public class PropertiesResourceLoader implements ResourceLoader {
    public Resource getResource(String propertiesfile) {
        return new PropertiesResource(propertiesfile);
    }
}
