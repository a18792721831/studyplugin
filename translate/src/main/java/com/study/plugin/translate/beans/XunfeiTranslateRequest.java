package com.study.plugin.translate.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class XunfeiTranslateRequest implements Serializable {
    private XunfeiTranslateCommon common;
    private XunfeiTranslateBusiness business;
    private XunfeiTranslateData data;
}
