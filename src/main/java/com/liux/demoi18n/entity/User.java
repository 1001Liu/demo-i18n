package com.liux.demoi18n.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/28 11:08
 * @description :用户
 */
@Data
public class User {

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 18, max = 24)
    private Integer age;

    @NotBlank
    private String sex;

    @NotBlank
    private String tel;
}
