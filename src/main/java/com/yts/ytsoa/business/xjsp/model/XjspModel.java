package com.yts.ytsoa.business.xjsp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XjspModel {
    /**
     * uuid主键
     */
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 休假申请人
     */
    @ApiModelProperty(value = "休假申请人", name = "xjsqr", dataType = "String")
    private String xjsqr;
    /**
     * 休假起始日期
     */
    @ApiModelProperty(value = "休假起始日期", name = "xjqsrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xjqsrq;
    /**
     * 休假事由
     */
    @ApiModelProperty(value = "休假事由", name = "xjsy", dataType = "String")
    private String xjsy;
    /**
     * 休假截止日期
     */
    @ApiModelProperty(value = "休假截至日期", name = "xjjzrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xjjzrq;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    /**
     * 状态
     * 1-同意
     * 2-不同意
     */
    @ApiModelProperty(value = "状态", name = "status", dataType = "String")
    private int status;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
}
