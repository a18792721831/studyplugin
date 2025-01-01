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
@Builder
public class BaiduYi34BTranslateMessage implements Serializable {
    private String role;
    private String content;
}
