package com.study.plugin.translate.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.TengxunjifanResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import lombok.Setter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Service
public final class TengxunjifanTranslateRestService extends TranslateRestService implements PluginAppKeys {
    private String URL = "https://tmt.tencentcloudapi.com/";
    private final static Charset UTF8 = StandardCharsets.UTF_8;
    private final static String CT_JSON = "application/json; charset=utf-8";
    private final static String SERVICE = "tmt";
    private final static String HOST = "tmt.tencentcloudapi.com";
    private final static String REGION = "ap-beijing";
    private final static String ACTION = "TextTranslate";
    private final static String VERSION = "2018-03-21";
    private final static String ALGORITHM = "TC3-HMAC-SHA256";
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    @Setter
    private String APP_ID = appInfoService.get(TENGXUNJIFAN_APP_ID_SAVE_KEY);
    @Setter
    private String APP_SECRET = appInfoService.get(TENGXUNJIFAN_APP_SECRET_SAVE_KEY);

    @Override
    public String translate(String word) {
        String payload = JSONUtil.toJsonStr(getParams(word));
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        try {
            HttpRequest request = HttpRequest.post(URL)
                    .header("Authorization", computeAuth(payload, timestamp))
                    .header("Content-Type", CT_JSON)
                    .header("Host", HOST)
                    .header("X-TC-Action", ACTION)
                    .header("X-TC-Timestamp", timestamp)
                    .header("X-TC-Version", VERSION)
                    .header("X-TC-Region", REGION)
                    .body(payload);
            HttpResponse response = request.execute();
            if (response.isOk()) {
                return JSONUtil.toBean(response.body(), TengxunjifanResult.class).getResponse().getTargetText();
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }
        return null;
    }

    private byte[] hmac256(byte[] key, String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    private String sha256Hex(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    private String computeAuth(String payload, String timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
        // 注意时区，否则容易出错
        SDF.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = SDF.format(new Date(Long.parseLong(timestamp + "000")));

        // ************* 步骤 1：拼接规范请求串 *************
        String httpRequestMethod = "POST";
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:application/json; charset=utf-8\n"
                + "host:" + HOST + "\n" + "x-tc-action:" + ACTION.toLowerCase() + "\n";
        String signedHeaders = "content-type;host;x-tc-action";

        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;

        // ************* 步骤 2：拼接待签名字符串 *************
        String credentialScope = date + "/" + SERVICE + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = ALGORITHM + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("TC3" + APP_SECRET).getBytes(UTF8), date);
        byte[] secretService = hmac256(secretDate, SERVICE);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();

        // ************* 步骤 4：拼接 Authorization *************
        String authorization = ALGORITHM + " " + "Credential=" + APP_ID + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        return authorization;
    }

    private Map<String, Object> getParams(String word) {
        Map<String, Object> params = new HashMap<>();
        params.put("SourceText", word);
        params.put("Source", "auto");
        params.put("Target", "en");
        params.put("ProjectId", 0);
        return params;
    }

}
