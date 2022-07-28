package com.liux.demoi18n.common.exception;

import com.liux.demoi18n.common.result.ReturnResult;
import com.liux.demoi18n.constant.ErrCodeEnum;
import com.liux.demoi18n.handle.MessageSourceHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/24 10:02
 * @description :全局异常处理
 */
@Slf4j
@RestControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {


    @Resource
    private MessageSourceHandler messageSourceHandler;

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ReturnResult exceptionHandle(Exception e) {
        e.printStackTrace();
        log.error("捕获到异常：{}", e.getMessage());
        return ReturnResult.fail(ErrCodeEnum.ERR_UNKNOWN.code(), ErrCodeEnum.INTERNAL_SERVER_ERROR.msg());
    }


    /**
     * 基础异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ReturnResult exceptionHandle(BaseException e) {
        e.printStackTrace();
        log.error("捕获到异常：{}", e.getMessage());
        ReturnResult result = ReturnResult.fail(ErrCodeEnum.ERR_UNKNOWN.code(), ErrCodeEnum.INTERNAL_SERVER_ERROR.msg());
        if (StringUtils.hasText(e.getMessage())) {
            // 不属于国际化异常
            result = ReturnResult.fail(e.getCode(), e.getMessage());
        } else {
            try {
                String message = messageSourceHandler.getMessage(ErrCodeEnum.getEnum(e.getCode()).msg(), e.getParams());
                result = ReturnResult.fail(e.getCode(), message);
            } catch (NoSuchMessageException noSuchMessageException) {
                log.warn("请配置对应的国际化信息{}", e.getMessage());
            }
        }

        return result;
    }

    /**
     * 参数异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ReturnResult exceptionHandle(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.error("捕获到异常：{}", e.getMessage());
        log.error("入参格式错误，错误原因：", e);
        //得到所有的属性错误
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        fieldErrors.forEach(error -> {
            String[] codes = error.getCodes();
            if (codes != null) {
                String message = null;
                for (int i = 0; i < codes.length; i++) {
                    String code = codes[i];
                    if (code != null) {
                        try {
                            message = messageSourceHandler.getMessage(code);
                            break;
                        } catch (NoSuchMessageException messageException) {
                            if (i == codes.length - 1) {
                                log.warn("请配置对应的国际化信息{}", Arrays.toString(codes));
                            }
                        }
                    }
                }
                if (message != null) {
                    errorMsg.append(message).append(";");
                } else {
                    errorMsg.append(error.getDefaultMessage()).append(";");
                }
            }
        });
        return new ReturnResult(ErrCodeEnum.PARAM_VALIDATED_ERROR.code(), errorMsg.toString());
    }

}
