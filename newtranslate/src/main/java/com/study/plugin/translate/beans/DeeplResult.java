package com.study.plugin.translate.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeeplResult {
    private List<DeeplTranslateResult> translations;
}
