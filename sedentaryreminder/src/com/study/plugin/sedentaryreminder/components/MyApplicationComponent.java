package com.study.plugin.sedentaryreminder.components;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class MyApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent() {
        Messages.showMessageDialog("initComponent", "applicationComponent", Messages.getInformationIcon());
    }

    @Override
    public void disposeComponent() {
        Messages.showMessageDialog("disposeComponent", "applicationComponent", Messages.getInformationIcon());
    }

    @Override
    public @NotNull
    String getComponentName() {
        return "MyApplicationComponent";
    }
}
