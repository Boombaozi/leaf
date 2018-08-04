package com.leaf.core.beans.annotation;

import java.lang.annotation.*;

/**
 * @program: leaf
 * @description: Controller注解
 * @author: huiyuhang  github.com/Boombaozi
 * @create: 2018-08
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
	String value() default "";
}
