/* Decompiler 4ms, total 304ms, lines 71 */
package io.zhile.research.intellij.ier.listener;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFrame;
import io.zhile.research.intellij.ier.common.Resetter;
import io.zhile.research.intellij.ier.helper.BrokenPlugins;
import io.zhile.research.intellij.ier.helper.NotificationHelper;
import io.zhile.research.intellij.ier.helper.ResetTimeHelper;
import org.jetbrains.annotations.NotNull;

public class AppActivationListener implements ApplicationActivationListener {
    private boolean tipped = false;

    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        if (ideFrame == null) {
            $$$reportNull$$$0(0);
        }

        BrokenPlugins.fix();
        if (!this.tipped && ResetTimeHelper.overResetPeriod()) {
            this.tipped = true;
            AnAction action = ActionManager.getInstance().getAction("io.zhile.research.intellij.ier.action.ResetAction");
            NotificationType type = NotificationType.INFORMATION;
            String message = "It has been a long time since the last reset!\nWould you like to reset it again?";
            if (Resetter.isAutoReset()) {
                action = ActionManager.getInstance().getAction("io.zhile.research.intellij.ier.action.RestartAction");
                type = NotificationType.WARNING;
            }

            NotificationHelper.show((Project)null, (String)null, (String)null, message, type, action);
        }
    }

    public void applicationDeactivated(@NotNull IdeFrame ideFrame) {
        if (ideFrame == null) {
            $$$reportNull$$$0(1);
        }

        this.applicationActivated(ideFrame);
    }

    public void delayedApplicationDeactivated(@NotNull IdeFrame ideFrame) {
        if (ideFrame == null) {
            $$$reportNull$$$0(2);
        }

    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        Object[] var10001 = new Object[]{"ideFrame", "io/zhile/research/intellij/ier/listener/AppActivationListener", null};
        switch(var0) {
            case 0:
            default:
                var10001[2] = "applicationActivated";
                break;
            case 1:
                var10001[2] = "applicationDeactivated";
                break;
            case 2:
                var10001[2] = "delayedApplicationDeactivated";
        }

        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
    }
}
