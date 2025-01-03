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
public class DeepseekTranslateRequest implements Serializable {
    private String model;
    private boolean stream;
    private List<DeepseekTranslateMessage> messages;
}
