package com.yts.ytsoa.business.rgtj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RgtjModel {
    @ApiModelProperty(value = "uuid", name = "主键", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "name", name = "员工姓名", dataType = "String")
    private String name;
    @ApiModelProperty(value = "xmts", name = "项目天数", dataType = "Double")
    private Double xmts;
    @ApiModelProperty(value = "sjgss", name = "实际工时数", dataType = "Double")
    private Double sjgss;
    @ApiModelProperty(value = "ccts", name = "出差天数", dataType = "int")
    private int ccts;
    @ApiModelProperty(value = "xmid", name = "项目id", dataType = "String")
    private String xmid;
}
