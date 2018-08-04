package com.leaf.core.beans.io;


import java.io.IOException;
import java.io.InputStream;


public class PropertiesResource implements Resource {

    private String propertiesfile;

    PropertiesResource(String propertiesfile){
        this.propertiesfile=propertiesfile;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.getClass().getResourceAsStream(propertiesfile);
    }
}
