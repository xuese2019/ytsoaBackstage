package com.yts.ytsoa.business.xjsq.model;

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
public class XjsqModel {
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String xjsy;
    /**
     * 休假截止日期
     */
    @ApiModelProperty(value = "休假截至日期", name = "xjjzrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xjjzrq;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
    /**
     * 是否同意
     * 1同意
     * 2不同意
     */
    @ApiModelProperty(value = "（1/2）是否同意", name = "sfty", dataType = "int")
    private int sfty;
}
