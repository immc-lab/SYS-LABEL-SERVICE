package com.label.common.exceptionhandler;

import com.label.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyExceptionHandler extends RuntimeException {
    private  String code;
    private String message;
    public MyExceptionHandler(String message){
        this.message = message;
    }
    public MyExceptionHandler(String message,String code){
        this.message = message;
        this.code = code;
    }
    public MyExceptionHandler(ResponseEnum responseEnum){
        this.message = responseEnum.getMessage();
        this.code = responseEnum.getCode();
    }
    public MyExceptionHandler(ResponseEnum responseEnum,Throwable e){
        super(e);
        this.message = responseEnum.getMessage();
        this.code = responseEnum.getCode();
    }

}
