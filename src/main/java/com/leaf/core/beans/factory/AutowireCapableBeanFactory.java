package com.leaf.core.beans.factory;



import com.leaf.core.BeanReference;
import com.leaf.core.aop.BeanFactoryAware;
import com.leaf.core.beans.BeanDefinition;
import com.leaf.core.beans.PropertyValue;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 可自动装配内容的BeanFactory
 * 
 * @author yihua.huang@dianping.com
 */
@Slf4j
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	//注入
	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;

				value = getBean(beanReference.getName());
				log.info("注入:["+value.getClass().getName()+"]"+beanReference.getName()+"-->["+bean.getClass().getName()+"]"+propertyValue.getName());
			}

			try {
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
								+ propertyValue.getName().substring(1), value.getClass());
				declaredMethod.setAccessible(true);

				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}
}
