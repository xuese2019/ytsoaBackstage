package com.yts.ytsoa.business.yzsq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@ApiModel(value = "用章申请")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YzsqModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;

    @ApiModelProperty(value = "申请人", name = "sqr", dataType = "String")
    private String sqr;

    @ApiModelProperty(value = "文件名称", name = "wjmc", dataType = "String")
    private String wjmc;

    @ApiModelProperty(value = "sqrq", name = "sqrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sqrq;

    @ApiModelProperty(value = "用章单位", name = "yzdw", dataType = "String")
    private String yzdw;

    @ApiModelProperty(value = "申请类型", name = "sqlx", dataType = "String")
    private String sqlx;

    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    private String bz;

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
    /**
     * 1:未审核
     * 2：已审核
     */
    @ApiModelProperty(value = "审核状态", name = "shzt", dataType = "Integer")
    private Integer shzt;

    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
}
