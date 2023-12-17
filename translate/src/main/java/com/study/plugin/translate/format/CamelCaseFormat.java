package com.study.plugin.translate.format;

import cn.hutool.core.util.ReUtil;
import com.intellij.openapi.components.Service;

@Service
public class CamelCaseFormat implements IWordFormat{

    public static final String REG = "^([a-z]+[A-Z]*\\w*)+$";

    @Override
    public boolean isMatch(String word) {
        return ReUtil.isMatch(REG, word);
    }

    @Override
    public String format(String word) {
        if (ReUtil.isMatch(PascalCaseFormat.REG, word)) {
            char[] array = word.toCharArray();
            array[0] = Character.toLowerCase(array[0]);
            return new String(array);
        }
        // 剩余输入，按照[ _-]分割单词，每个单词首字母大写
        String[] words = word.split("[ _-]");
        StringBuilder sb = new StringBuilder();
        for (String word1 : words) {
            sb.append(Character.toUpperCase(word1.charAt(0)));
            sb.append(word1.substring(1).toLowerCase());
        }
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    @Override
    public int getSelectIndex() {
        return 2;
    }
}
