package com.study.plugin.translate.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//{"src_tgt":[],"target":["initializesTheHeadAndTailPointers"],"confidence":0.8,"rc":0}
public class CaiyunTranslateResult implements Serializable {
    private Object[] srcTgt;
    private String[] target;
    private Double confidence;
    private Integer rc;
}
