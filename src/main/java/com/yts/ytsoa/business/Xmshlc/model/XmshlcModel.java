package com.yts.ytsoa.business.Xmshlc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "项目审核流程控制表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmshlcModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "组织机构名称", name = "zzjgmc", dataType = "String")
    private String zzjgmc;
    @ApiModelProperty(value = "审核类型（1项目经理2部门经理3质控部经理4项目合伙人）", name = "uuid", dataType = "int")
    private int shlx;
}
