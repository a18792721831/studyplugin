/* Decompiler 4ms, total 292ms, lines 65 */
package io.zhile.research.intellij.ier.action;

import com.intellij.icons.AllIcons.General;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowEP;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import io.zhile.research.intellij.ier.helper.NotificationHelper;
import io.zhile.research.intellij.ier.helper.PluginHelper;
import io.zhile.research.intellij.ier.listener.ListenerConnector;
import io.zhile.research.intellij.ier.tw.MainToolWindowFactory;
import io.zhile.research.intellij.ier.ui.dialog.MainDialog;
import org.jetbrains.annotations.NotNull;

public class ResetAction extends AnAction implements DumbAware {
    public ResetAction() {
        super("Eval Reset", "Reset my IDE eval information", General.Reset);
        AnAction optionsGroup = ActionManager.getInstance().getAction("WelcomeScreen.Options");
        if (optionsGroup instanceof DefaultActionGroup) {
            ((DefaultActionGroup)optionsGroup).add(this);
        }

        ListenerConnector.setup();
    }

    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }

        Project project = e.getProject();
        NotificationHelper.checkAndExpire(e);
        if (project == null) {
            MainDialog mainDialog = new MainDialog("Eval Reset");
            mainDialog.show();
        } else {
            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Eval Reset");
            if (null == toolWindow) {
                ToolWindowEP ep = new ToolWindowEP();
                ep.id = "Eval Reset";
                ep.anchor = ToolWindowAnchor.BOTTOM.toString();
                ep.icon = "AllIcons.General.Reset";
                ep.factoryClass = MainToolWindowFactory.class.getName();
                ep.setPluginDescriptor(PluginHelper.getPluginDescriptor());
                ToolWindowManagerEx.getInstanceEx(project).initToolWindow(ep);
                toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Eval Reset");
            }

            toolWindow.show((Runnable)null);
        }
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "io/zhile/research/intellij/ier/action/ResetAction", "actionPerformed"));
    }
}
