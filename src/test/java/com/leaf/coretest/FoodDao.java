package com.leaf.coretest;

import com.leaf.core.beans.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiny-spring
 * @description:
 * @author: boombaozi.com
 * @create: 2018-08
 **/

@Bean("fooddao")
public class FoodDao {

    FoodDao(){
        this.list=new ArrayList<String>();
    }

    private List<String> list;


    public String addFood(String foodname){
        list.add(foodname);
        return foodname;
    }

    public List<String> getList(){
        return list;
    }

}
