package com.example.core.utils.wechat.browser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

/**
 * @author 10696
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WxUserInfoVo {

    private String openid;

    private String nickname;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private List<String> privilege;

    private String unionid;


}
