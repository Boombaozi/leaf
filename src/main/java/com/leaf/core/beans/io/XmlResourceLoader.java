package com.leaf.core.beans.io;

import java.net.URL;

/**
 * @author yihua.huang@dianping.com
 */
public class XmlResourceLoader implements ResourceLoader{

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
