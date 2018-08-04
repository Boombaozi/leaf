package com.leaf.core.beans.io;

public class PropertiesResourceLoader implements ResourceLoader {
    public Resource getResource(String propertiesfile) {
        return new PropertiesResource(propertiesfile);
    }
}
