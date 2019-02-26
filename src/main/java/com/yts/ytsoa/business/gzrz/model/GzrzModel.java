package com.yts.ytsoa.business.gzrz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GzrzModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
    @ApiModelProperty(value = "投入该项目时间", name = "trgxmsj", dataType = "double")
    @NotNull(message = "投入该项目的时间不能为空")
    private Double trgxmsj;
    /**
     * 是否出差
     * 0：不出差
     * 1:出差
     */
    @ApiModelProperty(value = "是否出差", name = "sfcc", dataType = "int")
    private Integer sfcc;
    @ApiModelProperty(value = "工作内容", name = "gznr", dataType = "String")
    private String gznr;
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
    /**
     * 1:非项目日志
     * 2：项目日志
     */
    @ApiModelProperty(value = "日志类型（非/项目日志）", name = "rzzt", dataType = "int")
    private Integer rzlx;
    @ApiModelProperty(value = "提交人", name = "tjr", dataType = "String")
    private String tjr;
    @ApiModelProperty(value = "提交时间", name = "tjsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tjsj;
    @ApiModelProperty(value = "有效工时", name = "yxgs", dataType = "int")
    private int yxgs;
    @ApiModelProperty(value = "日期", name = "rq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date rq;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    /**
     * 出差地点
     */
    @ApiModelProperty(value = "出差地点", name = "ccdd", dataType = "String")
    private String ccdd;

    @ApiModelProperty(value = "出差天数", name = "ccts", dataType = "int")
    private int ccts;
}
