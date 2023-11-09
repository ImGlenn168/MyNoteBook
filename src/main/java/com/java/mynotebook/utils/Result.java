package com.java.mynotebook.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result success() {
        return new Result(1, "success");
    }

    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    public static Result result(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", data);
        return new Result(1, result);
    }

    public static Result fail() {
        return new Result(-1, "fail");
    }

    public static Result fail(Object data) {
        return new Result(-1, "fail", data);
    }

    public static Result getResult(int i) {
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }
}
