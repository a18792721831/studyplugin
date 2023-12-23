package com.study.plugin.translate.service;

import com.intellij.openapi.components.Service;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.util.HashMap;
import java.util.Map;
import lombok.Setter;

@Service
public final class DeeplTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "https://api-free.deepl.com/v2/translate";

    @Setter
    private String APP_SECRET = appInfoService.get(DEEPL_APP_SECRET_SAVE_KEY);


    @Override
    public String translate(String word) {
//        Map<String, String> params = getParams(word);
//        HttpUtil.createPost(HOST)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        params.entrySet().forEach(ent -> {
//            map.put(ent.getKey(), Collections.singletonList(ent.getValue()));
//        });
//        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<>(map, httpHeaders, HttpMethod.POST, URI.create(HOST));
//        DeeplResult result = restTemplate.postForObject(HOST, request, DeeplResult.class, Maps.of("auth_key", APP_SECRET));
//        if (Objects.nonNull(result) && !CollectionUtils.isEmpty(result.getTranslations())) {
//            return result.getTranslations().get(0).getText();
//        }
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
