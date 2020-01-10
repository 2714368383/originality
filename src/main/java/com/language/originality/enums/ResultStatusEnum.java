package com.language.originality.enums;

public enum ResultStatusEnum {
	SUCCESS(100, "成功"),
    ERROR(500, "操作失败");

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
