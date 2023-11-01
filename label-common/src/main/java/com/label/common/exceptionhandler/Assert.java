package com.label.common.exceptionhandler;

import com.label.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assert {
    public static void notNull(Object obj, ResponseEnum responseEnum){
        if(obj == null){
            log.info("obj isnull....");
            throw new MyExceptionHandler(responseEnum);
        }
    }

    public static void isTrue(boolean expression,ResponseEnum responseEnum){
        if(!expression){
            log.info("fail........");
            throw new MyExceptionHandler(responseEnum);
        }
    }
    public static void notEquals(Object m1,Object m2,ResponseEnum responseEnum){
        if (m1.equals(m2)){
            log.info("equals......");
            throw new MyExceptionHandler(responseEnum);
        }
    }
    public static void Equals(Object m1,Object m2,ResponseEnum responseEnum){
        if(!(m1.equals(m2))){
            log.info("notEquals.......");
            throw new MyExceptionHandler(responseEnum);
        }
    }




}
