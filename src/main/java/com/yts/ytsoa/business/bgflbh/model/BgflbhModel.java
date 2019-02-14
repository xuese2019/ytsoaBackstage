package com.yts.ytsoa.business.bgflbh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BgflbhModel implements Serializable {

    private String uuid;

    private int aBh = 1000;
    private int bBh = 3000;
    private int cBh = 5000;
    private int dBh = 6000;
    private int eBh = 0;

    private int bglx;

    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date ssnf;

    private String lb;
}
