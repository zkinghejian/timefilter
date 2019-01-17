package org.hj.timefilter.exception;

public class MyException extends RuntimeException {

    private String code;  //异常状态码

    private String message;  //异常信息

    private String method;   //发生的方法，位置等

    private String descinfo;   //描述

    public MyException(String code, String message, String method, String descinfo) {
        this.code = code;
        this.message = message;
        this.method = method;
        this.descinfo = descinfo;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }

    public String getDescinfo() {
        return descinfo;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setDescinfo(String descinfo) {
        this.descinfo = descinfo;
    }


}
