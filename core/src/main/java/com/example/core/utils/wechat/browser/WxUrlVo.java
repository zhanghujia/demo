package com.example.core.utils.wechat.browser;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 10696
 */
@Component
public class WxUrlVo {

    String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
            "?appid=%s" +
            "&redirect_uri=%s" +
            "&response_type=code" +
            "&scope=snsapi_login" +
            "&state=%s" +
            "#wechat_redirect";

    String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=%s" +
            "&secret=%s" +
            "&code=%s" +
            "&grant_type=authorization_code";

    String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token=%s" +
            "&openid=%s";

    public String getWxQrCode(String state) throws UnsupportedEncodingException {
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        URLEncoder.encode(redirectUrl, StandardCharsets.UTF_8);
        return String.format(baseUrl, ConstantWxUtils.WX_OPEN_APP_ID, redirectUrl, state);
    }

    public String getAccessToken(String code, String state) {
        return String.format(accessTokenUrl, ConstantWxUtils.WX_OPEN_APP_ID, ConstantWxUtils.WX_OPEN_APP_SECRET, code);
    }

    public String getUserInfo(String accessToken, String openid) {
        return String.format(userInfoUrl, accessToken, openid);
    }
}
