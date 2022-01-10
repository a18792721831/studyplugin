package com.study.plugin.sedentaryreminder.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.study.plugin.sedentaryreminder.service.SedentaryReminderConfigService;
import com.study.plugin.sedentaryreminder.ui.SedentaryReminderConfigUI;
import com.study.plugin.sedentaryreminder.utils.PluginAppKeys;
import java.util.Objects;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

public class SedentaryReminderConfig implements SearchableConfigurable, PluginAppKeys {

    private SedentaryReminderConfigUI ui = new SedentaryReminderConfigUI();

    private SedentaryReminderConfigService configService = ApplicationManager.getApplication().getService(SedentaryReminderConfigService.class);

    @Override
    public @NotNull
    @NonNls
    String getId() {
        return PLUGIN_CONFIG_ID;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return PLUGIN_CONFIG_NAME;
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        ui.setIntervalTime(configService.get(PLUGIN_INTERVAL_TIME, DEFAULT_INTERVAL_TIME));
        ui.setRestTime(configService.get(PLUGIN_REST_TIME, DEFAULT_REST_TIME));
        ui.setCompulsionRest(configService.get(PLUGIN_COMPULSION_REST, DEFAULT_COMPULSION_REST));
        ui.setTodaySkipReminder(configService.get(PLUGIN_TODAY_SKIP_REMINDER, DEFAULT_TODAY_SKIP_REMINDER));
        return ui.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return configService.get(PLUGIN_INTERVAL_TIME, DEFAULT_INTERVAL_TIME) != ui.getIntevalTime() ||
                configService.get(PLUGIN_REST_TIME, DEFAULT_REST_TIME) != ui.getRestTime() ||
                configService.get(PLUGIN_COMPULSION_REST, DEFAULT_COMPULSION_REST) != ui.getCompulsionRest() ||
                configService.get(PLUGIN_TODAY_SKIP_REMINDER, DEFAULT_TODAY_SKIP_REMINDER) != ui.getTodaySkipReminder();
    }

    @Override
    public void apply() throws ConfigurationException {
        Integer intevalTime = ui.getIntevalTime();
        if (Objects.nonNull(intevalTime)) {
            configService.save(PLUGIN_INTERVAL_TIME, intevalTime);
        }
        Integer restTime = ui.getRestTime();
        if (Objects.nonNull(restTime)) {
            configService.save(PLUGIN_REST_TIME, restTime);
        }
        configService.save(PLUGIN_COMPULSION_REST, ui.getCompulsionRest());
        configService.save(PLUGIN_TODAY_SKIP_REMINDER, ui.getTodaySkipReminder());
    }
}
