package com.example.core.utils.msm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author Andrew
 * @create 2019/02/18 10:39
 */
@Configuration
@EnableConfigurationProperties(MsgConfig.class)
@Slf4j
public class MessageService {

    private final MsgConfig msgConfig;

    @Autowired
    public MessageService(MsgConfig msgConfig) {
        this.msgConfig = msgConfig;
    }

    private JSONObject sendHttp(String url, String params) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        StringEntity entity = new StringEntity(params, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/x-www-form-urlencoded");
        setHeader(method);
        method.setEntity(entity);
        HttpResponse resp = client.execute(method);
        String result = EntityUtils.toString(resp.getEntity());
        client.close();
        JSONObject jsonRes = JSON.parseObject(result);
        return jsonRes;
    }

    private void setHeader(HttpPost method) throws Exception {
        Random random = new Random();
        long nonce = Math.abs(random.nextLong());
        long timpStamp = (System.currentTimeMillis() / 1000);
        String sign = DigestUtils.shaHex(msgConfig.getAPP_SECRIT() + nonce + timpStamp);
        method.addHeader("App-Key", msgConfig.getAPP_KEY());
        method.addHeader("Nonce", nonce + "");
        method.addHeader("Timestamp", timpStamp + "");
        method.addHeader("Signature", sign);
    }


    public String SendValidShortMessage(String phone) {
        try {
            String params = "mobile=" + phone + "&templateId=" + msgConfig.getTEMPLATE_ID() + "&region=" + msgConfig.getREGION();
            JSONObject obj = sendHttp(msgConfig.getSEND_CODE_URL(), params);
            if (obj != null && obj.get("code") != null && "200".equals(obj.get("code").toString())) {
                return (String) obj.get("sessionId");
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }

    public boolean validCode(String sessionId, String code) {
        try {
            String params = "sessionId=" + sessionId + "&code=" + code;

            JSONObject obj = sendHttp(msgConfig.getVERIF_CODE_URL(), params);
            if ((obj != null) && (obj.get("code") != null) && ("200".equals(obj.get("code").toString()))
                    && ("true".equals(obj.get("success").toString()))) {

                log.debug("verify:" + params + ": obj:" + obj.toString());
                return true;

            } else {
                log.debug("verify:" + params + ": obj:" + obj.toString());
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
