package com.bysj.wyb.common.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author wangyb
 */
@Aspect
@Component
@Slf4j
public class UserLogCut {

    @Pointcut("@annotation(com.bysj.wyb.common.annotation.UserLog)")
    public void UserLog() {

    }

    @Before("UserLog()")
    public void BeforeLog(JoinPoint point) {
        log.info("----------开始记录用户登录信息---------");
        log.info("用户名信息：" + Arrays.toString(point.getArgs()));
        log.info("登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @Around("UserLog()")
    public void methodAround(ProceedingJoinPoint point) {
        log.info("----------用户登录成功！---------");
        try {
            Object o = point.proceed();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
        }
    }

    @After("UserLog()")
    public void returnLog() {
        log.info("-----------记录用户登录信息结束------------");
    }
}
