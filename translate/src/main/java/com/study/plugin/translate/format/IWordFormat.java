package com.study.plugin.translate.format;

public interface IWordFormat {

    boolean isMatch(String word);

    String format(String word);

    int getSelectIndex();
}
