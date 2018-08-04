package com.leaf.core.beans.annotation;

import java.lang.annotation.*;

/**
 * 定义controller注解，用于标注控制器类
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
	String value() default "";
}