package com.study.plugin.translate.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.CaiyunTranslateResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Setter;

@Service
public final class CaiyunTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "http://api.interpreter.caiyunai.com/v1/translator";

    @Setter
    private String APP_SECRET = appInfoService.get(CAIYUN_APP_SECRET_SAVE_KEY);

    @Override
    public String translate(String word) {
        HttpRequest post = HttpUtil.createPost(HOST)
                .header("x-authorization", "token " + APP_SECRET)
                .timeout(5_000)
                .body(JSONUtil.toJsonStr(getParams(word)));
        HttpResponse response = post.execute();
        if (response.isOk()) {
//            {"src_tgt":[],"target":["initializesTheHeadAndTailPointers"],"confidence":0.8,"rc":0}
            return Arrays.stream(JSONUtil.toBean(response.body(), CaiyunTranslateResult.class).getTarget()).findAny().orElse(null);
        }
        return null;
    }

    private Map<String, Object> getParams(String word) {
        Map<String, Object> params = new HashMap<>();
        params.put("source", new String[]{word});
        params.put("trans_type", "zh2en");
        params.put("request_id", UUID.randomUUID().toString());
        params.put("replaced", "true");
        params.put("media", "text");
        return params;
    }
}
