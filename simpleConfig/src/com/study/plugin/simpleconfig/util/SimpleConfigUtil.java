package com.study.plugin.simpleconfig.util;

import com.intellij.ide.util.PropertiesComponent;

public class SimpleConfigUtil {

    private static final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    public static String getString(String key) {
        // key
        // key defaultValue
        return propertiesComponent.getValue(key);
    }

    public static void save(String key, Object value) {
        if (value instanceof String) {
            save(key, (String) value);
        } else if (value instanceof Integer) {
            save(key, (int) value);
        } else if (value instanceof Boolean) {
            save(key, (boolean) value);
        } else if (value instanceof Float) {
            save(key, (float) value);
        } else {
            NotificationUtil.error("type error : " + value);
        }
    }

    private static void save(String key, String value) {
        // key value
        propertiesComponent.setValue(key, value);
    }

    private static void save(String key, int value) {
        // key value defaultValue
        propertiesComponent.setValue(key, value, 0);
    }

    private static void save(String key, boolean value) {
        // key value
        // key value defaultValue
        propertiesComponent.setValue(key, value);
    }

    private static void save(String key, float value) {
        // key value defaultValue
        propertiesComponent.setValue(key, value, 0.0f);
    }

}
