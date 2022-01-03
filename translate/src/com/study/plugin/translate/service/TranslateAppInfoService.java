package com.study.plugin.translate.service;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.Service;

@Service
public final class TranslateAppInfoService {

    private final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    public void save(String key, String value) {
        propertiesComponent.setValue(key, value);
    }

    public String get(String key, String defaultValue) {
        return propertiesComponent.getValue(key, defaultValue);
    }

    public String get(String key) {
        return get(key, "");
    }

}
