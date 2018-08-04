package com.leaf.coretest;


import com.leaf.core.beans.annotation.Autowired;
import com.leaf.core.beans.annotation.Service;

import java.util.List;

/**
 * @program: tiny-spring
 * @description:
 * @author: boombaozi.com
 * @create: 2018-08
 **/

@Service("foodserviceimpl")
public class FoodServiceImpl implements FoodService {

    @Autowired("fooddao")
    private FoodDao foodDao;

    @Override
    public List<String> getAllFood() {
        return foodDao.getList();
    }

    @Override
    public String addFood(String foodname) {
        return foodDao.addFood(foodname);
    }
}
