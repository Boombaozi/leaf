package com.leaf.core.context;


import com.leaf.core.beans.factory.AbstractBeanFactory;

public class AnnotationApplicationContext  extends AbstractApplicationContext {


    public AnnotationApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {

    }
}
