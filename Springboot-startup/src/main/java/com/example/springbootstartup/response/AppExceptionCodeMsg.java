package com.example.springbootstartup.response;

// 定义业务相关异常
public enum AppExceptionCodeMsg {
    //user 100xx:
    USERNAME_NOT_EXISTS(10001,"用户名不存在"),
    WRONG_PASSWORD(10002, "密码错误"),
    USERNAME_DUPLICATED(10003, "用户名已存在"),
    INCORRECT_FORMAT(10004, "密码格式不符合要求"),
    WRONG_CONFIRM(10005, "两次密码不一致"),
    WRONG_EMAIL(10006, "邮箱无效")



    ;
    private int code;
    private String msg;
    AppExceptionCodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
