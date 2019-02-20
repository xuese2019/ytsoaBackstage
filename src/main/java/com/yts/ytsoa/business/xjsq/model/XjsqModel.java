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
     * 1未审核
     * 2已审核
     * 3审核未通过
     * 4部门经理审核
     * 5所长审核
     */
    @ApiModelProperty(value = "（1/2）是否同意", name = "shjg", dataType = "int")
    private int shjg;
    @ApiModelProperty(value = "签到时间", name = "qdsj", dataType = "Date")
    private Date qdsj;
    @ApiModelProperty(value = "签退时间", name = "qtsj", dataType = "Date")
    private Date qtsj;
    @ApiModelProperty(value = "签到地点", name = "qddd", dataType = "String")
    private String qddd;
    @ApiModelProperty(value = "签退地点", name = "qtdd", dataType = "String")
    private String qtdd;

}
