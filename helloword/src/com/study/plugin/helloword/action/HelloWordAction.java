package com.study.plugin.helloword.action;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.Objects;

public class HelloWordAction extends AnAction {

    // 获取通知组管理器
    private NotificationGroupManager manager = NotificationGroupManager.getInstance();

    // 获取注册的通知组
    private NotificationGroup balloon = manager.getNotificationGroup("helloword.notification.balloon");

    private NotificationGroup sitckyBalloon = manager.getNotificationGroup("helloword.notification.sticky.balloon");

    private NotificationGroup toolWindow = manager.getNotificationGroup("helloword.notification.tool.window");

    private NotificationGroup none = manager.getNotificationGroup("helloword.notification.none");

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("helloword");
        // 使用通知组创建通知
        Notification balloonNotification = balloon.createNotification("helloword-balloon", NotificationType.INFORMATION);
        Notification sitskyBalloonNotification = sitckyBalloon.createNotification("helloword-sitskyBalloon", NotificationType.INFORMATION);
        Notification toolWindowNotification = toolWindow.createNotification("helloword-toolWindow", NotificationType.INFORMATION);
        Notification noneNotificattion = none.createNotification("helloword-none", NotificationType.INFORMATION);
        // 将通知放入通知总线
//        Notifications.Bus.notify(balloonNotification);
//        Notifications.Bus.notify(sitskyBalloonNotification);
        Notifications.Bus.notify(toolWindowNotification);
        Notifications.Bus.notify(noneNotificattion);
    }
}
