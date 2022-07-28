package com.liux.demoi18n.constant;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/24 10:19
 * @description :异常枚举
 */
public enum ErrCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 系统内部错误
     */
    INTERNAL_SERVER_ERROR(-1, "INTERNAL_SERVER_ERROR"),
    /**
     * 请求参数异常
     */
    REQUEST_ABNORMAL(-2, "REQUEST_ABNORMAL"),
    /**
     * 参数校验错误
     */
    PARAM_VALIDATED_ERROR(-3, "PARAM_VALIDATED_ERROR"),
    /**
     * 业务逻辑异常
     */
    REST_EXCEPTION(-4, "REST_EXCEPTION"),

    /**
     * 数据不存在
     */
    NO_DATA(-5, "NO_DATA"),
    /**
     * 未定义异常
     */
    ERR_UNKNOWN(-6, "ERR_UNKNOWN"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(-7, "USER_NOT_EXIST");

    private int code;
    private String msg;

    ErrCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    public static ErrCodeEnum getEnum(int code) {
        for (ErrCodeEnum errCode : ErrCodeEnum.values()) {
            if (errCode.code == code) {
                return errCode;
            }
        }
        return ERR_UNKNOWN;
    }
}
