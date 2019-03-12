package com.yts.ytsoa.business.khgl.model;

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

@ApiModel(value = "客户管理表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhglModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    @ApiModelProperty(value = "业务回复人", name = "ywhfr", dataType = "String")
    private String ywhfr;
    @ApiModelProperty(value = "业务回复时间", name = "ywhfsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ywhfsj;
    @ApiModelProperty(value = "业务回复内容", name = "ywhfnr", dataType = "String")
    private String ywhfnr;
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
}
