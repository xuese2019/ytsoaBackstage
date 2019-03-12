package com.yts.ytsoa.business.yzsq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "审核记录")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsModel {
    @ApiModelProperty(value = "文件名称", name = "wjmc", dataType = "String")
    private String wjmc;
    @ApiModelProperty(value = "用章单位", name = "yzdw", dataType = "String")
    private String yzdw;
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "int")
    private String shjg;
}
