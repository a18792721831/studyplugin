package com.study.plugin.sedentaryreminder.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.DialogWrapper;
import com.study.plugin.sedentaryreminder.service.SedentaryReminderConfigService;
import com.study.plugin.sedentaryreminder.utils.PluginAppKeys;
import java.awt.BorderLayout;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ReminderDialog extends DialogWrapper implements PluginAppKeys {

    private static final Logger log = Logger.getInstance(ReminderDialog.class);

    private JPanel rootJPanel = new JPanel();

    private JProgressBar progressBar = new JProgressBar();

    // 创建计时器，主要是用于提醒对话框的进度条更新
    private Timer timer;

    // 为了更加直观，增加倒计时展示
    private JLabel timeLabel;

    private SedentaryReminderConfigService configService = ApplicationManager.getApplication().getService(SedentaryReminderConfigService.class);

    public ReminderDialog() {
        super(true);
        // 设置是否是模式对话框，即是否强制休息
        setModal(configService.get(PLUGIN_COMPULSION_REST, DEFAULT_COMPULSION_REST));
        setTitle("休息中~");
        initJPanel();
        init();
    }

    @SneakyThrows
    @Override
    protected void init() {
        if (SwingUtilities.isEventDispatchThread()) {
            super.init();
        } else {
            SwingUtilities.invokeAndWait(() -> super.init());
        }
    }

    private void initJPanel() {
        rootJPanel.setLayout(new BorderLayout());
        timeLabel = new JLabel();
        rootJPanel.add(timeLabel, BorderLayout.NORTH);
        // 居中展示
        rootJPanel.add(progressBar, BorderLayout.CENTER);
        // 进度条展示边框
        progressBar.setBorderPainted(true);
        // 每过1秒，进度条更新一次
        timer = new Timer(1000, e -> {
            progressBar.setValue(progressBar.getValue() + 1);
            timeLabel.setText(String.valueOf(progressBar.getMaximum() - progressBar.getValue()));
        });
        // 增加进度条监听，如果进度条满了，关闭对话框
        progressBar.addChangeListener(e -> {
            Object source = e.getSource();
            if (source instanceof JProgressBar) {
                JProgressBar bar = (JProgressBar) source;
                if (bar.getValue() == bar.getMaximum()) {
                    // 计时器关闭
                    timer.stop();
                    // 发送窗口关闭事件
                    SwingUtilities.invokeLater(() -> close(CLOSE_EXIT_CODE));
                    log.info("reminder dialog will be closed");
                }
            }
        });
        // 设置关闭对话框不可用
        getCancelAction().setEnabled(false);
    }

    @Override
    protected @Nullable
    JComponent createCenterPanel() {
        // 设置进度条最大值，也就是休息时间
        int restTime = configService.get(PLUGIN_REST_TIME, DEFAULT_REST_TIME) * 60;
        progressBar.setMaximum(restTime);
        timeLabel.setText(String.valueOf(restTime));
        // 设置进度条开始
        progressBar.setMinimum(0);
        // 进度条初始化为0
        progressBar.setValue(0);
        // 计时器启动
        timer.start();
        return rootJPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        // 隐藏 ok 取消按钮
        return null;
    }

    @SneakyThrows
    @Override
    public void show() {
        if (SwingUtilities.isEventDispatchThread()) {
            super.show();
        } else {
            SwingUtilities.invokeAndWait(() -> super.show());
        }
    }
}
