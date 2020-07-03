package com.example.core.utils.alipay;

import lombok.Data;

@Data
public class AliPayRefundBean {

    /**
     * 商品订单号
     */
    private String out_trade_no;
    /**
     * 支付宝交易号
     */
    private String trade_no;
    /**
     * 退款金额
     */
    private Double refund_amount;

}

