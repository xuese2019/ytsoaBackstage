package com.yts.ytsoa.business.pjsq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "票据申请审核记录")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResPjsqModel {
    @ApiModelProperty(value = "开票编号", name = "kpbh", dataType = "String")
    private String kpbh;
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "String")
    private String shjg;
}
