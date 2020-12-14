package com.example.core.utils.msm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Andrew
 * @create 2019/02/18 10:40
 */
@Data
@ConfigurationProperties(prefix = "rong")
public class MsgConfig {

    private String TEMPLATE_ID;

    private String REGION;

    private String SEND_CODE_URL;

    private String APP_SECRIT;

    private String APP_KEY;

    private String VERIF_CODE_URL;

}
