package com.bh.permission.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//增强@RestController的类
@RestControllerAdvice
public class WebExceptionControl {

    //当@RestController的类中有异常的时候，@ExceptionHandler注解中的属性标注的异常类被捕获到，可以在这里进行处理
    @ExceptionHandler(APIException.class)
    public String APIExceptionHandler(APIException e) {
        return e.getMessage();
    }
}