package com.example.business.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.business.service.AliPayService;
import com.example.core.utils.alipay.AliPayBean;
import com.example.core.utils.alipay.AliPayProcessor;
import com.example.core.utils.alipay.AliPayRefundBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 10696
 */

@Service
public class AliPayServiceImpl implements AliPayService {

    private final AliPayProcessor aliPayProcessor;


    public AliPayServiceImpl(AliPayProcessor aliPayProcessor) {
        this.aliPayProcessor = aliPayProcessor;
    }

    @Override
    public Map<String, String> alipay(AliPayBean aliPayBean) throws AlipayApiException {
        Map<String, String> map = new HashMap<>();
        String pay = aliPayProcessor.pay(aliPayBean);
        map.put("aliPay", pay);
        return map;
    }

    @Override
    public void returnNotifyUrlInfo(String outTradeNo, String tradeNo, String tradeStatus) {
        //判断交易状态
        String trade = "TRADE_SUCCESS";
        if (!trade.equals(tradeStatus)) {
            throw new RuntimeException("交易出错，支付回调失败");
        }

    }

    @Override
    public Map<String, String> refund(AliPayRefundBean aliPayRefundBean) throws AlipayApiException {
        Map<String, String> map = new HashMap<>(2);
        String refund = aliPayProcessor.refund(aliPayRefundBean);
        map.put("refund", refund);
        return map;
    }

}
