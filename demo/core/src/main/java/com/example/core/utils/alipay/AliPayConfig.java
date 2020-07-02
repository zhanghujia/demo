package com.example.core.utils.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Component;

/**
 * @author JIA
 */

@Component
public class AliPayConfig {

    private final AliPayProperties aliPayProperties;

    public AliPayConfig(AliPayProperties aliPayProperties) {
        this.aliPayProperties = aliPayProperties;
    }

    /**
     * 支付宝支付配置
     *
     * @param aliPayBean
     * @return 实例对象
     * @throws AlipayApiException
     */
    public String pay(AliPayBean aliPayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = aliPayProperties.getGatewayUrl();
        String appId = aliPayProperties.getAppId();
        String privateKey = aliPayProperties.getPrivateKey();
        String format = "json";
        String charset = aliPayProperties.getCharset();
        String alipayPublicKey = aliPayProperties.getPublicKey();
        String signType = aliPayProperties.getSignType();
        String returnUrl = aliPayProperties.getReturnUrl();
        String notifyUrl = aliPayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(aliPayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }

}
