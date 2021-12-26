package com.study.plugin.simpleconfig.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class NotificationUtil {

    // 获取通知组管理器
    private static NotificationGroupManager manager = NotificationGroupManager.getInstance();

    // 获取注册的通知组
    private static NotificationGroup balloon = manager.getNotificationGroup("simpleconfig.notification.balloon");

    public static void info(String msg) {
        Notification notification = balloon.createNotification(msg, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

    public static void warning(String msg) {
        Notification notification = balloon.createNotification(msg, NotificationType.WARNING);
        Notifications.Bus.notify(notification);
    }

    public static void error(String msg) {
        Notification notification = balloon.createNotification(msg, NotificationType.ERROR);
        Notifications.Bus.notify(notification);
    }
}
