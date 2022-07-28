package com.liux.demoi18n.common.result;

import com.liux.demoi18n.constant.ErrCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/28 10:05
 * @description :返回结果包装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResult {
    private int code;
    private String message;
    private Object data;

    public ReturnResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.code == ErrCodeEnum.SUCCESS.code();
    }

    public static ReturnResult success(Object data) {
        return new ReturnResult(ErrCodeEnum.SUCCESS.code(), ErrCodeEnum.SUCCESS.msg(), data);
    }

    public static ReturnResult fail(int code, String message) {
        return new ReturnResult(code, message);
    }
}
