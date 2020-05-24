package com.example.core.utils.wl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * @author JIA
 */
@Data
@Component
public class WlUtil {

    @Value("${wl.host}")
    private String host;

    @Value("${wl.path}")
    private String path;

    @Value("${wl.app-code}")
    private String appCode;


    public static String write(String no, String type,
                                String host, String path, String appCode)
            throws IOException {

        String urlSend = host + path + "?no=" + no + "&type=" + type;
        URL url = new URL(urlSend);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appCode);

        int httpCode = httpURLConnection.getResponseCode();

        String json = read(httpURLConnection.getInputStream());
        return json;
    }

    private static String read(InputStream is) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), StandardCharsets.UTF_8);
            buffer.append(line);
        }
        br.close();
        return buffer.toString();
    }

}
