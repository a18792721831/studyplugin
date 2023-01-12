package com.study.plugin.translate.listener;

import com.intellij.openapi.Disposable;
import com.intellij.util.messages.Topic;

public interface ITranslateAppInfoConfigChange {

    Topic<ITranslateAppInfoConfigChange> TOPIC = Topic.create("translateAppInfoConfigChange", ITranslateAppInfoConfigChange.class);

    void youdaoChange(String appId, String appSecret);

    void baiduChange(String appId, String appSecret);

    void caiyunChange(String appSecret);

    void deeplChange(String appSecret);
}
