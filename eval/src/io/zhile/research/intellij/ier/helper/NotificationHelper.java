/* Decompiler 30ms, total 314ms, lines 88 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.icons.AllIcons.General;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

public class NotificationHelper {
    public static void checkAndExpire(AnActionEvent e) {
        DataKey<Notification> notificationKey = DataKey.create("Notification");
        Notification notification = (Notification)notificationKey.getData(e.getDataContext());
        if (null != notification) {
            notification.expire();
        }

    }

    public static Notification show(@Nullable Project project, String title, String subtitle, String content, NotificationType type) {
        return show(project, title, subtitle, content, type, new AnAction[0]);
    }

    public static Notification show(@Nullable Project project, String title, String subtitle, String content, NotificationType type, AnAction action) {
        return show(project, title, subtitle, content, type, new AnAction[]{action});
    }

    public static Notification show(@Nullable Project project, String title, String subtitle, String content, NotificationType type, AnAction[] actions) {
        if (title == null) {
            title = PluginHelper.getPluginName();
        }

        NotificationGroup group = new NotificationGroup("io.zhile.research.ide-eval-resetter", NotificationDisplayType.BALLOON, true, (String)null, General.Reset);
        Notification notification = group.createNotification(title, subtitle, content, type, NotificationListener.URL_OPENING_LISTENER);
        AnAction[] var8 = actions;
        int var9 = actions.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            AnAction action = var8[var10];
            notification.addAction(action);
        }

        notification.notify(project);
        return notification;
    }

    public static Notification showError(@Nullable Project project, String title, String subtitle, String content) {
        return show(project, title, subtitle, content, NotificationType.ERROR);
    }

    public static Notification showError(@Nullable Project project, String title, String content) {
        return showError(project, title, (String)null, content);
    }

    public static Notification showError(@Nullable Project project, String content) {
        return showError(project, (String)null, (String)null, content);
    }

    public static Notification showWarn(@Nullable Project project, String title, String subtitle, String content) {
        return show(project, title, subtitle, content, NotificationType.WARNING);
    }

    public static Notification showWarn(@Nullable Project project, String title, String content) {
        return showWarn(project, title, (String)null, content);
    }

    public static Notification showWarn(@Nullable Project project, String content) {
        return showWarn(project, (String)null, (String)null, content);
    }

    public static Notification showInfo(@Nullable Project project, String title, String subtitle, String content) {
        return show(project, title, subtitle, content, NotificationType.INFORMATION);
    }

    public static Notification showInfo(@Nullable Project project, String title, String content) {
        return showInfo(project, title, (String)null, content);
    }

    public static Notification showInfo(@Nullable Project project, String content) {
        return showInfo(project, (String)null, (String)null, content);
    }
}
