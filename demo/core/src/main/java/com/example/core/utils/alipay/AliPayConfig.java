package com.example.core.utils.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
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
     * @return 接口对象
     */
    public AlipayClient alipayClient(String aliPayPublicKey) {
        // 1、获得初始化的AliPayClient
        String serverUrl = aliPayProperties.getGatewayUrl();
        String appId = aliPayProperties.getAppId();
        String privateKey = aliPayProperties.getPrivateKey();
        String format = "json";
        String charset = aliPayProperties.getCharset();
        String signType = aliPayProperties.getSignType();
        return new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, aliPayPublicKey, signType);
    }

    /**
     * 支付
     *
     * @param aliPayBean
     * @return 实例对象
     * @throws AlipayApiException
     */
    public String pay(AliPayBean aliPayBean) throws AlipayApiException {

        // 2、设置请求参数
        String aliPayPublicKey = aliPayProperties.getPublicKey();
        String returnUrl = aliPayProperties.getReturnUrl();
        String notifyUrl = aliPayProperties.getNotifyUrl();
        // 构造请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(aliPayBean));
        // 连接参数
        AlipayClient alipayClient = alipayClient(aliPayPublicKey);
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        System.out.println(result);
        return result;
    }

    /**
     * 退款
     *
     * @param aliPayRefundBean
     * @return 实例对象
     * @throws AlipayApiException
     */
    public String refund(AliPayRefundBean aliPayRefundBean) throws AlipayApiException {
        // 2、设置请求参数
        String aliPayPublicKey = aliPayProperties.getPublicKey();
        // 构造请求
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(JSON.toJSONString(aliPayRefundBean));
        //连接参数
        AlipayClient alipayClient = alipayClient(aliPayPublicKey);
        // 3、请求支付宝进行退款，并获取支付结果
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        System.out.println(response);
        return response.getMsg();
    }

}