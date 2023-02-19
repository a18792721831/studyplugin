package com.study.plugin.translate.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HuaweijifanTranslateResult implements Serializable {
    private String srcText;
    private String translatedText;
    private String from;
    private String to;
}
