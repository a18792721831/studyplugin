package com.study.plugin.translate.listener;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.study.plugin.translate.service.*;

public class TranslateAppInfoConfigChangeListener implements ITranslateAppInfoConfigChange{

    private Application application = ApplicationManager.getApplication();

    private YoudaoTranslateRestService youdaoTranslateRestService = application.getService(YoudaoTranslateRestService.class);

    private BaiduTranslateRestService baiduTranslateRestService = application.getService(BaiduTranslateRestService.class);

    private DeeplTranslateRestService deeplTranslateRestService = application.getService(DeeplTranslateRestService.class);

    private CaiyunTranslateRestService caiyunTranslateRestService = application.getService(CaiyunTranslateRestService.class);

    private TengxunjifanTranslateRestService tengxunjifanTranslateRestService = application.getService(TengxunjifanTranslateRestService.class);

    private HuaweijifanTranslateRestService huaweijifanTranslateRestService = application.getService(HuaweijifanTranslateRestService.class);

    private AlijifanTranslateRestService alijifanTranslateRestService = application.getService(AlijifanTranslateRestService.class);

    private TencenthunyuanTranslateRestService tencenthunyuanTranslateRestService = application.getService(TencenthunyuanTranslateRestService.class);

    private BaiduYi34BTranslateRestService baiduYi34BTranslateRestService = application.getService(BaiduYi34BTranslateRestService.class);

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

    @Override
    public void tengxunjifanChange(String appId, String appSecret) {
        tengxunjifanTranslateRestService.setAPP_ID(appId);
        tengxunjifanTranslateRestService.setAPP_SECRET(appSecret);
    }

    @Override
    public void huaweijifanChange(String projectId,String appId, String appSecret) {
        huaweijifanTranslateRestService.setPROJECT_ID(projectId);
        huaweijifanTranslateRestService.setAPP_SECRET(appSecret);
        huaweijifanTranslateRestService.setAPP_ID(appId);
    }

    @Override
    public void alijifanChange(String appId, String appSecret) {
        alijifanTranslateRestService.setAPP_ID(appId);
        alijifanTranslateRestService.setAPP_SECRET(appSecret);
    }

    @Override
    public void tencenthunyuanChange(String appId, String appSecret, String prompt) {
        tencenthunyuanTranslateRestService.setAPP_ID(appId);
        tencenthunyuanTranslateRestService.setAPP_SECRET(appSecret);
        tencenthunyuanTranslateRestService.setPROMPT(prompt);
    }

    @Override
    public void baiduqianfanChange(String appId, String appSecret, String prompt) {
        baiduYi34BTranslateRestService.setAPP_ID(appId);
        baiduYi34BTranslateRestService.setAPP_SECRET(appSecret);
        baiduYi34BTranslateRestService.setPROMPT(prompt);
    }


}
