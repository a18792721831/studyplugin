package com.study.plugin.sedentaryreminder.task;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.study.plugin.sedentaryreminder.service.SedentaryReminderConfigService;
import com.study.plugin.sedentaryreminder.ui.ReminderDialog;
import com.study.plugin.sedentaryreminder.utils.PluginAppKeys;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class ReminderTask extends TimerTask implements PluginAppKeys {

    private SedentaryReminderConfigService configService = ApplicationManager.getApplication().getService(SedentaryReminderConfigService.class);

    private static final Logger log = Logger.getInstance(ReminderTask.class);

    @Override
    public void run() {
        log.info("reminder timer task is run");
        // 记录时间提醒时间
        configService.save(SEDENTARY_REMINDER_LAST_REMINDER_DATE, LocalDateTime.now());
        // 清空已用时间
        configService.clear(SEDENTARY_REMINDER_LAST_USE_DATE);
        log.info("last reminder date is save : " + configService.get(SEDENTARY_REMINDER_LAST_REMINDER_DATE, LocalDateTime.now()) +
                ", last use date is clear : " +
                configService.get(SEDENTARY_REMINDER_LAST_USE_DATE, 0L));
        // 弹出提醒对话框
        new ReminderDialog().show();
        log.info("reminder dialog is show");
    }
}
