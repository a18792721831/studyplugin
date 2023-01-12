/* Decompiler 7ms, total 340ms, lines 30 */
package io.zhile.research.intellij.ier.action;

import com.intellij.icons.AllIcons.Actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import io.zhile.research.intellij.ier.helper.AppHelper;
import io.zhile.research.intellij.ier.helper.NotificationHelper;
import org.jetbrains.annotations.NotNull;

public class RestartAction extends AnAction implements DumbAware {
    public RestartAction() {
        super("Restart IDE", "Restart my IDE", Actions.Restart);
    }

    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }

        NotificationHelper.checkAndExpire(e);
        AppHelper.restart();
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "io/zhile/research/intellij/ier/action/RestartAction", "actionPerformed"));
    }
}
