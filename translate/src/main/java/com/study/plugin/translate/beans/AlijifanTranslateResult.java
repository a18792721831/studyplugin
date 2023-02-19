package com.study.plugin.translate.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author jiayq
 * @Date 2023-02-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlijifanTranslateResult implements Serializable {
    private String WordCount;
    private String Translated;
}
