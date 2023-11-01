package com.label.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class R {
    public static Object ok;
    private String status;
    private String message;
    private Object data;
    private R(){}
    public static R ok(){
        R r = new R();
        r.setStatus(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }
    public static R error(){
        R r = new R();
        r.setStatus(ResponseEnum.ERROR.getCode());
        r.setStatus(ResponseEnum.ERROR.getMessage());
        return r;
    }
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setStatus(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(String code){
        this.setStatus(code);
        return this;
    }

//    public R data(String key, Object value){
//        this.data = new HashMap<>();
//        this.data.put(key, value);
//        return this;
//    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public R data(Object object){
        this.setData(object);
        return this;
    }
}
