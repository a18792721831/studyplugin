package com.study.plugin.translate.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.text.StringUtil;
import com.study.plugin.translate.service.TranslateAppInfoService;
import com.study.plugin.translate.ui.TranslateConfigUI;
import com.study.plugin.translate.utils.PluginAppKeys;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

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
        return ui.getRootJPanel();
    }

    @Override
    public boolean isModified() {
        if (!appInfoService.get(YOUDAO_APP_ID_SAVE_KEY).equals(ui.getYoudaoAppId()) ||
                !appInfoService.get(YOUDAO_APP_SECRET_SAVE_KEY).equals(ui.getYoudaoAppSecret()) ||
                !appInfoService.get(BIYING_APP_ID_SAVE_KEY).equals(ui.getBiyingAppId()) ||
                !appInfoService.get(BIYING_APP_SECRET_SAVE_KEY).equals(ui.getBiyingAppSecret()) ||
                !appInfoService.get(BAIDU_APP_ID_SAVE_KEY).equals(ui.getBaiduAppId()) ||
                !appInfoService.get(BAIDU_APP_SECRET_SAVE_KEY).equals(ui.getBaiduAppSecret()) ||
                !appInfoService.get(DEEPL_APP_SECRET_SAVE_KEY).equals(ui.getDeeplAppSecret()) ||
                !appInfoService.get(CAIYUN_APP_SECRET_SAVE_KEY).equals(ui.getCaiyunAppSecret())) {
            return true;
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        String youdaoAppId = ui.getYoudaoAppId();
        String youdaoAppSecret = ui.getYoudaoAppSecret();
        if (StringUtil.isNotEmpty(youdaoAppId) && StringUtil.isNotEmpty(youdaoAppSecret)) {
            appInfoService.save(YOUDAO_APP_ID_SAVE_KEY, youdaoAppId);
            appInfoService.save(YOUDAO_APP_SECRET_SAVE_KEY, youdaoAppSecret);
        }
        String biyingAppId = ui.getBiyingAppId();
        String biyingAppSecret = ui.getBiyingAppSecret();
        if (StringUtil.isNotEmpty(biyingAppId) && StringUtil.isNotEmpty(biyingAppSecret)) {
            appInfoService.save(BIYING_APP_ID_SAVE_KEY, biyingAppId);
            appInfoService.save(BIYING_APP_SECRET_SAVE_KEY, biyingAppSecret);
        }
        String baiduAppId = ui.getBaiduAppId();
        String baiduAppSecret = ui.getBaiduAppSecret();
        if (StringUtil.isNotEmpty(baiduAppId) && StringUtil.isNotEmpty(baiduAppSecret)) {
            appInfoService.save(BAIDU_APP_ID_SAVE_KEY, baiduAppId);
            appInfoService.save(BAIDU_APP_SECRET_SAVE_KEY, baiduAppSecret);
        }
        String deeplAppSecret = ui.getDeeplAppSecret();
        if (StringUtil.isNotEmpty(deeplAppSecret)) {
            appInfoService.save(DEEPL_APP_SECRET_SAVE_KEY, deeplAppSecret);
        }
        String caiyunAppSecret = ui.getCaiyunAppSecret();
        if (StringUtil.isNotEmpty(caiyunAppSecret)) {
            appInfoService.save(CAIYUN_APP_SECRET_SAVE_KEY, caiyunAppSecret);
        }
    }
}
