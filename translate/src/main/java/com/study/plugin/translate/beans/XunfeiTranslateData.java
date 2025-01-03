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
public class XunfeiTranslateData implements Serializable {
    private String text;
}
