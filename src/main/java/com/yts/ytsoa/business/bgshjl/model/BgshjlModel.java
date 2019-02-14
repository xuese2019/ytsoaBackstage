package com.yts.ytsoa.business.bgshjl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "报告审核记录")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BgshjlModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "报告id", name = "bgid", dataType = "String")
    private String bgid;
    @ApiModelProperty(value = "审核人id", name = "shrid", dataType = "String")
    private String shrid;
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    @ApiModelProperty(value = "是否同意", name = "tof", dataType = "int")
    private int tof;
    @ApiModelProperty(value = "顺序号", name = "sxh", dataType = "int")
    private int sxh;
}
