package com.leaf.core.context;


import com.leaf.core.beans.BeanDefinition;
import com.leaf.core.beans.annotation.AnnotationBeanDefinitionReader;
import com.leaf.core.beans.factory.AbstractBeanFactory;
import com.leaf.core.beans.factory.AutowireCapableBeanFactory;
import com.leaf.core.beans.io.PropertiesResourceLoader;
import java.util.Map;
/**
 * @program: leaf
 * @description:
 * @author: huiyuhang  github.com/Boombaozi
 * @create: 2018-08
 **/
public class AnnotationApplicationContext  extends AbstractApplicationContext {

    String config;

    public AnnotationApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
    }

    public AnnotationApplicationContext(String config){
        super(new AutowireCapableBeanFactory());
        this.config=config;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        AnnotationBeanDefinitionReader reader=
                new AnnotationBeanDefinitionReader(new PropertiesResourceLoader());
          reader.loadBeanDefinitions(config);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

    }


}
