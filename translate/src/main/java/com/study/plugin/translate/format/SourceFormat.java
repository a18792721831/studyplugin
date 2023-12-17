package com.study.plugin.translate.format;

import com.intellij.openapi.components.Service;

@Service
public class SourceFormat implements IWordFormat{
    @Override
    public boolean isMatch(String word) {
        return false;
    }

    @Override
    public String format(String word) {
        return null;
    }

    @Override
    public int getSelectIndex() {
        return 0;
    }
}
