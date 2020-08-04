package com.yaoyili.config;

import com.yaoyili.CheckException;
import com.yaoyili.ParamErrorException;
import com.yaoyili.controller.ao.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

/*
* 通用controller的切面，进行异常处理、日志打印等通用过程
* */

@Component
@Aspect
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Pointcut("execution(public com.yaoyili.controller.ao.ResultBean *(..))")
    void controllerHandler() {}


    /*
    * Around Advice用法总结
    *   作用：打印日志、异常处理与接口返回、业务时间检测等
    *   第一个参数必须是ProceedingJoinPoint，调用ProceedingJoinPoint.proceed()执行被切入的方法，返回Object类型
    *
    * */
    @Around("controllerHandler()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();
        ResultBean<?> res;

        try {
            res = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            res = handleException(pjp, e);
        }

        return res;
    }

    /*
    * 所有的失败情况都在这里处理，Controller只需要放成功的步骤即可，
    * 也可以用抛出异常的方式进行一些特殊任务执行
    * */

    private ResultBean<?> handleException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> res = new ResultBean();
        e.printStackTrace();

        if (e instanceof CheckException) {
            //业务逻辑错误
            res.setMsg(e.getLocalizedMessage());
            res.setCode(ResultBean.FAIL);
        }
        else if (e instanceof ParamErrorException) {
            //参数校验错误
            res.setMsg(e.getLocalizedMessage());
            res.setCode(ResultBean.CHECK_ERROR);
        }
        else if (e instanceof RuntimeException) {
            res.setMsg("操作失败");
            res.setCode(ResultBean.FAIL);
        }
        else {
            logger.error(pjp.getSignature() + " error ", e);
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            res.setMsg(e.toString());
            res.setCode(ResultBean.FAIL);
        }

        return res;
    }
}
