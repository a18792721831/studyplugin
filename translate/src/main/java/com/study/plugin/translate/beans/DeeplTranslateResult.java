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
public class DeeplTranslateResult implements Serializable {
    private String detected_source_language;
    private String text;
}
