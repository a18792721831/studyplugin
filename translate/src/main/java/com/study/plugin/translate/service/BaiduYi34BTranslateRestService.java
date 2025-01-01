package com.study.plugin.translate.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.*;
import com.study.plugin.translate.utils.PluginAppKeys;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Setter;

@Service
public final class BaiduYi34BTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String HOST = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/yi_34b_chat?access_token=";
    private String ACCESS_TOKEN_URL ="https://aip.baidubce.com/oauth/2.0/token";

    @Setter
    private String APP_ID = appInfoService.get(BAIDUQIANFAN_APP_ID_SAVE_KEY);

    @Setter
    private String APP_SECRET = appInfoService.get(BAIDUQIANFAN_APP_SECRET_SAVE_KEY);

    @Setter
    private String PROMPT = appInfoService.get(BAIDUQIANFAN_PROMPT_SAVE_KEY);

    private String ACCESS_TOKEN = appInfoService.get(BAIDUQIANFAN_ACCESSTOKEN_SAVE_KEY);

    private String ACCESS_TOKEN_EXPTIME = appInfoService.get(BAIDUQIANFAN_ACCESSTOKEN_EXPTIME_SAVE_KEY);

    @Override
    public String translate(String word) {
        String requestUrl = HOST+getAccessToken();
        // 发送请求
        String res = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(getParams(word)));
        if (StrUtil.isNotBlank(res)) {
            return JSONUtil.toBean(res, BaiduYi34BTranslateResult.class).getResult();
        }
        return null;
    }

    private BaiduYi34BTranslateRequest getParams(String message) {
        BaiduYi34BTranslateRequest request = new BaiduYi34BTranslateRequest();
        request.setMessages(List.of(BaiduYi34BTranslateMessage.builder().role("user").content("将 " + message + " 翻译成英语，只返回结果，不需要返回请求的文字，不需要返回的英文翻译是，不需要返回任何多余的字符，直接返回结果，符合这些要求" + PROMPT).build()));
        return request;
    }

    private String getAccessToken() {
        if (StrUtil.isBlank(ACCESS_TOKEN) ||
                StrUtil.isBlank(ACCESS_TOKEN_EXPTIME) ||
                LocalDateTime.parse(ACCESS_TOKEN_EXPTIME).isBefore(LocalDateTime.now())) {
            Map<String, String> params = getAccessTokenParams();
            StringBuilder builder = new StringBuilder(ACCESS_TOKEN_URL + "?");
            params.entrySet().forEach(ent -> {
                builder.append(ent.getKey() + "=" + ent.getValue() + "&");
            });
            String requestUrl = builder.toString();
            requestUrl = requestUrl.substring(0, requestUrl.length() - 1);
            // 重新获取
            String res = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(getAccessTokenParams()));
            if (StrUtil.isNotBlank(res)) {
                ACCESS_TOKEN = JSONUtil.toBean(res, BaiduAccessTokenResult.class).getAccessToken();
                appInfoService.save(BAIDUQIANFAN_ACCESSTOKEN_SAVE_KEY, ACCESS_TOKEN);
                ACCESS_TOKEN_EXPTIME = LocalDateTime.now().toString();
                appInfoService.save(BAIDUQIANFAN_ACCESSTOKEN_EXPTIME_SAVE_KEY, LocalDateTime.now().plusDays(30).toString());
            }
        }
        return ACCESS_TOKEN;
    }

    private Map<String, String> getAccessTokenParams() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", APP_ID);
        params.put("client_secret", APP_SECRET);
        return params;
    }

}
