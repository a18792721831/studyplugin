package com.study.plugin.translate.beans;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiayq
 * @Date 2024-12-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaiduYi34BTranslateRequest implements Serializable {
    private List<BaiduYi34BTranslateMessage> messages;
}
