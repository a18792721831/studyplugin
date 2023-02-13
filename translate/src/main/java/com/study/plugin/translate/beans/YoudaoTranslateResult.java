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
public class YoudaoTranslateResult implements Serializable {
    private String errorCode;
    private String query;
    private List<String> translation;
    private String l;
}
