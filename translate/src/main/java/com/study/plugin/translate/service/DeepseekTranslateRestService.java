package com.study.plugin.translate.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.intellij.openapi.components.Service;
import com.study.plugin.translate.beans.DeepseekTranslateMessage;
import com.study.plugin.translate.beans.DeepseekTranslateRequest;
import com.study.plugin.translate.beans.DeepseekTranslateResponse;
import com.study.plugin.translate.beans.TencenthunyuanResult;
import com.study.plugin.translate.utils.PluginAppKeys;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public final class DeepseekTranslateRestService extends TranslateRestService implements PluginAppKeys {

    private String URL = "https://api.deepseek.com/chat/completions";

    @Setter
    private String APP_KEY = appInfoService.get(DEEPSEEK_APP_KEY_SAVE_KEY);

    @Setter
    private String APP_PROMPT = appInfoService.get(DEEPSEEK_PROMPT_SAVE_KEY);


    @Override
    public String translate(String word) {
        String payload = JSONUtil.toJsonStr(getParams(word));
        HttpResponse response = HttpUtil.createPost(URL).
                header("Content-Type", "application/json").
                header("Authorization", "Bearer " + APP_KEY).
                body(payload).
                execute();
        if (response.isOk()) {
            return JSONUtil.toBean(response.body(), DeepseekTranslateResponse.class).getChoices().get(0).getMessage().getContent();
        }
        return null;
    }

    private DeepseekTranslateRequest getParams(String word) {
        return DeepseekTranslateRequest.builder().model("deepseek-chat").
                stream(false).
                messages(List.of(DeepseekTranslateMessage.builder().role("user").content("将 " + word + " 翻译成英文，只返回结果，"+APP_PROMPT).build())).
                build();
    }
}