package com.yts.ytsoa.business.bgshr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "报告审核人设置")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BgshrModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "部门经理id", name = "bmjlid", dataType = "String")
    private String bmjlid;
    @ApiModelProperty(value = "质控部id", name = "zkbid", dataType = "String")
    private String zkbid;
    @ApiModelProperty(value = "合伙人id", name = "hhrid", dataType = "String")
    private String hhrid;
}
