package com.example.springbootstartup.response;

public class Response<T> {
    private int code;
    private String msg;
    private T data;
    private Response(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Response success(T data){
        Response response = new Response(200, "success", data);
        return response;
    }

    public static <T> Response success(String msg, T data){
        Response response = new Response(200, msg, data);
        return response;
    }

    public static <T> Response error(int code, String msg){
        Response response = new Response(200, msg, null);
        return response;
    }

    public static <T> Response error(AppExceptionCodeMsg appExceptionCodeMsg){
        Response response = new Response(appExceptionCodeMsg.getCode(), appExceptionCodeMsg.getMsg(), null);
        return response;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public T getData() {
        return data;
    }
}
