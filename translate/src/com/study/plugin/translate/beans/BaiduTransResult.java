package com.study.plugin.translate.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiduTransResult implements Serializable {
    private String src;
    private String dst;
}
