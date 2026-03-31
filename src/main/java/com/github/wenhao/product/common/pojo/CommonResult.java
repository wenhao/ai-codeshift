package com.github.wenhao.product.common.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private T data;

    private CommonResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>("SUCCESS", "操作成功", null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>("SUCCESS", "操作成功", data);
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return new CommonResult<>("SUCCESS", msg, data);
    }

    public static <T> CommonResult<T> fail(String code, String msg) {
        return new CommonResult<>(code, msg, null);
    }

    public static <T> CommonResult<T> fail(String msg) {
        return new CommonResult<>("ERROR", msg, null);
    }

    public boolean isSuccess() {
        return "SUCCESS".equals(this.code);
    }
}