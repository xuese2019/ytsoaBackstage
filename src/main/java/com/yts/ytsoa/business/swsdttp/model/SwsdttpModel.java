package com.yts.ytsoa.business.swsdttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "事务所动态图片")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwsdttpModel {
    @ApiModelProperty(value = "uuid", name = "主键", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "swsdtid", name = "事务所动态id", dataType = "String")
    private String swsdtid;
    @ApiModelProperty(value = "tpmc", name = "图片名称", dataType = "String")
    private String tpmc;
}
