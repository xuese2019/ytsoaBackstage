package com.yts.ytsoa.business.shrsz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "审核人设置表")
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShrszModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "部门经理id", name = "bmjlid", dataType = "String")
    private String bmjlid;
    @ApiModelProperty(value = "质管部id", name = "zgbid", dataType = "String")
    private String zgbid;
    @ApiModelProperty(value = "合伙人id", name = "hhrid", dataType = "String")
    private String hhrid;
    @ApiModelProperty(value = "员工id", name = "accountid", dataType = "String")
    private String accountid;
}
