package com.yts.ytsoa.business.xmyq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@ApiModel(value = "项目审核")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmyqModel {
    /**
     * 员工主键uuid
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 延期理由
     */
    @ApiModelProperty(value = "延期理由", name = "yqly", dataType = "String")
    private String yqly;
    /**
     * 填写时间
     */
    @ApiModelProperty(value = "填写时间", name = "txsj", dataType = "Date")
    private Date txsj;
    /**
     * 填写人
     */
    @ApiModelProperty(value = "填写人", name = "txr", dataType = "String")
    private String txr;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    /**
     * 承接人
     */
    @ApiModelProperty(value = "承接人", name = "xmcjr", dataType = "String")
    private String xmcjr;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", name = "xmkssj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmkssj;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    /**
     * 业务状态
     */
    @ApiModelProperty(value = "业务状态", name = "ywzt", dataType = "int")
    private int ywzt;
    /**
     * 项目结束时间
     */
    @ApiModelProperty(value = "项目结束时间", name = "xmxcjssj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmxcjssj;
    @ApiModelProperty(value = "系统当前时间", name = "xtdqsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xtdqsj;
    /**
     * 项目状态
     * 1：未完成
     * 2：已完成
     */
    @ApiModelProperty(value = "项目状态", name = "xmzt", dataType = "int")
    private int xmzt;
}
