package com.study.plugin.translate.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.AlijifanResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.UUID;

/**
 * @author jiayq
 * @Date 2023-02-19
 */
@Service
public final class AlijifanTranslateRestService extends TranslateRestService implements PluginAppKeys {
    @Setter
    private String APP_ID = appInfoService.get(ALIJIFAN_APP_ID_SAVE_KEY);
    @Setter
    private String APP_SECRET = appInfoService.get(ALIJIFAN_APP_SECRET_SAVE_KEY);
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    static {
        df.setTimeZone(new SimpleTimeZone(0, "UTC"));
    }

    @Override
    public String translate(String word) {
        try {
            String body = String.format("SourceLanguage=auto&SourceText=%s&Scene=general&TargetLanguage=en&FormatType=text", URLEncoder.encode(word, "UTF-8"));
            String url = getUrl(word);
            HttpRequest request = HttpRequest.post(url)
                    .header("x-acs-action", "TranslateGeneral")
                    .header("host", "mt.cn-hangzhou.aliyuncs.com")
                    .header("x-acs-version", "2018-10-12")
                    .header("user-agent", "AlibabaCloud (Windows 10; amd64) Java/11.0.11+9-LTS-194 tea-util/0.2.6 TeaDSL/1")
                    .contentType("application/x-www-form-urlencoded")
                    .body(body.getBytes("UTF-8"));
            HttpResponse response = request.execute();
            if (response.isOk()) {
//            {"RequestId":"4FC1889E-9007-5222-91AF-DA3C4C670F6E","Data":{"WordCount":"2","Translated":"Hello"},"Code":"200"}
                return JSONUtil.toBean(response.body(), AlijifanResult.class).getData().getTranslated();
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
        return null;
    }

    @NotNull
    private String getUrl(String word) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String date = df.format(new Date());
        String SignatureNonce = getSignatureNonce();
        String Signature = getSignature(word, date, SignatureNonce);
        String url = "HTTPS://mt.cn-hangzhou.aliyuncs.com/?SignatureVersion=1.0&Action=TranslateGeneral&Format=json&" +
                String.format("SignatureNonce=%s&", URLEncoder.encode(SignatureNonce, "UTF-8")) +
                String.format("Version=2018-10-12&AccessKeyId=%s&", APP_ID) +
                String.format("Signature=%s&SignatureMethod=HMAC-SHA1&", URLEncoder.encode(Signature, "UTF-8")) +
                String.format("Timestamp=%s", URLEncoder.encode(date, "UTF-8"));
        return url;
    }

    @NotNull
    private static String getSignatureNonce() {
        StringBuffer uniqueNonce = new StringBuffer();
        UUID uuid = UUID.randomUUID();
        uniqueNonce.append(uuid);
        uniqueNonce.append(System.currentTimeMillis());
        uniqueNonce.append(Thread.currentThread().getId());
        String SignatureNonce = uniqueNonce.toString();
        return SignatureNonce;
    }

    private String getSignature(String word, String date, String SignatureNonce) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(percentEncode("AccessKeyId")).append("=").append(percentEncode(APP_ID))
                .append("&").append(percentEncode("Action")).append("=").append(percentEncode("TranslateGeneral"))
                .append("&").append(percentEncode("Format")).append("=").append(percentEncode("json"))
                .append("&").append(percentEncode("FormatType")).append("=").append(percentEncode("text"))
                .append("&").append(percentEncode("Scene")).append("=").append(percentEncode("general"))
                .append("&").append(percentEncode("SignatureMethod")).append("=").append(percentEncode("HMAC-SHA1"))
                .append("&").append(percentEncode("SignatureNonce")).append("=").append(percentEncode(SignatureNonce))
                .append("&").append(percentEncode("SignatureVersion")).append("=").append(percentEncode("1.0"))
                .append("&").append(percentEncode("SourceLanguage")).append("=").append(percentEncode("auto"))
                .append("&").append(percentEncode("SourceText")).append("=").append(percentEncode(word))
                .append("&").append(percentEncode("TargetLanguage")).append("=").append(percentEncode("en"))
                .append("&").append(percentEncode("Timestamp")).append("=").append(percentEncode(date))
                .append("&").append(percentEncode("Version")).append("=").append(percentEncode("2018-10-12"));
        StringBuilder sign = new StringBuilder();
        sign.append("POST").append("&").append(percentEncode("/"))
                .append("&").append(percentEncode(stringBuilder.toString()));
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec((APP_SECRET + "&").getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(sign.toString().getBytes("UTF-8"));
        String Signature = DatatypeConverter.printBase64Binary(signData);
        return Signature;
    }

    public String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }
}
