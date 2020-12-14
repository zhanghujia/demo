package com.example.business.service;

import com.alipay.api.AlipayApiException;
import com.example.core.utils.alipay.AliPayBean;
import com.example.core.utils.alipay.AliPayRefundBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 10696
 */

@Component
public interface AliPayService {

    /**
     * 支付宝支付
     *
     * @param aliPayBean 支付实体
     * @return 实例对象
     */
    Map<String, String> alipay(AliPayBean aliPayBean) throws AlipayApiException;

    /**
     * 支付成功回调
     *
     * @param outTradeNo  订单号
     * @param tradeNo     支付宝交易号
     * @param tradeStatus 支付状态
     */
    void returnNotifyUrlInfo(String outTradeNo, String tradeNo, String tradeStatus);

    /**
     * 支付宝退款
     *
     * @param aliPayRefundBean 支付退款实体
     * @return 实例对象
     * @throws AlipayApiException 阿里支付异常
     */
    Map<String, String> refund(AliPayRefundBean aliPayRefundBean) throws AlipayApiException;

}
