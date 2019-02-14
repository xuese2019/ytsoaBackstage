package com.yts.ytsoa.business.gzsq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class gzsqModel {
    /**
     * uuid 主键
     */
    private String uuid;
    /**
     * 申请人
     */
    private String sqr;
    /**
     * 申请日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sqrq;
    /**
     * 用章单位
     */
    private String yzdw;
    /**
     * 申请类型
     */
    private String sqlx;
}
