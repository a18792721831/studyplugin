package com.study.plugin.translate.format;

import cn.hutool.core.util.ReUtil;
import com.intellij.openapi.components.Service;

@Service
public class NoSpaceFormat implements IWordFormat{

    public static final String REG = "^[a-z]+$";

    @Override
    public boolean isMatch(String word) {
        return ReUtil.isMatch(REG, word);
    }

    @Override
    public String format(String word) {
        return word.replaceAll(" ", "");
    }

    @Override
    public int getSelectIndex() {
        return 5;
    }
}
