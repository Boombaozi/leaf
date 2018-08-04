package com.leaf.core.context;

import com.leaf.core.beans.factory.AutowireCapableBeanFactory;
import com.leaf.coretest.FoodDao;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @program: leaf
 * @description:
 * @author: huiyuhang  github.com/Boombaozi
 * @create: 2018-08
 **/
public class AnnotationApplicationContextTest {
    @Test
    public void loadBeanDefinitions() throws Exception {
        AnnotationApplicationContext context=
                new AnnotationApplicationContext("/leaf.properties");


//       FoodDao foodDao= (FoodDao) context.getBean("fooddao");
//       foodDao.addFood("肉");
//       foodDao.addFood("肉");
//       for(String foodname:foodDao.getList()){
//           System.out.println(foodname);
//       }

    }

}