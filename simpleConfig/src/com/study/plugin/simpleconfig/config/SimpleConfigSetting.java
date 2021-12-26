package com.study.plugin.simpleconfig.config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.study.plugin.simpleconfig.ui.SimpleConfigSettingUI;
import com.study.plugin.simpleconfig.util.NotificationUtil;
import com.study.plugin.simpleconfig.util.SimpleConfigKeys;
import com.study.plugin.simpleconfig.util.SimpleConfigUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class SimpleConfigSetting implements SearchableConfigurable {

    private SimpleConfigSettingUI form = new SimpleConfigSettingUI();

    @Override
    public @NotNull
    @NonNls
    String getId() {
        return SimpleConfigKeys.SETTING_ID;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return SimpleConfigKeys.SETTING_NAME;
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        String configValue = SimpleConfigUtil.getString(SimpleConfigKeys.SETTING_TEST_CONFIG_KEY);
        form.getSimpleConfigInput().setText(configValue);
        return form.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        JTextField simpleConfigInput = form.getSimpleConfigInput();
        String inputValue = simpleConfigInput.getText();
        SimpleConfigUtil.save(SimpleConfigKeys.SETTING_TEST_CONFIG_KEY, inputValue);
        NotificationUtil.info("save " + inputValue + " success!");
    }
}
