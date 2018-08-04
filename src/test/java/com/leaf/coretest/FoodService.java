package com.leaf.coretest;

import java.util.List;

/**
 * @program: tiny-spring
 * @description:
 * @author: boombaozi.com
 * @create: 2018-08-04 13
 **/

public interface FoodService {

    public List<String> getAllFood();


    public String  addFood(String foodname);

}
