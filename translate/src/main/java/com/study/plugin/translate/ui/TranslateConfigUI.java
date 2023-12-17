package com.study.plugin.translate.ui;

import lombok.Getter;

import javax.swing.*;

public class TranslateConfigUI {
    @Getter
    private JPanel rootJPanel;
    private JTabbedPane translateConfigTabedPanel;
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
    private JTextArea youdaoDescription;
    private JTextArea bingyingDescription;
    private JTextArea baiduDescription;
    private JTextArea deeplDescription;
    private JTextArea caiyunDescription;
    private JPanel tengxunjifanJPanel;
    private JPasswordField tengxunjifanAppIdTextField;
    private JPasswordField tengxunjifanAppSecretTextField;
    private JPasswordField huaweijifanProjectIdTextField;
    private JPasswordField huaweijifanAppSecretTextField;
    private JPanel huaweijifanJPanel;
    private JPanel alijifanJPanel;
    private JTextArea tengxunjifanDescription;
    private JTextArea huaweijifanDescription;
    private JPasswordField huaweijifanAppIdTextField;
    private JPasswordField alijifanAppIdTextField;
    private JPasswordField alijifanAppSecretTextField;
    private JTextArea alijifanDescription;
    private JComboBox outFormatCombox;

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

    public String getTengxunjifanAppId() {
        return String.valueOf(tengxunjifanAppIdTextField.getPassword()).trim();
    }

    public String getTengxunjifanAppSecret() {
        return String.valueOf(tengxunjifanAppSecretTextField.getPassword()).trim();
    }

    public String getHuaweijifanProjectId() {
        return String.valueOf(huaweijifanProjectIdTextField.getPassword()).trim();
    }

    public String getHuaweijifanAppId() {
        return String.valueOf(huaweijifanAppIdTextField.getPassword()).trim();
    }

    public String getHuaweijifanAppSecret() {
        return String.valueOf(huaweijifanAppSecretTextField.getPassword()).trim();
    }

    public String getAlijifanAppId() {
        return String.valueOf(alijifanAppIdTextField.getPassword()).trim();
    }

    public String getAlijifanAppSecret() {
        return String.valueOf(alijifanAppSecretTextField.getPassword()).trim();
    }

    /**
     * 原文(xx yy)
     * 大驼峰(XxYy)
     * 小驼峰(xxYy)
     * 小蛇形(xx_yy)
     * 大蛇形(XX_YY)
     * 无空格原文(xxyy)
     */
    public int getOutFormat() {
        return outFormatCombox.getSelectedIndex();
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

    public void setTengxunjifanAppId(String tengxunjifanAppId) {
        tengxunjifanAppIdTextField.setText(tengxunjifanAppId);
    }

    public void setTengxunjifanAppSecret(String tengxunjifanAppSecret) {
        tengxunjifanAppSecretTextField.setText(tengxunjifanAppSecret);
    }

    public void setHuaweijifanProjectId(String huaweijifanProjectId) {
        huaweijifanProjectIdTextField.setText(huaweijifanProjectId);
    }

    public void setHuaweijifanAppId(String huaweijifanAppId) {
        huaweijifanAppIdTextField.setText(huaweijifanAppId);
    }

    public void setHuaweijifanAppSecret(String huaweijifanAppSecret) {
        huaweijifanAppSecretTextField.setText(huaweijifanAppSecret);
    }

    public void setAlijifanAppId(String alijifanAppId) {
        alijifanAppIdTextField.setText(alijifanAppId);
    }

    public void setAlijifanAppSecret(String alijifanAppSecret) {
        alijifanAppSecretTextField.setText(alijifanAppSecret);
    }

    /**
     * 原文(xx yy)
     * 大驼峰(XxYy)
     * 小驼峰(xxYy)
     * 小蛇形(xx_yy)
     * 大蛇形(XX_YY)
     * 无空格原文(xxyy)
     */
    public void setOutFormatCombox(int outFormat) {
        outFormatCombox.setSelectedIndex(outFormat);
    }
}
