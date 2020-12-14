package com.example.core.enums;

import lombok.Getter;

/**
 * @author JIA
 * 自定义成功枚举类
 */
@Getter
public enum SuccessEnum {

    /* 系统级别 */

    SUCCESS_REQUEST(200, "服务请求成功"),
    SUCCESS_CREATED(201, "请求成功,服务器创建资源"),
    SUCCESS_ACCEPTED(202, "请求成功,服务器尚未处理"),
    SUCCESS_NON_AUTHORITATIVE(203,"请求成功,信息非授权"),
    SUCCESS_NO_CONTENT(204,"请求成功,无内容"),
    SUCCESS_RESET_CONTENT(205,"请求成功,内容重置"),
    SUCCESS_PARTIAL_CONTENT(206,"请求成功,部分内容已处理")

    ;

    private final Integer key;
    private final String msg;

    SuccessEnum(Integer key, String msg) {
        this.key = key;
        this.msg = msg;
    }

}
