package com.study.plugin.sedentaryreminder.components;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class MyProjectComponent implements ProjectComponent {

    @Override
    public void projectOpened() {
        Messages.showMessageDialog("projectOpen", "projectComponent", Messages.getInformationIcon());
        System.out.println("projectOpened");
    }

    @Override
    public void projectClosed() {
        Messages.showMessageDialog("projectClosed", "projectComponent", Messages.getInformationIcon());
        System.out.println("projectClosed");
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    public @NotNull
    String getComponentName() {
        return "MyProjectComponent";
    }
}
