package com.github.wenhao.product.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通用返回结果包装类
 *
 * @param <T> 数据类型
 */
@Data
@Schema(description = "通用返回结果")
public class CommonResult<T> {

    @Schema(description = "是否成功", required = true)
    private Boolean success;

    @Schema(description = "错误码", required = true)
    private String code;

    @Schema(description = "错误提示", required = true)
    private String message;

    @Schema(description = "返回数据")
    private T data;

    private CommonResult() {}

    private CommonResult(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(true, "0", "success", data);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(true, "0", message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> error(String code, String message) {
        return new CommonResult<>(false, code, message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> error(String message) {
        return new CommonResult<>(false, "-1", message, null);
    }

    /**
     * 构建失败结果
     */
    public static <T> CommonResult<T> fail(String code, String message) {
        return new CommonResult<>(false, code, message, null);
    }

    /**
     * 构建失败结果
     */
    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(false, "-1", message, null);
    }
}