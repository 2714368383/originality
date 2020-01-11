package com.language.originality.enums;

/**
 * 异常的枚举类 1- 999
 * 200 成功 500 失败
 * 100-199 参数校验
 * 400-499 认证登陆
 */
public enum ResultStatusEnum {
    ERROR(500, "操作失败"),
    OPERATION_SUCCESS(200, "操作成功"),
    PARAM_VALIDATOR(101, "传入参数异常");

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回结果信息
     */
    private String message;

    ResultStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
