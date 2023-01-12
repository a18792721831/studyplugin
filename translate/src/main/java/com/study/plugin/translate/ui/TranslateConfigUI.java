package com.study.plugin.translate.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Dimension;
import java.awt.Insets;
import lombok.Getter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class TranslateConfigUI {
    @Getter
    private JPanel rootJPanel;
    private JTabbedPane youdaoTabbedPanel;
    private JPanel youdaoJPanel;
    private JPanel biyingJPanel;
    private JPanel baiduJPanel;
    private JPasswordField youdaoAppIdTextField;
    private JPasswordField youdaoAppSecretTextField;
    private JPasswordField biyingAppIdTextField;
    private JPasswordField biyingAppSecretTextField;
    private JPasswordField baiduAppIdTextField;
    private JPasswordField baiduAppSecretTextField;
    private JPanel deeplJPanel;
    private JPasswordField deeplAppSecretTextField;
    private JPanel caiyunJPanel;
    private JPasswordField caiyunAppSecretTextField;
    private JTextArea 如果你还没有百度翻译的账号请到httpsAiYoudaoTextArea;
    private JTextArea 必应翻译需要到HttpsCnBingTextArea;
    private JTextArea 百度翻译需要到httpApiFanyiBaiduTextArea;
    private JTextArea 国内无法使用请确保浏览器可调用否则请勿使用TextArea;
    private JTextArea 彩云小译需要到httpsDashboardCaiyunappComTextArea;

    public String getYoudaoAppId() {
        return String.valueOf(youdaoAppIdTextField.getPassword()).trim();
    }

    public String getYoudaoAppSecret() {
        return String.valueOf(youdaoAppSecretTextField.getPassword()).trim();
    }

    public String getBiyingAppId() {
        return String.valueOf(biyingAppIdTextField.getPassword()).trim();
    }

    public String getBiyingAppSecret() {
        return String.valueOf(biyingAppSecretTextField.getPassword()).trim();
    }

    public String getBaiduAppId() {
        return String.valueOf(baiduAppIdTextField.getPassword()).trim();
    }

    public String getBaiduAppSecret() {
        return String.valueOf(baiduAppSecretTextField.getPassword()).trim();
    }

    public String getDeeplAppSecret() {
        return String.valueOf(deeplAppSecretTextField.getPassword()).trim();
    }

    public String getCaiyunAppSecret() {
        return String.valueOf(caiyunAppSecretTextField.getPassword()).trim();
    }

    public void setYoudaoAppId(String youdaoAppId) {
        youdaoAppIdTextField.setText(youdaoAppId);
    }

    public void setYoudaoAppSecret(String youdaoAppSecret) {
        youdaoAppSecretTextField.setText(youdaoAppSecret);
    }

    public void setBiyingAppId(String biyingAppId) {
        biyingAppIdTextField.setText(biyingAppId);
    }

    public void setBiyingAppSecret(String biyingAppSecret) {
        biyingAppSecretTextField.setText(biyingAppSecret);
    }

    public void setBaiduAppId(String baiduAppId) {
        baiduAppIdTextField.setText(baiduAppId);
    }

    public void setBaiduAppSecret(String baiduAppSecret) {
        baiduAppSecretTextField.setText(baiduAppSecret);
    }

    public void setDeeplAppSecret(String deeplAppSecret) {
        deeplAppSecretTextField.setText(deeplAppSecret);
    }

    public void setCaiyunAppSecret(String caiyunAppSecret) {
        caiyunAppSecretTextField.setText(caiyunAppSecret);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TranslateConfigUI");
        frame.setContentPane(new TranslateConfigUI().rootJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
