package com.yts.ytsoa.business.bgflbh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BglxModel {
    private int bglx;
    private String lb;
    private int bgbhHz;
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date ssnf;
}
