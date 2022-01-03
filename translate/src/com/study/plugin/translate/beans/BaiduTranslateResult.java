package com.study.plugin.translate.beans;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiduTranslateResult implements Serializable {
    private String from;
    private String to;
    private List<BaiduTransResult> trans_result;
    private Integer error_code;
}
