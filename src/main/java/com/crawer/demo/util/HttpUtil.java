package com.crawer.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 2:01 下午
 * @since v3.0
 */
@Component
public class HttpUtil {

    private static final String CHARTSET = "utf8";
    private PoolingHttpClientConnectionManager pm;

    public HttpUtil() {
        this.pm = new PoolingHttpClientConnectionManager();
        this.pm.setMaxTotal(100);
        this.pm.setDefaultMaxPerRoute(10);
    }


    private String doGet(String url, Map<String, Object> params, String chartSet) {
        CloseableHttpClient aDefault = HttpClients.custom().setConnectionManager(pm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.addHeader("Sec-Fetch-Site", "same-site");
        httpGet.addHeader("Sec-Fetch-Mode", "cors");
        httpGet.addHeader("Sec-Fetch-Dest", "empty");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;
        try {
            response = aDefault.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, chartSet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }


    public String doGetImage(String url) {
        CloseableHttpClient aDefault = HttpClients.custom().setConnectionManager(pm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;
        try {
            response = aDefault.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 文件后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    // 文件名
                    String fileName = UUID.randomUUID().toString() + extName;
                    entity.writeTo(new FileOutputStream(new File("/Users/mason/Desktop/pic/" + fileName)));
                    return fileName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    private RequestConfig getConfig() {
        return RequestConfig.custom()
            .setConnectTimeout(1000)// 创建连接的最长时间
            .setSocketTimeout(10000)
            .setConnectionRequestTimeout(500) // 获取连接的最长时间
            .build();
    }

    public String doGet(String url) {
        return doGet(url, null, CHARTSET);
    }
}
