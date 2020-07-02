package com.example.core.utils.alipay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author JIA
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayProperties {

    private String appId;

    private String privateKey;

    private String publicKey;

    private String notifyUrl;

    private String returnUrl;

    private String signType;

    private String charset;

    private String gatewayUrl;

}
