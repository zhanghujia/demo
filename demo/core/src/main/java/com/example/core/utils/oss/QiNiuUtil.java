package com.example.core.utils.oss;

import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JIA
 */

@Data
@Component
@ConditionalOnProperty(prefix = "oss", name = "access-key")
public class QiNiuUtil {

    @Value("${oss.access-key}")
    private String accessKey;

    @Value("${oss.secret-key}")
    private String secretKey;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${oss.file-domain}")
    private String fileDomain;

    private Auth auth;

    private Auth getAuth() {
        if (Objects.isNull(auth)) {
            auth = Auth.create(getAccessKey(), getSecretKey());
        }
        return auth;
    }

    public Map<String, Object> getUpToken() {
        Map<String, Object> map = new HashMap<>(2);
        String upToken = getAuth().uploadToken(bucket);
        map.put("upToken", upToken);
        map.put("domain", fileDomain);
        return map;
    }


}
