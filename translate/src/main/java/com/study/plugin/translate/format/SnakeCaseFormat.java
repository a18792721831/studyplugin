package com.study.plugin.translate.format;

import cn.hutool.core.util.ReUtil;
import com.intellij.openapi.components.Service;

@Service
public final class SnakeCaseFormat implements IWordFormat {

    public static final String REG = "^([a-z]+_)*[a-z]+$";

    @Override
    public boolean isMatch(String word) {
        return ReUtil.isMatch(REG, word);
    }

    @Override
    public String format(String word) {
        if (ReUtil.isMatch(UpperSnakeCaseFormat.REG, word)) {
            return word.toLowerCase();
        }
        if (ReUtil.isMatch(PascalCaseFormat.REG, word) ||
                ReUtil.isMatch(CamelCaseFormat.REG, word)) {
            StringBuilder sb = new StringBuilder();
            char[] array = word.toCharArray();
            sb.append(Character.toLowerCase(array[0]));
            for (int i = 1; i < array.length; i++) {
                if (Character.isUpperCase(array[i])) {
                    sb.append("_");
                }
                sb.append(Character.toLowerCase(array[i]));
            }
            return sb.toString();
        }
        // 剩余输入，按照[ _-]分割单词，每个单词首字母大写
        String[] words = word.split("[ _-]");
        StringBuilder sb = new StringBuilder();
        for (String word1 : words) {
            sb.append(word1.toLowerCase());
            sb.append('_');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    @Override
    public int getSelectIndex() {
        return 3;
    }
}
