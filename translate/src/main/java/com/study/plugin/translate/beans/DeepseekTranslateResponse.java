package com.study.plugin.translate.beans;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiayq
 * @Date 2023-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeepseekTranslateResponse implements Serializable {
    private String id;
    private String object;
    private long created;
    private String model;
    private String systemFingerprint;
    private DeepseekTranslateUsage usage;
    private List<DeepseekTranslateChoice> choices;
}
