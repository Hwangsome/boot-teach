package com.bh.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义数据的返回格式
 */
@Data
//允许链式调用
@Accessors(chain = true)
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    //将构造器私有化
    private R() {
    }

    //成功的静态方法
    public static R ok() {

        return
                new R()
                        .setSuccess(Boolean.TRUE)
                        .setCode(ResultCode.SUCCESS)
                        .setMessage("成功");
    }

    public static R error() {
        return
                new R()
                        .setSuccess(Boolean.FALSE)
                        .setCode(ResultCode.ERROR)
                        .setMessage("失败");
    }

    //实现链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
