package com.example.business.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.business.service.AliPayService;
import com.example.core.utils.alipay.AliPayBean;
import com.example.core.utils.alipay.AliPayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 10696
 */

@Service
public class AliPayServiceImpl implements AliPayService {

    private final AliPayConfig aliPayConfig;


    public AliPayServiceImpl(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    @Override
    public Map<String, String> alipay(AliPayBean aliPayBean) throws AlipayApiException {
        Map<String, String> map = new HashMap<>();
        String pay = aliPayConfig.pay(aliPayBean);
        map.put("aliPay", pay);
        return map;
    }

    @Override
    public void returnNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response) {
        //商户订单号
        String outTradeNo = request.getParameter("out_trade_no");

        //商户订单号
        String tradeNo = request.getParameter("trade_no");

        //贸易状态
        String trade_status = request.getParameter("trade_status");
        String trade = "TRADE_SUCCESS";
        if (!trade.equals(trade_status)) {
            throw new RuntimeException("交易出错，支付回调失败");
        }
    }


}
