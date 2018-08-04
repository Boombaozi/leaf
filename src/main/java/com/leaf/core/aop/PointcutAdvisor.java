package com.leaf.core.aop;

/**
 * @author yihua.huang@dianping.com
 */
public interface PointcutAdvisor extends Advisor{

   Pointcut getPointcut();
}
