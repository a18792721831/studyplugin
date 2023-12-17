package com.study.plugin.translate.format;

import cn.hutool.core.util.ReUtil;
import com.intellij.openapi.components.Service;

@Service
public class PascalCaseFormat implements IWordFormat {

    public static final String REG = "^([A-Z][a-z]*)+$";

    @Override
    public boolean isMatch(String word) {
        return ReUtil.isMatch(REG, word);
    }

    @Override
    public String format(String word) {
        // 假设输入是小驼峰,小驼峰把第一个字符转为大写就OK
        if (ReUtil.isMatch(CamelCaseFormat.REG, word)) {
            char[] array = word.toCharArray();
            array[0] = Character.toUpperCase(array[0]);
            return new String(array);
        }
        // 剩余输入，按照[ _-]分割单词，每个单词首字母大写
        String[] words = word.split("[ _-]");
        StringBuilder sb = new StringBuilder();
        for (String word1 : words) {
            sb.append(Character.toUpperCase(word1.charAt(0)));
            sb.append(word1.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public int getSelectIndex() {
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(ReUtil.isMatch("^([A-Z][a-z]*)+$", "XxYy"));
    }
}
