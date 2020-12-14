package com.example.core.enums;

import lombok.Getter;

/**
 * @author JIA
 * 自定义消息枚举类
 */
@Getter
public enum InfoEnum {

    NULL_PARAMETER(400, "请求参数错误"),

    LOGIN_USER_NAME_ERROR(201, "用户名错误,此用户不存在"),

    LOGIN_PASSWORD_ERROR(202, "密码错误,请重新输入"),

    LOGIN_REGISTER_RE(203,"用户已存在,不可重复注册"),

    TOKEN_EXPIRED(203, "token过期或token不存在"),

    AUTHCODE_ERROR(204,"验证码输入错误"),

    ;

    private final Integer key;
    private final String msg;

    InfoEnum(Integer key, String msg) {
        this.key = key;
        this.msg = msg;
    }

}
