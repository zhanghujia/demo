package com.example.core.utils.wechat.browser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * @author 10696
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AccessTokenVo {

    private String access_token;

    private String expires_in;

    private String refresh_token;

    private String openid;

    private String scope;

    private String unionid;

}
