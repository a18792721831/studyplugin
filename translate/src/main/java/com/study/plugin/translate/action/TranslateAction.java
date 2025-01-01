package com.study.plugin.translate.action;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.study.plugin.translate.format.*;
import com.study.plugin.translate.service.*;
import com.study.plugin.translate.utils.NotificationUtil;
import com.study.plugin.translate.utils.PluginAppKeys;
import com.study.plugin.translate.utils.Tools;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class TranslateAction extends AnAction implements PluginAppKeys {

    private Application application = ApplicationManager.getApplication();

    private YoudaoTranslateRestService youdaoTranslateRestService = application.getService(YoudaoTranslateRestService.class);
    private BaiduTranslateRestService baiduTranslateRestService = application.getService(BaiduTranslateRestService.class);
    private CaiyunTranslateRestService caiyunTranslateRestService = application.getService(CaiyunTranslateRestService.class);
    private TengxunjifanTranslateRestService tengxunjifanTranslateRestService = application.getService(TengxunjifanTranslateRestService.class);
    private HuaweijifanTranslateRestService huaweijifanTranslateRestService = application.getService(HuaweijifanTranslateRestService.class);
    private AlijifanTranslateRestService alijifanTranslateRestService = application.getService(AlijifanTranslateRestService.class);
    private TencenthunyuanTranslateRestService tencenthunyuanTranslateRestService = application.getService(TencenthunyuanTranslateRestService.class);
    private BaiduYi34BTranslateRestService baiduYi34BTranslateRestService = application.getService(BaiduYi34BTranslateRestService.class);
    // 持有所有的服务
    private final List<TranslateRestService> allService = Lists.newArrayList(
            youdaoTranslateRestService,
            baiduTranslateRestService,
            caiyunTranslateRestService,
            tengxunjifanTranslateRestService,
            huaweijifanTranslateRestService,
            alijifanTranslateRestService,
            tencenthunyuanTranslateRestService,
            baiduYi34BTranslateRestService);
    private TranslateAppInfoService appInfoService = application.getService(TranslateAppInfoService.class);
    private CamelCaseFormat camelCaseFormat = application.getService(CamelCaseFormat.class);
    private NoSpaceFormat noSpaceFormat = application.getService(NoSpaceFormat.class);
    private PascalCaseFormat pascalCaseFormat = application.getService(PascalCaseFormat.class);
    private SnakeCaseFormat snakeCaseFormat = application.getService(SnakeCaseFormat.class);
    private SourceFormat sourceFormat = application.getService(SourceFormat.class);
    private UpperSnakeCaseFormat upperSnakeCaseFormat = application.getService(UpperSnakeCaseFormat.class);

    private final Map<Integer, IWordFormat> allFormat = Maps.newHashMap();

    public TranslateAction() {
        allFormat.put(camelCaseFormat.getSelectIndex(), camelCaseFormat);
        allFormat.put(noSpaceFormat.getSelectIndex(), noSpaceFormat);
        allFormat.put(pascalCaseFormat.getSelectIndex(), pascalCaseFormat);
        allFormat.put(snakeCaseFormat.getSelectIndex(), snakeCaseFormat);
        allFormat.put(sourceFormat.getSelectIndex(), sourceFormat);
        allFormat.put(upperSnakeCaseFormat.getSelectIndex(), upperSnakeCaseFormat);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            NotificationUtil.error("translate field, please try later!");
            return;
        }
        SelectionModel selectionModel = editor.getSelectionModel();
        String word = selectionModel.getSelectedText();
        String enWord = callTranslate(word);
        // 如果所有厂商翻译失败，那么通知并结束
        if (Tools.isBlank(enWord)) {
            NotificationUtil.error("translate field, please try later!");
            return;
        }
        IWordFormat format = allFormat.get(appInfoService.getInt(OUTPUT_FORMAT_MODE));
        if (!format.isMatch(enWord)) {
            enWord = format.format(enWord.replaceAll("\\.", "")).replaceAll("'", "");
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
                if (Tools.isNotBlank(enWord)) {
                    // 只要有任意一个子类成功翻译，那么直接结束，不在尝试其他的厂商
                    return enWord;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
