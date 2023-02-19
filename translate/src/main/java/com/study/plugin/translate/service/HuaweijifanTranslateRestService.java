package com.study.plugin.translate.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.HuaweijifanTranslateResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Service
public final class HuaweijifanTranslateRestService extends TranslateRestService implements PluginAppKeys {
    @Setter
    private String PROJECT_ID = appInfoService.get(HUAWEIJIFAN_PROJECT_ID_SAVE_KEY);
    @Setter
    private String APP_SECRET = appInfoService.get(HUAWEIJIFAN_APP_SECRET_SAVE_KEY);
    @Setter
    private String APP_ID = appInfoService.get(HUAWEIJIFAN_APP_ID_SAVE_KEY);
    private String HOST_PAR = "https://nlp-ext.cn-north-4.myhuaweicloud.com/v1/%s/machine-translation/text-translation";
    private static final String URL_PAR = "/v1/%s/machine-translation/text-translation/\n";
    private static final String messageDigestAlgorithm = "SDK-HMAC-SHA256";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
    private static final String INTERNATIONAL_PROTOCOL = "TLSv1.2";


    static {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public String translate(String word) {
        String body = JSONUtil.toJsonStr(getParams(word));
        String date = sdf.format(new Date());
        String signatureResult = computeAuthorization(body, date);
        HttpRequest request = HttpRequest.post(String.format(HOST_PAR, PROJECT_ID))
                .header("Authorization", signatureResult)
                .header("Host", "nlp-ext.cn-north-4.myhuaweicloud.com")
                .header("X-Sdk-Date", date)
                .contentType("application/json")
                .setSSLProtocol(INTERNATIONAL_PROTOCOL)
                .body(body);
        HttpResponse response = request.execute();
        if (response.isOk()) {
            return JSONUtil.toBean(response.body(), HuaweijifanTranslateResult.class).getTranslatedText();
        }
        return null;
    }

    @NotNull
    private String computeAuthorization(String body, String date) {
        String messageDigestContent = toHex(hash(body));
        String canonicalRequest = "POST\n" +
                String.format(URL_PAR, PROJECT_ID) +
                "\n" +
                "content-type:application/json\n" +
                "host:nlp-ext.cn-north-4.myhuaweicloud.com\n" +
                String.format("x-sdk-date:%s\n", date) +
                "\n" +
                "content-type;host;x-sdk-date\n" +
                messageDigestContent;
        String stringToSign = messageDigestAlgorithm + "\n" + date + "\n" + toHex(hash(canonicalRequest));
        byte[] signature = sign(stringToSign.getBytes(StandardCharsets.UTF_8), APP_SECRET.getBytes(StandardCharsets.UTF_8));
        String credential = "Access=" + APP_ID;
        String signerHeaders = "SignedHeaders=" + "content-type;host;x-sdk-date";
        String signatureHeader = "Signature=" + toHex(signature);
        String signatureResult = messageDigestAlgorithm + " " + credential + ", " + signerHeaders + ", " + signatureHeader;
        return signatureResult;
    }

    private String toHex(byte[] data) {
        StringBuffer sbuff = new StringBuffer(data.length * 2);
        byte[] var2 = data;
        int var3 = data.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            byte bye = var2[var4];
            String hexStr = Integer.toHexString(bye);
            if (hexStr.length() == 1) {
                sbuff.append("0");
            } else if (hexStr.length() == 8) {
                hexStr = hexStr.substring(6);
            }

            sbuff.append(hexStr);
        }

        return sbuff.toString().toLowerCase(Locale.getDefault());
    }

    private byte[] sign(byte[] data, byte[] key) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            return mac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException var5) {
            return new byte[0];
        }
    }

    private byte[] hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            return md.digest();
        } catch (NoSuchAlgorithmException var3) {
            return new byte[0];
        }
    }

    private Map<String, Object> getParams(String word) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", word);
        params.put("from", "auto");
        params.put("to", "en");
        return params;
    }
}
