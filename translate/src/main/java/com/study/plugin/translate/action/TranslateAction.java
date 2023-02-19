package com.study.plugin.translate.action;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.study.plugin.translate.service.AlijifanTranslateRestService;
import com.study.plugin.translate.service.BaiduTranslateRestService;
import com.study.plugin.translate.service.CaiyunTranslateRestService;
import com.study.plugin.translate.service.HuaweijifanTranslateRestService;
import com.study.plugin.translate.service.TengxunjifanTranslateRestService;
import com.study.plugin.translate.service.TranslateRestService;
import com.study.plugin.translate.service.YoudaoTranslateRestService;
import com.study.plugin.translate.utils.NotificationUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TranslateAction extends AnAction {

    private Application application = ApplicationManager.getApplication();

    private YoudaoTranslateRestService youdaoTranslateRestService = application.getService(YoudaoTranslateRestService.class);
    private BaiduTranslateRestService baiduTranslateRestService = application.getService(BaiduTranslateRestService.class);
    private CaiyunTranslateRestService caiyunTranslateRestService = application.getService(CaiyunTranslateRestService.class);
    private TengxunjifanTranslateRestService tengxunjifanTranslateRestService = application.getService(TengxunjifanTranslateRestService.class);
    private HuaweijifanTranslateRestService huaweijifanTranslateRestService = application.getService(HuaweijifanTranslateRestService.class);
    private AlijifanTranslateRestService alijifanTranslateRestService = application.getService(AlijifanTranslateRestService.class);
    // 持有所有的服务
    private final List<TranslateRestService> allService = Lists.newArrayList(youdaoTranslateRestService, baiduTranslateRestService, caiyunTranslateRestService, tengxunjifanTranslateRestService, huaweijifanTranslateRestService, alijifanTranslateRestService);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            NotificationUtil.error("翻译失败，请稍后重试！");
            return;
        }
        SelectionModel selectionModel = editor.getSelectionModel();
        String word = selectionModel.getSelectedText();
        String enWord = callTranslate(word);
        // 如果所有厂商翻译失败，那么通知并结束
        if (StrUtil.isBlank(enWord)) {
            NotificationUtil.error("翻译失败，请稍后重试！");
            return;
        }
        // 判断是否符合驼峰
        if (!ReUtil.isMatch("(.*)_(\\\\w)(.*)", enWord)) {
            // 转为驼峰
            enWord = convertHumps(enWord.replaceAll("\\.", "")).replaceAll("'", "");
        }
        Document document = editor.getDocument();
        // 替换
        String finalEnWord = enWord;
        WriteCommandAction.runWriteCommandAction(e.getProject(), () -> document.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), finalEnWord));
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Caret caret = e.getData(CommonDataKeys.CARET);
        if (caret != null && caret.getSelectionStart() != caret.getSelectionEnd()) {
            e.getPresentation().setEnabledAndVisible(Boolean.TRUE);
        } else {
            e.getPresentation().setEnabledAndVisible(Boolean.FALSE);
        }
    }

    private String convertHumps(String source) {
        StringBuilder result = new StringBuilder();
        // 以空格分割单词
        String[] allWords = source.split(" ");
        for (int i = 0; i < allWords.length; i++) {
            // 驼峰第一个单词全部小写
            if (i == 0) {
                result.append(allWords[i].toLowerCase());
            } else {
                // 其他单词第一个字母大写，其他小写
                String lowerCase = allWords[i].toLowerCase();
                result.append(Character.toUpperCase(lowerCase.charAt(0)));
                result.append(lowerCase.substring(1));
            }
        }
        return result.toString();
    }

    /**
     * 轮训请求所有的服务
     *
     * @param word
     * @return
     */
    private String callTranslate(String word) {
        // 对所有的服务进行轮训
        for (TranslateRestService service : allService) {
            // try包围则是当其中一个子类出现异常的时候，不会影响其他子类
            try {
                String enWord = service.translate(word);
                if (StringUtils.isNotBlank(enWord)) {
                    // 只要有任意一个子类成功翻译，那么直接结束，不在尝试其他的厂商
                    return enWord;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
