package com.study.plugin.sedentaryreminder.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public interface PluginAppKeys {
    String PLUGIN_CONFIG_ID = "com.study.plugin.sedentaryreminder.config.id";
    String PLUGIN_CONFIG_NAME = "久坐提醒配置";
    String PLUGIN_INTERVAL_TIME = "com.study.plugin.sedentaryreminder.config.interval.time";
    String PLUGIN_REST_TIME = "com.study.plugin.sedentaryreminder.config.rest.time";
    String PLUGIN_COMPULSION_REST = "com.study.plugin.sedentaryreminder.config.compulsion.rest";
    String PLUGIN_TODAY_SKIP_REMINDER = "com.study.plugin.sedentaryreminder.config.today.skip.reminder";
    Integer DEFAULT_INTERVAL_TIME = 25;
    Integer DEFAULT_REST_TIME = 5;
    boolean DEFAULT_COMPULSION_REST = true;
    boolean DEFAULT_TODAY_SKIP_REMINDER = false;
    String SEDENTARY_REMINDER_LAST_REMINDER_DATE = "com.study.plugin.sedentaryreminder.last.reminder.date";
    String SEDENTARY_REMINDER_LAST_USE_DATE = "com.study.plugin.sedentaryreminder.last.use.date";
    ZoneOffset currentZoneOffset = OffsetDateTime.now().getOffset();
}
