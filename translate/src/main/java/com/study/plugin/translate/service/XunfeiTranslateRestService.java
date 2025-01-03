package com.study.plugin.translate.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.*;
import com.study.plugin.translate.utils.PluginAppKeys;
import lombok.Setter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Service
public final class XunfeiTranslateRestService extends TranslateRestService implements PluginAppKeys {
    private String URL = "https://itrans.xfyun.cn/v2/its";
    private final static Charset UTF8 = StandardCharsets.UTF_8;
    private final static String CT_JSON = "application/json; charset=utf-8";
    private final static String ACCEPT = "application/json,version=1.0";
    private final static String HOST = "itrans.xfyun.cn";
    private final static String FROM = "cn";
    private final static String TO = "en";
    private final static String ALGORITHM = "hmac-sha256";
    private final static SimpleDateFormat SDF = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    @Setter
    private String APP_ID = appInfoService.get(XUNFEI_APP_ID_SAVE_KEY);
    @Setter
    private String APP_KEY = appInfoService.get(XUNFEI_APP_KEY_SAVE_KEY);
    @Setter
    private String APP_SECRET = appInfoService.get(XUNFEI_APP_SECRET_SAVE_KEY);

    @Override
    public String translate(String word) {
        String payload = JSONUtil.toJsonStr(getParams(word));
        SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dateD = new Date();
        String date = SDF.format(dateD);
        try {
            HttpResponse response = HttpUtil.createPost(URL).
                    header("Authorization", getAuthorization(date, getDigestBase64(payload))).
                    header("Content-Type", CT_JSON).
                    header("Accept", ACCEPT).
                    header("Host", HOST).
                    header("Date", date).
                    header("Digest", getDigestBase64(payload)).
                    body(payload).
                    execute();
            if (response.isOk()) {
                return JSONUtil.toBean(response.body(), XunfeiTranslateResult.class).getData().getResult().getTransResult().getDst();
            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }

    private String getDigestBase64(String body) throws NoSuchAlgorithmException {
        return "SHA-256=" + Base64.getEncoder().encodeToString(sha256(body));
    }

    private String getAuthorization(String date, String digestBase64) throws Exception {
        StringBuilder builder = new StringBuilder("host: ").append(HOST).append("\n").
                append("date: ").append(date).append("\n").
                append("POST ").append(URL.substring(URL.indexOf(HOST) + HOST.length())).append(" HTTP/1.1").append("\n").
                append("digest: ").append(digestBase64);
        String sha = hmacsign(builder.toString(), APP_SECRET);
        return String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", APP_KEY, ALGORITHM, "host date request-line digest", sha);
    }

    private String hmacsign(String signature, String apiSecret) throws Exception {
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(UTF8), mac.getAlgorithm());
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(signature.getBytes(UTF8));
        return Base64.getEncoder().encodeToString(hexDigits);
    }

    private byte[] sha256(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(s.getBytes(UTF8));
    }

    private XunfeiTranslateRequest getParams(String word) {
        return XunfeiTranslateRequest.builder().
                common(XunfeiTranslateCommon.builder().appId(APP_ID).build()).
                data(XunfeiTranslateData.builder().text(word).build()).
                business(XunfeiTranslateBusiness.builder().to(TO).from(FROM).build()).
                build();
    }

}
