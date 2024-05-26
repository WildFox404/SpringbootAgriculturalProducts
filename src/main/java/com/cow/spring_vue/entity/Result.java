package com.cow.spring_vue.entity;

public class Result<T>{
    private Integer code;
    private String message;
    private T data;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static <T> Result<T> error(String temp_message) {
        Result<T> result = new Result<>();
        result.setCode(400); // 设置成功状态码
        result.setMessage(temp_message); // 设置消息
        result.setData(null); // 设置数据
        return result;
    }

    public static <T> Result<T> error(String temp_message,T data) {
        Result<T> result = new Result<>();
        result.setCode(400); // 设置成功状态码
        result.setMessage(temp_message); // 设置消息
        result.setData(data); // 设置数据
        return result;
    }


    public static <T> Result<T> success(String temp_messgae){
        Result<T> result = new Result<>();
        result.setCode(200); // 设置成功状态码
        result.setMessage(temp_messgae); // 设置消息
        result.setData(null); // 设置数据
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(200); // 设置成功状态码
        result.setMessage(null); // 设置消息
        result.setData(data); // 设置数据
        return result;
    }
    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode(200); // 设置成功状态码
        result.setMessage(null); // 设置消息
        result.setData(null); // 设置数据
        return result;
    }
    // 静态方法 success
    public static <T> Result<T> success(String temp_message, T temp_data) {
        Result<T> result = new Result<>();
        result.setCode(200); // 设置成功状态码
        result.setMessage(temp_message); // 设置消息
        result.setData(temp_data); // 设置数据
        return result;
    }
}
