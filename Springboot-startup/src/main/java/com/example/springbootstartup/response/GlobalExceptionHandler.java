package com.example.springbootstartup.response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public <T> Response<T> exceptionHandler(Exception e){
        // 判断是否为自定义异常
        if(e instanceof AppException){
            AppException appException = (AppException) e;
            return Response.error(appException.getCode(), appException.getMsg());
        }
        // 非自定义异常
        return Response.error(500, "服务器异常");
    }
}
