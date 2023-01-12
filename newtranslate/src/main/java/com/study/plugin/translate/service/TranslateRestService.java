package com.study.plugin.translate.service;

import com.intellij.openapi.application.ApplicationManager;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public abstract class TranslateRestService {

    protected RestTemplate restTemplate;

    protected OkHttpClient okHttpClient;

    protected volatile AtomicBoolean isInit = new AtomicBoolean(Boolean.FALSE);

    protected TranslateAppInfoService appInfoService = ApplicationManager.getApplication().getService(TranslateAppInfoService.class);

    protected synchronized void init() {
        // 如果已经初始化了，直接结束
        if (isInit.get()) {
            return;
        }
        // okhttpClient

        okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(Objects.requireNonNull(sslSocketFactory()), x509TrustManager())
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

//        // 连接池
//        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
//        poolingHttpClientConnectionManager.setMaxTotal(4);
//        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(2);
//        // 我们目前只有2个在线翻译可用，每个翻译2个线程用于Rest请求，所以设置最大连接4，每个翻译api是2个并发
//        // 客户端构造器
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
//        // 创建restTemplate
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setHttpClient(httpClientBuilder.build());
//        httpRequestFactory.setConnectTimeout(6000);
//        httpRequestFactory.setConnectTimeout(6000);
//        httpRequestFactory.setReadTimeout(12000);
        this.restTemplate = new RestTemplate(httpRequestFactory());
        isInit.compareAndSet(Boolean.FALSE, Boolean.TRUE);
    }

    private ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    private X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    private SSLSocketFactory sslSocketFactory() {
        try {
            //信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // TODO logger
        }
        return null;
    }

    private ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
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
