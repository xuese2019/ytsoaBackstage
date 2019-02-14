package com.yts.ytsoa.business.xmjl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "项目内部讨论组")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmjlModel implements Serializable {
    /**
     * 主键uuid
     */
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    /**
     * 回复人
     */
    @ApiModelProperty(value = "回复人", name = "hfr", dataType = "String")
    private String hfr;
    /**
     * 回复时间
     */
    @ApiModelProperty(value = "回复时间", name = "hfsj", dataType = "Date")
    private Date hfsj;
    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容", name = "hfnr", dataType = "String")
    private String hfnr;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;


}
