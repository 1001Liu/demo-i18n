package com.liux.demoi18n.controller;

import com.liux.demoi18n.common.exception.BaseException;
import com.liux.demoi18n.common.result.ReturnResult;
import com.liux.demoi18n.constant.ErrCodeEnum;
import com.liux.demoi18n.entity.User;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/28 11:08
 * @description :
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("add")
    @ResponseBody
    public ReturnResult add(@RequestBody @Validated User user) {
        return ReturnResult.success(user);
    }

    @GetMapping("getUser")
    @ResponseBody
    @SneakyThrows
    public ReturnResult getUser(@RequestParam String userName) {
        throw new BaseException(ErrCodeEnum.USER_NOT_EXIST.code(), new Object[]{userName});
    }
}
