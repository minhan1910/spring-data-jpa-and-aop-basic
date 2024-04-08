package com.an.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.an.cruddemo.dao.*.*(..))")
    private void forDaoPackage() {};

    @Pointcut("execution(* com.an.cruddemo.dao.*.add*(..))")
    private void forAddMethodInDaoPackage() {};

    @Pointcut("execution(* com.an.cruddemo.dao.*.update*(..))")
    private void forUpdateMethodInDaoPackage() {};

//    @Before("execution(* add*())")
//    @Before("execution(public void com.an.cruddemo.dao.AccountDao.addAccount())")
    @Before("forDaoPackage() && (forAddMethodInDaoPackage() || forUpdateMethodInDaoPackage())")
//    @Before("execution(* com.an.cruddemo.dao.*.*(com.an.cruddemo.entity.Student, ..))")
    public void beforeAddOrUpdateAccountAdvice() {
        System.out.println("\n=======> Executing @Before advice on add or update method in Dao pacakge");
    }

//    @Before()
//    public void beforeAddAndUpdateAccountAdvice() {
//        System.out.println("\n=======> Executing @Before advice on add or update method in Dao pacakge");
//    }

}
