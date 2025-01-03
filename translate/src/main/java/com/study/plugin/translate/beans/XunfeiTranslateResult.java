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
public class XunfeiTranslateResult implements Serializable {
    private int code;
    private String message;
    private String sid;
    private XunfeiTranslateResultData data;
}
