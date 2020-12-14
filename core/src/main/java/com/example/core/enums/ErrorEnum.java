package com.example.core.enums;

import lombok.Getter;

/**
 * @author JIA
 * 自定义错误枚举类
 */

@Getter
public enum ErrorEnum {

    /* 系统级别 */

    ERROR_REQUEST(100, "请求失败"),
    ERROR_BAD_REQUEST(400, "请求语法错误"),
    ERROR_UNAUTHORIZED(401, "未授权"),
    ERROR_FORBIDDEN(403, "服务器拒绝请求"),
    ERROR_NOT_FOUND(404, "请求资源不存在"),
    ERROR_METHOD_NOT_AllOWED(405, "指定的请求方法,被禁用"),
    ERROR_REQUEST_TIMEOUT(408, "请求超时"),
    ERROR_REQUEST_ENTITY_TOO_LARGE(413, "请求实体过大,超出处理能力"),
    ERROR_REQUEST_URI_TOO_LONG(414, "请求URL太长,拒绝访问"),

    ;
    private final Integer key;
    private final String msg;


    ErrorEnum(Integer key, String msg) {
        this.key = key;
        this.msg = msg;
    }

}
