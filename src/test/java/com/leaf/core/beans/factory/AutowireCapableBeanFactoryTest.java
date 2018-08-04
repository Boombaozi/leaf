package com.leaf.core.beans.factory;

import com.leaf.core.beans.BeanDefinition;
import com.leaf.core.beans.annotation.AnnotationBeanDefinitionReader;
import com.leaf.core.beans.io.PropertiesResourceLoader;
import com.leaf.coretest.FoodDao;
import com.leaf.coretest.FoodServiceImpl;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * @program: leaf
 * @description:
 * @author: huiyuhang  github.com/Boombaozi
 * @create: 2018-08
 **/
public class AutowireCapableBeanFactoryTest {
    @Test
    public void applyPropertyValues() throws Exception {



        AnnotationBeanDefinitionReader reader1=
                new AnnotationBeanDefinitionReader(new PropertiesResourceLoader());

        reader1.loadBeanDefinitions("/leaf.properties");


        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader1.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        FoodDao dao=(FoodDao) beanFactory.getBean("fooddao");
        dao.addFood("sss");
        List<String> name=dao.getList();
        for(String s   :name){
            System.out.println(s);
        }
        FoodServiceImpl service=(FoodServiceImpl) beanFactory.getBean("foodserviceimpl");

        List<String> name2=service.getAllFood();
        for(String s   :name2){
            System.out.println(s);
        }

    }

}