package com.study.plugin.translate.service;

import com.intellij.openapi.application.ApplicationManager;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class TranslateRestService {

    protected RestTemplate restTemplate;

    protected volatile AtomicBoolean isInit = new AtomicBoolean(Boolean.FALSE);

    protected TranslateAppInfoService appInfoService = ApplicationManager.getApplication().getService(TranslateAppInfoService.class);

    protected synchronized void init() {
        // 如果已经初始化了，直接结束
        if (isInit.get()) {
            return;
        }
        // 连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(4);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(2);
        // 我们目前只有2个在线翻译可用，每个翻译2个线程用于Rest请求，所以设置最大连接4，每个翻译api是2个并发
        // 客户端构造器
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        // 创建restTemplate
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setHttpClient(httpClientBuilder.build());
        httpRequestFactory.setConnectTimeout(6000);
        httpRequestFactory.setConnectTimeout(6000);
        httpRequestFactory.setReadTimeout(12000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        this.restTemplate = restTemplate;
        isInit.compareAndSet(Boolean.FALSE, Boolean.TRUE);
    }

    /**
     * 加密
     *
     * @param string
     * @return
     */
    protected static String getDigest(String string, String key) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance(key);
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public abstract String translate(String word);
}
