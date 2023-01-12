/* Decompiler 4ms, total 289ms, lines 61 */
package io.zhile.research.intellij.ier.tw;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory.SERVICE;
import io.zhile.research.intellij.ier.ui.form.MainForm;
import org.jetbrains.annotations.NotNull;

public class MainToolWindowFactory implements ToolWindowFactory, DumbAware {
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        if (project == null) {
            $$$reportNull$$$0(0);
        }

        if (toolWindow == null) {
            $$$reportNull$$$0(1);
        }

        Disposable disposable = Disposer.newDisposable();
        MainForm mainForm = new MainForm(disposable);
        Content content = SERVICE.getInstance().createContent(mainForm.getContent(disposable), "", true);
        content.setDisposer(disposable);
        toolWindow.getContentManager().addContent(content);
    }

    public static void unregisterAll() {
        Project[] var0 = ProjectManager.getInstance().getOpenProjects();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            Project project = var0[var2];
            ToolWindowManager.getInstance(project).unregisterToolWindow("Eval Reset");
        }

    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        Object[] var10001 = new Object[3];
        switch(var0) {
            case 0:
            default:
                var10001[0] = "project";
                break;
            case 1:
                var10001[0] = "toolWindow";
        }

        var10001[1] = "io/zhile/research/intellij/ier/tw/MainToolWindowFactory";
        var10001[2] = "createToolWindowContent";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
    }
}
