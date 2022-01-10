package com.study.plugin.sedentaryreminder.service;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.Service;
import com.study.plugin.sedentaryreminder.utils.PluginAppKeys;
import java.time.LocalDateTime;

@Service
public final class SedentaryReminderConfigService {

    private final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    public void save(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    public void save(String key, Integer value) {
        propertiesComponent.setValue(key, value, 0);
    }

    public void save(String key, Boolean value) {
        propertiesComponent.setValue(key, value);
    }

    public void save(String key, LocalDateTime date) {
        propertiesComponent.setValue(key,
                String.valueOf(date.toEpochSecond(PluginAppKeys.currentZoneOffset)));
    }

    public LocalDateTime get(String key, LocalDateTime defValue) {
        return LocalDateTime.ofEpochSecond(
                Long.valueOf(propertiesComponent.getValue(key, String.valueOf(defValue.toEpochSecond(PluginAppKeys.currentZoneOffset)))),
                0, PluginAppKeys.currentZoneOffset);
    }

    public void clear(String key) {
        propertiesComponent.unsetValue(key);
    }

    public String get(String key, String defValue) {
        return propertiesComponent.getValue(key, defValue);
    }

    public int get(String key, int defValue) {
        return propertiesComponent.getInt(key, defValue);
    }

    public void save(String key, long value) {
        propertiesComponent.setValue(key, String.valueOf(value));
    }

    public long get(String key, long defValue) {
        return propertiesComponent.getLong(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        return propertiesComponent.getBoolean(key, defValue);
    }

}
