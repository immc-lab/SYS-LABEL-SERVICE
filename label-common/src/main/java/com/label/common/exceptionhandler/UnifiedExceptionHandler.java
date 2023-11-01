package com.label.common.exceptionhandler;

import com.label.common.result.R;
import com.label.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j//打印日志
@Component
@RestControllerAdvice//注解可以捕获任何异常
public class UnifiedExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public R handlerException(Exception e){
        System.out.println("执行！");
        log.error(e.getMessage(),e);
        return R.error();
    }
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R  handlerException(BadSqlGrammarException e){
        log.error(e.getMessage(),e);
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }
    @ExceptionHandler(value = MyExceptionHandler.class)
    public R  handlerException(MyExceptionHandler e){
        log.error(e.getMessage(),e);
        return R.error().code(e.getCode().toString()).message(e.getMessage());
    }
}
