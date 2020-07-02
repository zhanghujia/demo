package com.example.business.service;

import com.alipay.api.AlipayApiException;
import com.example.core.utils.alipay.AliPayBean;
import com.example.core.utils.alipay.AliPayBean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 10696
 */

@Component
public interface AliPayService {

    /**
     * 阿里云支付
     *
     * @param aliPayBean
     * @return
     */
    Map<String, String> alipay(AliPayBean aliPayBean) throws AlipayApiException;

    /**
     * 支付成功回调
     *
     * @param request
     * @param response
     */
    void returnNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response);
}
