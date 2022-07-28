package com.liux.demoi18n.common.exception;

import lombok.Data;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/24 11:11
 * @description :基础异常
 */
@Data
public class BaseException extends Exception {

    private int code;

    private String message;

    private Object[] params;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }


    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(int code, Object[] params) {
        this.code = code;
        this.params = params;
    }

}
