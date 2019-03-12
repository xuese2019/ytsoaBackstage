package com.yts.ytsoa.business.jygl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "借阅审核记录")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultModel {
    /**
     * 底稿借阅编号
     */
    @ApiModelProperty(value = "底稿借阅编号", name = "dgjybh", dataType = "String")
    private String dgjybh;
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "int")
    private int shjg;
    @ApiModelProperty(value = "借阅状态", name = "jyzt", dataType = "int")
    private int jyzt;
}
