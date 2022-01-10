package com.study.plugin.sedentaryreminder.listeners;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.study.plugin.sedentaryreminder.service.SedentaryReminderConfigService;
import com.study.plugin.sedentaryreminder.task.ReminderTask;
import com.study.plugin.sedentaryreminder.utils.PluginAppKeys;
import java.time.LocalDateTime;
import java.util.Timer;

public class SedentaryReminderApplicationListener implements AppLifecycleListener, PluginAppKeys {

    private SedentaryReminderConfigService configService = ApplicationManager.getApplication().getService(SedentaryReminderConfigService.class);

    // 创建计时器
    private Timer timer = new Timer();

    private static final Logger log = Logger.getInstance(SedentaryReminderApplicationListener.class);

    @Override
    public void appStarted() {
        // 获取上次提醒时间
        LocalDateTime lastReminderDate = configService.get(SEDENTARY_REMINDER_LAST_REMINDER_DATE, LocalDateTime.now());
        log.info("app start last reminder date : " + lastReminderDate);
        // 获取上次编程时间（单位：秒）
        long lastUseDateSeconds = configService.get(SEDENTARY_REMINDER_LAST_USE_DATE, 0L);
        log.info("app start last use date : " + lastUseDateSeconds);
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        if (now.getDayOfMonth() != lastReminderDate.getDayOfMonth()) {
            // 如果本次打开时间与上次提醒时间不在一天，则重置今日跳过配置
            configService.clear(PLUGIN_TODAY_SKIP_REMINDER);
            log.info("app start last reminder not today, clear today skip reminder");
        }
        // 获取今日是否跳过
        boolean todaySkipReminder = configService.get(PLUGIN_TODAY_SKIP_REMINDER, false);
        if (todaySkipReminder) {
            log.info("app start todaySkipReminder is true");
            return;
        }
        // 获取编程时间
        int intervalTime = configService.get(PLUGIN_INTERVAL_TIME, DEFAULT_INTERVAL_TIME);
        // 获取休息时间
        int restTime = configService.get(PLUGIN_REST_TIME, DEFAULT_REST_TIME);
        // 如果上次提醒时间和现在不是一天，那么清空编程时间,然后创建计时器任务
        if (now.getDayOfMonth() != lastReminderDate.getDayOfMonth()) {
            log.info("app start last reminder date not today");
            configService.save(SEDENTARY_REMINDER_LAST_USE_DATE, 0L);
            log.info("app start save last use date 0");
            timer.schedule(new ReminderTask(), intervalTime * 60 * 1000, (intervalTime + restTime) * 60 * 1000);
            log.info("app start first reminder in " + intervalTime + " min");
            log.info("app start reminder interval is " + (intervalTime + restTime));
        }
        // 如果上次提醒时间和现在是同一天，那么接着上次的时间继续计时
        else {
            log.info("app start last reminder date is today");
            timer.schedule(new ReminderTask(), (intervalTime * 60 - lastUseDateSeconds) * 1000, (intervalTime + restTime) * 60 * 1000);
            log.info("app start first reminder in " + (intervalTime * 60 - lastUseDateSeconds) + " sec");
            log.info("app start reminder interval is " + (intervalTime + restTime));
        }
    }

    @Override
    public void appWillBeClosed(boolean isRestart) {
        // 终止计时器，放弃全部任务
        timer.cancel();
        // 释放内存
        timer.purge();
        log.info("app colsed timer is stop");
        // 获取今日是否跳过
        // 放在计时器关闭之后是防止修改配置导致内存泄漏
        boolean todaySkipReminder = configService.get(PLUGIN_TODAY_SKIP_REMINDER, false);
        if (todaySkipReminder) {
            log.info("app closed todaySkipReminder is true");
            return;
        }
        // 记录编程时间
        // 获取上次提醒时间
        LocalDateTime lastReminderTime = configService.get(SEDENTARY_REMINDER_LAST_REMINDER_DATE, LocalDateTime.now());
        // 获取休息时间
        int restTime = configService.get(PLUGIN_REST_TIME, DEFAULT_REST_TIME);
        // 获取编程时间
        int intervalTime = configService.get(PLUGIN_INTERVAL_TIME, DEFAULT_INTERVAL_TIME);
        LocalDateTime now = LocalDateTime.now();
        long lastUseTime = now.toEpochSecond(currentZoneOffset) - lastReminderTime.toEpochSecond(currentZoneOffset) - restTime * 60;
        // 避免用户以修改是否跳过为方式跳过休息
        // 如果他反复修改配置，期望跳过休息，那么会尽快的实现一次休息
        lastUseTime = lastUseTime > intervalTime ? 0 : lastUseTime;
        configService.save(SEDENTARY_REMINDER_LAST_USE_DATE,
                lastUseTime);
        log.info("app closed last use date is save : " + configService.get(SEDENTARY_REMINDER_LAST_USE_DATE, 0L));
    }
}
