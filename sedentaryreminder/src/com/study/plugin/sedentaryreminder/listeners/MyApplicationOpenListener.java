package com.study.plugin.sedentaryreminder.listeners;

import com.intellij.ide.AppLifecycleListener;
import com.study.plugin.sedentaryreminder.utils.NotificationUtil;

public class MyApplicationOpenListener implements AppLifecycleListener {

    @Override
    public void appStarted() {
        NotificationUtil.error("appStarted");
    }

    @Override
    public void appClosing() {
        NotificationUtil.error("appClosing");
    }
}
