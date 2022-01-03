package com.study.plugin.translate.service;

import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.YoudaoTranslateResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.util.HashMap;
import java.util.Map;

@Service
public final class YoudaoTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "https://openapi.youdao.com/api";

    private String APP_ID = appInfoService.get(YOUDAO_APP_ID_SAVE_KEY);

    private String APP_SECRET = appInfoService.get(YOUDAO_APP_SECRET_SAVE_KEY);

    private String DIGEST_KEY = "SHA-256";

    public YoudaoTranslateRestService() {
        super();
        if (!isInit.get()) {
            super.init();
        }
    }


    @Override
    public String translate(String word) {
        Map<String, String> params = getParams(word);
        StringBuilder builder = new StringBuilder(HOST + "?");
        params.entrySet().forEach(ent -> {
            builder.append(ent.getKey() + "=" + ent.getValue() + "&");
        });
        String requestUrl = builder.toString();
        requestUrl = requestUrl.substring(0, requestUrl.length() - 1);
        YoudaoTranslateResult result = restTemplate.getForObject(requestUrl, YoudaoTranslateResult.class);
        if (result.getErrorCode().equals("0")) {
            return result.getTranslation().get(0);
        }
        return null;
    }

    private Map<String, String> getParams(String word) {
        Map<String, String> params = new HashMap<>();
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", "auto");
        params.put("to", "en");
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_ID + truncate(word) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr, DIGEST_KEY);
        params.put("appKey", APP_ID);
        params.put("q", word);
        params.put("salt", salt);
        params.put("sign", sign);
        return params;
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}
