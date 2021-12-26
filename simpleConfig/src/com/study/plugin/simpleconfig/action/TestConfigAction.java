package com.study.plugin.simpleconfig.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.text.StringUtil;
import com.study.plugin.simpleconfig.util.NotificationUtil;
import com.study.plugin.simpleconfig.util.SimpleConfigKeys;
import com.study.plugin.simpleconfig.util.SimpleConfigUtil;

public class TestConfigAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String configValue = SimpleConfigUtil.getString(SimpleConfigKeys.SETTING_TEST_CONFIG_KEY);
        if(StringUtil.isEmpty(configValue)) {
            NotificationUtil.error("empty");
        }
        NotificationUtil.info(configValue);
    }
}
