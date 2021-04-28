package com.example.web.controller;

import com.example.core.utils.wechat.browser.AccessTokenVo;
import com.example.core.utils.wechat.browser.WxUrlVo;
import com.example.core.utils.wechat.browser.WxUserInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author 10696
 */

@Controller
@RequestMapping("/api")
public class WxApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxUrlVo wxUrlVo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/callback")
    @ResponseBody
    public WxUserInfoVo callback(String code, String state) throws JsonProcessingException {
        String forObject = restTemplate.getForObject(wxUrlVo.getAccessToken(code, state), String.class);
        AccessTokenVo accessTokenVO = objectMapper.readValue(forObject, AccessTokenVo.class);
        //获取userInfo
        String accessToken = accessTokenVO.getAccess_token();
        String openid = accessTokenVO.getOpenid();
        String json = restTemplate.getForObject(wxUrlVo.getUserInfo(accessToken, openid), String.class);
        assert json != null;
        //编码
        String userinfoJson = new String(json.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        WxUserInfoVo wxUserInfoVo = objectMapper.readValue(userinfoJson, WxUserInfoVo.class);
        return wxUserInfoVo;
    }

    @GetMapping("/login")
    public String getWxCode() throws UnsupportedEncodingException {
        String state = "kuaipaotiku";
        return "redirect:" + wxUrlVo.getWxQrCode(state);
    }

}

