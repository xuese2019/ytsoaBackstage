package com.yts.ytsoa.business.xmcj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "项目子名称表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmzmcModel {
    @ApiModelProperty(value = "uuid", name = "主键", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "xmzmc", name = "项目子名称", dataType = "String")
    private String xmzmc;
    @ApiModelProperty(value = "parentid", name = "父级id", dataType = "String")
    private String parentid;

}
