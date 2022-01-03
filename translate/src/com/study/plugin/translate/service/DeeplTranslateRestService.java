package com.study.plugin.translate.service;

import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.DeeplResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.groovy.util.Maps;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public final class DeeplTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "https://api-free.deepl.com/v2/translate";

    private String APP_SECRET = appInfoService.get(DEEPL_APP_SECRET_SAVE_KEY);

    public DeeplTranslateRestService() {
        super();
        if (!isInit.get()) {
            init();
        }
    }

    @Override
    public String translate(String word) {
        Map<String, String> params = getParams(word);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        params.entrySet().forEach(ent -> {
            map.put(ent.getKey(), Collections.singletonList(ent.getValue()));
        });
        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<>(map, httpHeaders, HttpMethod.POST, URI.create(HOST));
        DeeplResult result = restTemplate.postForObject(HOST, request, DeeplResult.class, Maps.of("auth_key", APP_SECRET));
        if (Objects.nonNull(result) && !CollectionUtils.isEmpty(result.getTranslations())) {
            return result.getTranslations().get(0).getText();
        }
        return null;
    }

    private Map<String, String> getParams(String word) {
        Map<String, String> params = new HashMap<>();
        params.put("text", word);
        // 非必填
        params.put("source_lang", "ZH");
        params.put("target_lang", "EN-US");
        params.put("auth_key", APP_SECRET);
        return params;
    }
}
