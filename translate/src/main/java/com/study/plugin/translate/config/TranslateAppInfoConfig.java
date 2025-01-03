package com.study.plugin.translate.config;

import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.study.plugin.translate.listener.ITranslateAppInfoConfigChange;
import com.study.plugin.translate.service.TranslateAppInfoService;
import com.study.plugin.translate.ui.TranslateConfigUI;
import com.study.plugin.translate.utils.PluginAppKeys;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TranslateAppInfoConfig implements SearchableConfigurable, PluginAppKeys {

    private TranslateConfigUI ui = new TranslateConfigUI();

    private TranslateAppInfoService appInfoService = ApplicationManager.getApplication().getService(TranslateAppInfoService.class);

    @Override
    public @NotNull
    @NonNls
    String getId() {
        return PLUGIN_CONFIG_ID;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return PLUGIN_CONFIG_NAME;
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        ui.setYoudaoAppId(appInfoService.get(YOUDAO_APP_ID_SAVE_KEY));
        ui.setYoudaoAppSecret(appInfoService.get(YOUDAO_APP_SECRET_SAVE_KEY));
        ui.setBiyingAppId(appInfoService.get(BIYING_APP_ID_SAVE_KEY));
        ui.setBiyingAppSecret(appInfoService.get(BIYING_APP_SECRET_SAVE_KEY));
        ui.setBaiduAppId(appInfoService.get(BAIDU_APP_ID_SAVE_KEY));
        ui.setBaiduAppSecret(appInfoService.get(BAIDU_APP_SECRET_SAVE_KEY));
        ui.setDeeplAppSecret(appInfoService.get(DEEPL_APP_SECRET_SAVE_KEY));
        ui.setCaiyunAppSecret(appInfoService.get(CAIYUN_APP_SECRET_SAVE_KEY));
        ui.setTengxunjifanAppId(appInfoService.get(TENGXUNJIFAN_APP_ID_SAVE_KEY));
        ui.setTengxunjifanAppSecret(appInfoService.get(TENGXUNJIFAN_APP_SECRET_SAVE_KEY));
        ui.setHuaweijifanProjectId(appInfoService.get(HUAWEIJIFAN_PROJECT_ID_SAVE_KEY));
        ui.setHuaweijifanAppId(appInfoService.get(HUAWEIJIFAN_APP_ID_SAVE_KEY));
        ui.setHuaweijifanAppSecret(appInfoService.get(HUAWEIJIFAN_APP_SECRET_SAVE_KEY));
        ui.setAlijifanAppId(appInfoService.get(ALIJIFAN_APP_ID_SAVE_KEY));
        ui.setAlijifanAppSecret(appInfoService.get(ALIJIFAN_APP_SECRET_SAVE_KEY));
        ui.setTencenthunyuanAppId(appInfoService.get(TENCENTHUNYUAN_APP_ID_SAVE_KEY));
        ui.setTencenthunyuanAppSecret(appInfoService.get(TENCENTHUNYUAN_APP_SECRET_SAVE_KEY));
        ui.setTencenthunyuanPrompt(appInfoService.get(TENCENTHUNYUAN_PROMPT_SAVE_KEY));
        ui.setBaiduqianfanAppId(appInfoService.get(BAIDUQIANFAN_APP_ID_SAVE_KEY));
        ui.setBaiduqianfanAppSecret(appInfoService.get(BAIDUQIANFAN_APP_SECRET_SAVE_KEY));
        ui.setBaiduqianfanPrompt(appInfoService.get(BAIDUQIANFAN_PROMPT_SAVE_KEY));
        ui.setXunfeiAppId(appInfoService.get(XUNFEI_APP_ID_SAVE_KEY));
        ui.setXunfeiAppKey(appInfoService.get(XUNFEI_APP_KEY_SAVE_KEY));
        ui.setXunfeiAppSecret(appInfoService.get(XUNFEI_APP_SECRET_SAVE_KEY));
        ui.setDeepseekAppKey(appInfoService.get(DEEPSEEK_APP_KEY_SAVE_KEY));
        ui.setDeepseekPrompt(appInfoService.get(DEEPSEEK_PROMPT_SAVE_KEY));
        ui.setOutFormatCombox(appInfoService.getInt(OUTPUT_FORMAT_MODE));
        JPanel rootJPanel = ui.getRootJPanel();
        rootJPanel.revalidate();
        rootJPanel.repaint();
        return rootJPanel;
    }

    @Override
    public boolean isModified() {
        if (isChanged()) {
            return true;
        }
        return false;
    }

    private boolean isChanged() {
        return !appInfoService.get(YOUDAO_APP_ID_SAVE_KEY).equals(ui.getYoudaoAppId()) ||
                !appInfoService.get(YOUDAO_APP_SECRET_SAVE_KEY).equals(ui.getYoudaoAppSecret()) ||
                !appInfoService.get(BIYING_APP_ID_SAVE_KEY).equals(ui.getBiyingAppId()) ||
                !appInfoService.get(BIYING_APP_SECRET_SAVE_KEY).equals(ui.getBiyingAppSecret()) ||
                !appInfoService.get(BAIDU_APP_ID_SAVE_KEY).equals(ui.getBaiduAppId()) ||
                !appInfoService.get(BAIDU_APP_SECRET_SAVE_KEY).equals(ui.getBaiduAppSecret()) ||
                !appInfoService.get(DEEPL_APP_SECRET_SAVE_KEY).equals(ui.getDeeplAppSecret()) ||
                !appInfoService.get(CAIYUN_APP_SECRET_SAVE_KEY).equals(ui.getCaiyunAppSecret()) ||
                !appInfoService.get(TENGXUNJIFAN_APP_ID_SAVE_KEY).equals(ui.getTengxunjifanAppId()) ||
                !appInfoService.get(TENGXUNJIFAN_APP_SECRET_SAVE_KEY).equals(ui.getTengxunjifanAppSecret()) ||
                !appInfoService.get(HUAWEIJIFAN_PROJECT_ID_SAVE_KEY).equals(ui.getHuaweijifanProjectId()) ||
                !appInfoService.get(HUAWEIJIFAN_APP_SECRET_SAVE_KEY).equals(ui.getHuaweijifanAppSecret()) ||
                !appInfoService.get(ALIJIFAN_APP_ID_SAVE_KEY).equals(ui.getAlijifanAppId()) ||
                !appInfoService.get(ALIJIFAN_APP_SECRET_SAVE_KEY).equals(ui.getAlijifanAppSecret()) ||
                !appInfoService.get(TENCENTHUNYUAN_APP_ID_SAVE_KEY).equals(ui.getTencenthunyuanAppId()) ||
                !appInfoService.get(TENCENTHUNYUAN_APP_SECRET_SAVE_KEY).equals(ui.getTencenthunyuanAppSecret()) ||
                !appInfoService.get(TENCENTHUNYUAN_PROMPT_SAVE_KEY).equals(ui.getTencenthunyuanPrompt()) ||
                !appInfoService.get(BAIDUQIANFAN_APP_ID_SAVE_KEY).equals(ui.getBaiduqianfanAppId()) ||
                !appInfoService.get(BAIDUQIANFAN_APP_SECRET_SAVE_KEY).equals(ui.getBaiduqianfanAppSecret()) ||
                !appInfoService.get(BAIDUQIANFAN_PROMPT_SAVE_KEY).equals(ui.getBaiduqianfanPrompt()) ||
                !appInfoService.get(XUNFEI_APP_ID_SAVE_KEY).equals(ui.getXunfeiAppId()) ||
                !appInfoService.get(XUNFEI_APP_KEY_SAVE_KEY).equals(ui.getXunfeiAppKey()) ||
                !appInfoService.get(XUNFEI_APP_SECRET_SAVE_KEY).equals(ui.getXunfeiAppSecret()) ||
                !appInfoService.get(DEEPSEEK_APP_KEY_SAVE_KEY).equals(ui.getDeepseekAppKey()) ||
                !appInfoService.get(DEEPSEEK_PROMPT_SAVE_KEY).equals(ui.getDeepseekPrompt()) ||
                appInfoService.getInt(OUTPUT_FORMAT_MODE) != ui.getOutFormat()
                ;
    }

    @Override
    public void apply() throws ConfigurationException {
        String youdaoAppId = ui.getYoudaoAppId();
        String youdaoAppSecret = ui.getYoudaoAppSecret();
        if (StrUtil.isNotBlank(youdaoAppId) && StrUtil.isNotBlank(youdaoAppSecret)) {
            appInfoService.save(YOUDAO_APP_ID_SAVE_KEY, youdaoAppId);
            appInfoService.save(YOUDAO_APP_SECRET_SAVE_KEY, youdaoAppSecret);
            ApplicationManager.getApplication().getMessageBus()
                    .syncPublisher(ITranslateAppInfoConfigChange.TOPIC).youdaoChange(youdaoAppId, youdaoAppSecret);
        }
        String biyingAppId = ui.getBiyingAppId();
        String biyingAppSecret = ui.getBiyingAppSecret();
        if (StrUtil.isNotBlank(biyingAppId) && StrUtil.isNotBlank(biyingAppSecret)) {
            appInfoService.save(BIYING_APP_ID_SAVE_KEY, biyingAppId);
            appInfoService.save(BIYING_APP_SECRET_SAVE_KEY, biyingAppSecret);
        }
        String baiduAppId = ui.getBaiduAppId();
        String baiduAppSecret = ui.getBaiduAppSecret();
        if (StrUtil.isNotBlank(baiduAppId) && StrUtil.isNotBlank(baiduAppSecret)) {
            appInfoService.save(BAIDU_APP_ID_SAVE_KEY, baiduAppId);
            appInfoService.save(BAIDU_APP_SECRET_SAVE_KEY, baiduAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .baiduChange(baiduAppId, baiduAppSecret);
        }
        String deeplAppSecret = ui.getDeeplAppSecret();
        if (StrUtil.isNotBlank(deeplAppSecret)) {
            appInfoService.save(DEEPL_APP_SECRET_SAVE_KEY, deeplAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .deeplChange(deeplAppSecret);
        }
        String caiyunAppSecret = ui.getCaiyunAppSecret();
        if (StrUtil.isNotBlank(caiyunAppSecret)) {
            appInfoService.save(CAIYUN_APP_SECRET_SAVE_KEY, caiyunAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .caiyunChange(caiyunAppSecret);
        }
        String tengxunjifanAppId = ui.getTengxunjifanAppId();
        String tengxunjifanAppSecret = ui.getTengxunjifanAppSecret();
        if (StrUtil.isNotBlank(tengxunjifanAppId) && StrUtil.isNotBlank(tengxunjifanAppSecret)) {
            appInfoService.save(TENGXUNJIFAN_APP_ID_SAVE_KEY, tengxunjifanAppId);
            appInfoService.save(TENGXUNJIFAN_APP_SECRET_SAVE_KEY, tengxunjifanAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .tengxunjifanChange(tengxunjifanAppId, tengxunjifanAppSecret);
        }
        String huaweijifanProjectId = ui.getHuaweijifanProjectId();
        String huaweijifanAppId = ui.getHuaweijifanAppId();
        String huaweijifanAppSecret = ui.getHuaweijifanAppSecret();
        if (StrUtil.isNotBlank(huaweijifanProjectId) && StrUtil.isNotBlank(huaweijifanAppSecret)) {
            appInfoService.save(HUAWEIJIFAN_PROJECT_ID_SAVE_KEY, huaweijifanProjectId);
            appInfoService.save(HUAWEIJIFAN_APP_SECRET_SAVE_KEY, huaweijifanAppSecret);
            appInfoService.save(HUAWEIJIFAN_APP_ID_SAVE_KEY, huaweijifanAppId);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .huaweijifanChange(huaweijifanProjectId, huaweijifanAppId, huaweijifanAppSecret);
        }
        String alijifanAppId = ui.getAlijifanAppId();
        String alijifanAppSecret = ui.getAlijifanAppSecret();
        if (StrUtil.isNotBlank(alijifanAppId) && StrUtil.isNotBlank(alijifanAppSecret)) {
            appInfoService.save(ALIJIFAN_APP_ID_SAVE_KEY, alijifanAppId);
            appInfoService.save(ALIJIFAN_APP_SECRET_SAVE_KEY, alijifanAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .alijifanChange(alijifanAppId, alijifanAppSecret);
        }
        String tencenthunyuanAppId = ui.getTencenthunyuanAppId();
        String tencenthunyuanAppSecret = ui.getTencenthunyuanAppSecret();
        String tencenthunyuanPrompt = ui.getTencenthunyuanPrompt();
        if (StrUtil.isNotBlank(tencenthunyuanAppId) && StrUtil.isNotBlank(tencenthunyuanAppSecret)) {
            appInfoService.save(TENCENTHUNYUAN_APP_ID_SAVE_KEY, tencenthunyuanAppId);
            appInfoService.save(TENCENTHUNYUAN_APP_SECRET_SAVE_KEY, tencenthunyuanAppSecret);
            appInfoService.save(TENCENTHUNYUAN_PROMPT_SAVE_KEY, tencenthunyuanPrompt);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .tencenthunyuanChange(tencenthunyuanAppId, tencenthunyuanAppSecret, tencenthunyuanPrompt);
        }
        String baiduqianfanAppId = ui.getBaiduqianfanAppId();
        String baiduqianfanAppSecret = ui.getBaiduqianfanAppSecret();
        String baiduqianfanPrompt = ui.getBaiduqianfanPrompt();
        if (StrUtil.isNotBlank(baiduqianfanAppId) && StrUtil.isNotBlank(baiduqianfanAppSecret)) {
            appInfoService.save(BAIDUQIANFAN_APP_ID_SAVE_KEY, baiduqianfanAppId);
            appInfoService.save(BAIDUQIANFAN_APP_SECRET_SAVE_KEY, baiduqianfanAppSecret);
            appInfoService.save(BAIDUQIANFAN_PROMPT_SAVE_KEY, baiduqianfanPrompt);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .baiduqianfanChange(baiduqianfanAppId, baiduqianfanAppSecret, baiduqianfanPrompt);
        }
        String xunfeiAppId = ui.getXunfeiAppId();
        String xunfeiAppKey = ui.getXunfeiAppKey();
        String xunfeiAppSecret = ui.getXunfeiAppSecret();
        if (StrUtil.isNotBlank(xunfeiAppId) && StrUtil.isNotBlank(xunfeiAppSecret)) {
            appInfoService.save(XUNFEI_APP_ID_SAVE_KEY, xunfeiAppId);
            appInfoService.save(XUNFEI_APP_KEY_SAVE_KEY, xunfeiAppKey);
            appInfoService.save(XUNFEI_APP_SECRET_SAVE_KEY, xunfeiAppSecret);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .xunfeiChange(xunfeiAppId, xunfeiAppKey, xunfeiAppSecret);
        }
        String deepseekAppKey = ui.getDeepseekAppKey();
        String deepseekPrompt = ui.getDeepseekPrompt();
        if (StrUtil.isNotBlank(deepseekAppKey)) {
            appInfoService.save(DEEPSEEK_APP_KEY_SAVE_KEY, deepseekAppKey);
            appInfoService.save(DEEPSEEK_PROMPT_SAVE_KEY, deepseekPrompt);
            ApplicationManager.getApplication().getMessageBus().syncPublisher(ITranslateAppInfoConfigChange.TOPIC)
                    .deepseekChange(deepseekAppKey, deepseekPrompt);
        }
        int outFormat = ui.getOutFormat();
        if (outFormat != -1) {
            appInfoService.saveInt(OUTPUT_FORMAT_MODE, outFormat);
        }
    }
}
