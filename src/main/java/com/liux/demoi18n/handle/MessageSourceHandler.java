package com.liux.demoi18n.handle;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/24 11:33
 * @description :国际化消息处理
 */
@Component
public class MessageSourceHandler {
    @Resource
    private ResourceBundleMessageSource resourceBundleMessageSource;


    public String getMessage(String code) {
        return resourceBundleMessageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] params) {
        return resourceBundleMessageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }
}
