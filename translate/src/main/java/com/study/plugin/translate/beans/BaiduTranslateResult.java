package com.study.plugin.translate.beans;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaiduTranslateResult implements Serializable {
    private String from;
    private String to;
    private List<BaiduTransResult> trans_result;
    private Integer error_code;
}
