package com.study.plugin.translate.ui;

import com.study.plugin.translate.utils.NotificationUtil;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class TranslateConfigUI {
    @Getter
    private JPanel rootJPanel;
    private JTabbedPane translateConfigTabedPanel;
    private JPanel youdaoJPanel;
    private JPanel biyingJPanel;
    private JPanel baiduJPanel;
    private JTextField youdaoAppSecretTextField;
    private JTextField biyingAppIdTextField;
    private JTextField biyingAppSecretTextField;
    private JTextField baiduAppIdTextField;
    private JTextField baiduAppSecretTextField;
    private JPanel deeplJPanel;
    private JTextField deeplAppSecretTextField;
    private JPanel caiyunJPanel;
    private JTextField caiyunAppSecretTextField;
    private JPanel tengxunjifanJPanel;
    private JTextField tengxunjifanAppIdTextField;
    private JTextField tengxunjifanAppSecretTextField;
    private JTextField huaweijifanProjectIdTextField;
    private JTextField huaweijifanAppSecretTextField;
    private JPanel huaweijifanJPanel;
    private JPanel alijifanJPanel;
    private JTextField huaweijifanAppIdTextField;
    private JTextField alijifanAppIdTextField;
    private JTextField alijifanAppSecretTextField;
    private JComboBox outFormatCombox;
    private JEditorPane youdaoEditDescription;
    private JEditorPane biyingEditDescription;
    private JEditorPane baiduEditDecription;
    private JEditorPane deeplEditDecription;
    private JEditorPane caiyunEditDescription;
    private JEditorPane tengxunjifanEditDescription;
    private JEditorPane huaweijifanEditDescription;
    private JEditorPane alijifanEditDescription;
    private JTextField youdaoAppIdTextField;
    private JPanel tencenthunyuan;
    private JTextField tencenthunyuanAppIdTextField;
    private JTextField tencenthunyuanAppSecretTextField;
    private JEditorPane tencenthunyuanEditDescription;
    private JEditorPane tencenthunyuanPromptEditText;
    private JTextField baiduqianfanAppIdTextField;
    private JTextField baiduqianfanAppSecretTextFileld;
    private JEditorPane baiduqianfanPropmtEditText;
    private JEditorPane baiduqianfanEditDecription;

    public TranslateConfigUI() {
        HyperlinkListener hyperlinkEventConsumer = (HyperlinkEvent e) -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                StringSelection stringSelection = new StringSelection(e.getURL().toString());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception e1) {
                    NotificationUtil.error("调用默认浏览器打开网址失败，网址已复制到粘贴板，请粘贴打开！");
                }
            }
        };
        youdaoEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        biyingEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        baiduEditDecription.addHyperlinkListener(hyperlinkEventConsumer);
        deeplEditDecription.addHyperlinkListener(hyperlinkEventConsumer);
        caiyunEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        tengxunjifanEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        huaweijifanEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        alijifanEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        tencenthunyuanEditDescription.addHyperlinkListener(hyperlinkEventConsumer);
        baiduqianfanEditDecription.addHyperlinkListener(hyperlinkEventConsumer);


    }

    public String getYoudaoAppId() {
        return String.valueOf(youdaoAppIdTextField.getText()).trim();
    }

    public String getYoudaoAppSecret() {
        return String.valueOf(youdaoAppSecretTextField.getText()).trim();
    }

    public String getBiyingAppId() {
        return String.valueOf(biyingAppIdTextField.getText()).trim();
    }

    public String getBiyingAppSecret() {
        return String.valueOf(biyingAppSecretTextField.getText()).trim();
    }

    public String getBaiduAppId() {
        return String.valueOf(baiduAppIdTextField.getText()).trim();
    }

    public String getBaiduAppSecret() {
        return String.valueOf(baiduAppSecretTextField.getText()).trim();
    }

    public String getDeeplAppSecret() {
        return String.valueOf(deeplAppSecretTextField.getText()).trim();
    }

    public String getCaiyunAppSecret() {
        return String.valueOf(caiyunAppSecretTextField.getText()).trim();
    }

    public String getTengxunjifanAppId() {
        return String.valueOf(tengxunjifanAppIdTextField.getText()).trim();
    }

    public String getTengxunjifanAppSecret() {
        return String.valueOf(tengxunjifanAppSecretTextField.getText()).trim();
    }

    public String getHuaweijifanProjectId() {
        return String.valueOf(huaweijifanProjectIdTextField.getText()).trim();
    }

    public String getHuaweijifanAppId() {
        return String.valueOf(huaweijifanAppIdTextField.getText()).trim();
    }

    public String getHuaweijifanAppSecret() {
        return String.valueOf(huaweijifanAppSecretTextField.getText()).trim();
    }

    public String getAlijifanAppId() {
        return String.valueOf(alijifanAppIdTextField.getText()).trim();
    }

    public String getAlijifanAppSecret() {
        return String.valueOf(alijifanAppSecretTextField.getText()).trim();
    }

    public String getTencenthunyuanAppId() {
        return String.valueOf(tencenthunyuanAppIdTextField.getText()).trim();
    }

    public String getTencenthunyuanAppSecret() {
        return String.valueOf(tencenthunyuanAppSecretTextField.getText()).trim();
    }

    public String getTencenthunyuanPrompt() {
        return String.valueOf(tencenthunyuanPromptEditText.getText()).trim();
    }

    public String getBaiduqianfanAppId() {
        return String.valueOf(baiduqianfanAppIdTextField.getText()).trim();
    }

    public String getBaiduqianfanAppSecret() {
        return String.valueOf(baiduqianfanAppSecretTextFileld.getText()).trim();
    }

    public String getBaiduqianfanPrompt() {
        return String.valueOf(baiduqianfanPropmtEditText.getText()).trim();
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

    public void setTencenthunyuanAppId(String tencenthunyuanAppId) {
        tencenthunyuanAppIdTextField.setText(tencenthunyuanAppId);
    }

    public void setTencenthunyuanAppSecret(String tencenthunyuanAppSecret) {
        tencenthunyuanAppSecretTextField.setText(tencenthunyuanAppSecret);
    }

    public void setTencenthunyuanPrompt(String tencenthunyuanPrompt) {
        tencenthunyuanPromptEditText.setText(tencenthunyuanPrompt);
    }

    public void setBaiduqianfanAppId(String baiduqianfanAppId) {
        baiduqianfanAppIdTextField.setText(baiduqianfanAppId);
    }

    public void setBaiduqianfanAppSecret(String baiduqianfanAppSecret) {
        baiduqianfanAppSecretTextFileld.setText(baiduqianfanAppSecret);
    }

    public void setBaiduqianfanPrompt(String baiduqianfanPrompt) {
        baiduqianfanPropmtEditText.setText(baiduqianfanPrompt);
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
