package com.leaf.core.beans.annotation;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.leaf.core.BeanReference;
import com.leaf.core.beans.BeanDefinition;
import com.leaf.core.beans.PropertyValue;
import com.leaf.core.beans.PropertyValues;
import com.leaf.core.beans.factory.AutowireCapableBeanFactory;
import com.leaf.core.beans.io.PropertiesResourceLoader;
import com.leaf.coretest.FoodDao;
import com.leaf.coretest.FoodServiceImpl;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AnnotationBeanDefinitionReaderTest {
    @Test
    public void loadBeanDefinitions() throws Exception {


        AnnotationBeanDefinitionReader reader1=
                new AnnotationBeanDefinitionReader(new PropertiesResourceLoader());
        reader1.loadBeanDefinitions("/leaf.properties");



        for(Map.Entry<String,BeanDefinition> entry : reader1.getRegistry().entrySet()){
            BeanDefinition beanDefinition= entry.getValue();

            System.out.println("name="+entry.getKey()+"----------");
            showbeande(beanDefinition);

        }








//        FoodDao foodDao=(FoodDao) factory.getBean("fooddao");
//        foodDao.addFood("ss");
//        foodDao.getList();
////
//         FoodServiceImpl foodService=(FoodServiceImpl)factory.getBean("foodserviceimpl");
//        foodService.addFood("肉");
//        for (String list :foodService.getAllFood()){
//            System.out.println(list);
//        }

    }

    public static void  showbeande(BeanDefinition beanDefinition){


        if(beanDefinition.getBean()==null) {
            System.out.println("bean=null");
        }else {
            System.out.println(beanDefinition.getBean().toString());
        }
        if(beanDefinition.getBeanClass()==null){
            System.out.println("beanClass==null");
        }else {
            System.out.println("beanClass=="+beanDefinition.getBeanClass().getName());
        }

        if(beanDefinition.getBeanClassName()==null){
            System.out.println("beanclassname==null");
        }else {
            System.out.println("beanclassname=="+beanDefinition.getBeanClassName());
        }

        if(beanDefinition.getPropertyValues()==null){
            System.out.println("property==null");
        }else {
            List<PropertyValue> propertyValue= beanDefinition.getPropertyValues().getPropertyValues();
            for(PropertyValue propertyValue2:propertyValue){
                if(propertyValue2.getValue()==null){
                    System.out.println("propertyValue==null");
                }
                if(propertyValue2.getName()==null){
                    System.out.println("propertiesname==null");
                }
                if(propertyValue2.getName()!=null&&propertyValue2.getValue()!=null) {
                    System.out.println("property     "+propertyValue2.getName() + "  " + propertyValue2.getValue().toString());
                    System.out.println("id =="+propertyValue2.getValue().getClass().getName());
                    BeanReference beanReference=(BeanReference)propertyValue2.getValue();

                    if(	beanReference.getName()==null){

                        System.out.println("beanreference.name==null");
                    }else {
                        System.out.println("beanrefer.name=="+beanReference.getName());
                    }
                    if(	beanReference.getBean()==null){

                        System.out.println("beanreference.bean==null");
                    }else {
                        System.out.println("beanrefer.bean=="+beanReference.getBean());
                    }


                }

            }

        }


    }

}