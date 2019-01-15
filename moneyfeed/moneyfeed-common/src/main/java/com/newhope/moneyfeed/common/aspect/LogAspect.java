package com.newhope.moneyfeed.common.aspect;

import com.newhope.moneyfeed.common.annotation.ShowCostTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 2018/7/26.
 */
@Aspect
@Component
public class LogAspect {

    private final static Logger logger= LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.newhope.moneyfeed.common.annotation.ShowCostTime)")
    public void annotationPoint(){};



    @Around("annotationPoint()")
    public Object showCostTime(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime=System.currentTimeMillis();
        ((MethodSignature)pjp.getSignature()).getMethod().getAnnotation(ShowCostTime.class);
        String targetMethodName = pjp.getSignature().getName();
        logger.info("--------开始执行方法[{}]---------------",targetMethodName);
        final Object proceed = pjp.proceed();
        Long endTime=System.currentTimeMillis();
        logger.info("--------结束执行方法[{}],耗时[{}]秒---------------",targetMethodName,(endTime-startTime)/1000);
        return proceed;
    }

}