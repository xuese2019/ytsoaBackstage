package com.yts.ytsoa.business.shr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "审核人表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShrModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "描述", name = "ms", dataType = "String")
    private String ms;
}
