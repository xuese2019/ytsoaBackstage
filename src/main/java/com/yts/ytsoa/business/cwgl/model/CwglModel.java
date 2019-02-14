package com.yts.ytsoa.business.cwgl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "财务管理")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwglModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "日期", name = "rq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date rq;
    @ApiModelProperty(value = "收费类型", name = "sflx", dataType = "String")
    private String sflx;
    /**
     * 1：未收费
     * 2:已收费
     */
    @ApiModelProperty(value = "状态", name = "zt", dataType = "int")
    private int zt;
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
}
