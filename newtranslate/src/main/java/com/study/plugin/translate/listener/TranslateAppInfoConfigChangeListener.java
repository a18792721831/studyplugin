package com.study.plugin.translate.listener;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.study.plugin.translate.service.BaiduTranslateRestService;
import com.study.plugin.translate.service.CaiyunTranslateRestService;
import com.study.plugin.translate.service.DeeplTranslateRestService;
import com.study.plugin.translate.service.YoudaoTranslateRestService;

public class TranslateAppInfoConfigChangeListener implements ITranslateAppInfoConfigChange{

    private Application application = ApplicationManager.getApplication();

    private YoudaoTranslateRestService youdaoTranslateRestService = application.getService(YoudaoTranslateRestService.class);

    private BaiduTranslateRestService baiduTranslateRestService = application.getService(BaiduTranslateRestService.class);

    private DeeplTranslateRestService deeplTranslateRestService = application.getService(DeeplTranslateRestService.class);

    private CaiyunTranslateRestService caiyunTranslateRestService = application.getService(CaiyunTranslateRestService.class);

    @Override
    public void youdaoChange(String appId, String appSecret) {
        youdaoTranslateRestService.setAPP_ID(appId);
        youdaoTranslateRestService.setAPP_SECRET(appSecret);
    }

    @Override
    public void baiduChange(String appId, String appSecret) {
        baiduTranslateRestService.setAPP_ID(appId);
        baiduTranslateRestService.setAPP_SECRET(appSecret);
    }

    @Override
    public void caiyunChange(String appSecret) {
        caiyunTranslateRestService.setAPP_SECRET(appSecret);
    }

    @Override
    public void deeplChange(String appSecret) {
        deeplTranslateRestService.setAPP_SECRET(appSecret);
    }
}
