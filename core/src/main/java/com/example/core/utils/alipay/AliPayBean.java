package com.example.core.utils.alipay;

import lombok.Data;

/**
 * @author JIA
 */

@Data
public class AliPayBean {

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 订单名称
     */
    private String subject;

    /**
     * 付款金额
     */
    private String total_amount;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 超时时间参数
     */
    private String timeout_express = "10m";

    /**
     * 产品编号
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";


}
