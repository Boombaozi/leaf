package com.leaf.core.beans.annotation;

import java.lang.annotation.*;

/**
 * 定义bean注解，有该注解的类归容器管理
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
	String value() default "";
}
