package com.guofei.wu.springannotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-12 20:46
 * @since v3.0
 */
@Aspect
public class LogAspect {
    // 切入点
    @Pointcut(value = "execution(public int com.guofei.wu.springannotation.aop.MathCalculator.*(..)))")
    public void pointCut() {
    }

    // 在本类中可以这样使用
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("方法" + joinPoint.getSignature().getName() + "logStart..." + Arrays.asList(joinPoint.getArgs()));
    }


    // 在其他类中使用
    @After(value = "com.guofei.wu.springannotation.aop.LogAspect.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("方法" + joinPoint.getSignature().getName() + "logEnd...");
    }


    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturning(JoinPoint joinPoint, Object result) {
        System.out.println("方法" + joinPoint.getSignature().getName() + "longReturning...返回结果:" + result);

    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("方法" + joinPoint.getSignature().getName() + "logException...异常信息为：" + ex);
    }

    // 环绕通知
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {

        System.out.println("doAround run...");

        Object result = null;

        try {

            System.out.println("method before invoke...");

            result = joinPoint.proceed();

            System.out.println("method invoked, result: " + result);

        } catch (Throwable throwable) {

            System.out.println("method throws Exception: " + throwable.getMessage());

            throwable.printStackTrace();

        }

        return result;

    }


}
