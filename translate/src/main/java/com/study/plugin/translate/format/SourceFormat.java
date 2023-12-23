package com.study.plugin.translate.format;

import com.intellij.openapi.components.Service;

@Service
public final class SourceFormat implements IWordFormat{
    @Override
    public boolean isMatch(String word) {
        return true;
    }

    @Override
    public String format(String word) {
        return word;
    }

    @Override
    public int getSelectIndex() {
        return 0;
    }
}
