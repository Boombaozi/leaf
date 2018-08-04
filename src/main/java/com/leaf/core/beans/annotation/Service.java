package com.leaf.core.beans.annotation;

import java.lang.annotation.*;

/**
 * 定义service注解，用于标注服务类
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
	String value() default "";
}
