package com.study.plugin.translate.service;

import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.BaiduTranslateResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

@Service
public final class BaiduTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String APP_ID = appInfoService.get(BAIDU_APP_ID_SAVE_KEY);

    private String APP_SECRET = appInfoService.get(BAIDU_APP_SECRET_SAVE_KEY);

    private String DIGEST_KEY = "MD5";

    public BaiduTranslateRestService() {
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
        BaiduTranslateResult result = restTemplate.getForObject(requestUrl, BaiduTranslateResult.class);
        if (Objects.isNull(result.getError_code()) && !CollectionUtils.isEmpty(result.getTrans_result())) {
            return result.getTrans_result().get(0).getDst();
        }
        return null;
    }

    private Map<String, String> getParams(String word) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", word);
        params.put("from", "zh");
        params.put("to", "en");
        params.put("appid", APP_ID);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = APP_ID + word + salt + APP_SECRET; // 加密前的原文
        params.put("sign", getDigest(src, DIGEST_KEY).toLowerCase());
        return params;
    }

}
