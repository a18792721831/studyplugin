package com.study.plugin.translate.service;

import com.intellij.openapi.components.Service;
import com.study.plugin.translate.utils.PluginAppKeys;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@Service
public final class CaiyunTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "http://api.interpreter.caiyunai.com/v1/translator";

    private String APP_SECRET = appInfoService.get(CAIYUN_APP_SECRET_SAVE_KEY);

    public CaiyunTranslateRestService() {
        super();
        if (!isInit.get()) {
            init();
        }
    }

    @Override
    public String translate(String word) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("content-type", "application/json");
        requestHeaders.add("x-authorization", "token " + APP_SECRET);
        RequestEntity<Map<String, String>> request = new RequestEntity<>(getParams(word), HttpMethod.POST, URI.create(HOST));
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(HOST, request, String.class);
        HttpStatus statusCode = stringResponseEntity.getStatusCode();
        if (HttpStatus.OK == statusCode) {
            String enWord = stringResponseEntity.getBody();
            return enWord;
        }

        return null;
    }

    private Map<String, String> getParams(String word) {
        Map<String, String> params = new HashMap<>();
        params.put("source", word);
        params.put("trans_type", "zh2en");
        params.put("request_id", UUID.randomUUID().toString());
        params.put("replaced", "true");
        params.put("media", "text");
        return params;
    }
}
