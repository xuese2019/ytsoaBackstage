package com.yts.ytsoa.business.shjd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "审核阶段")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShjdModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "审核人1", name = "shr1", dataType = "String")
    private String shr1;
    @ApiModelProperty(value = "审核人2", name = "shr2", dataType = "String")
    private String shr2;
    @ApiModelProperty(value = "审核人3", name = "shr3", dataType = "String")
    private String shr3;
    @ApiModelProperty(value = "审核人4", name = "shr4", dataType = "String")
    private String shr4;
    @ApiModelProperty(value = "审核人5", name = "shr5", dataType = "String")
    private String shr5;
    @ApiModelProperty(value = "审核人6", name = "shr6", dataType = "String")
    private String shr6;
    @ApiModelProperty(value = "审核人7", name = "shr7", dataType = "String")
    private String shr7;
    @ApiModelProperty(value = "审核人8", name = "shr8", dataType = "String")
    private String shr8;
    @ApiModelProperty(value = "审核人9", name = "shr9", dataType = "String")
    private String shr9;
    @ApiModelProperty(value = "审核人10", name = "shr10", dataType = "String")
    private String shr10;
}
