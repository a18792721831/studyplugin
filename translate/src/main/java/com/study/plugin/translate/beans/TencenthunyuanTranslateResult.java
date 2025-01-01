package com.study.plugin.translate.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class TencenthunyuanTranslateResult implements Serializable {
    private List<TencenthunyuanTranslationChoice> choices;
}
