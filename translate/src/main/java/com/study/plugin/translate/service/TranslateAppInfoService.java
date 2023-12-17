package com.study.plugin.translate.service;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.Service;

@Service
public final class TranslateAppInfoService {

    private final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    public void save(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    public void saveInt(String key, int value) {
        propertiesComponent.setValue(key, value, -1);
    }

    public String get(String key, String defaultValue) {
        return propertiesComponent.getValue(key, defaultValue);
    }

    public int getInt(String key , int defaultValue) {
        return propertiesComponent.getInt(key, defaultValue);
    }

    public String get(String key) {
        return get(key, "");
    }

    public int getInt(String key) {
        return getInt(key, 2);
    }

}
