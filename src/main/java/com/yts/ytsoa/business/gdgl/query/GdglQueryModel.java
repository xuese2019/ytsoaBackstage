package com.yts.ytsoa.business.gdgl.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "归档管理表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GdglQueryModel implements Serializable {

    @ApiModelProperty(value = "归档申请编号后缀", name = "gdsqbh_hz", dataType = "Integer")
    private Integer gdsqbh_hz;

    @ApiModelProperty(value = "被审计单位", name = "bsjdw", dataType = "String")
    private String bsjdw;

    @ApiModelProperty(value = "承接部门", name = "cjbm", dataType = "String")
    private String cjbm;

    @ApiModelProperty(value = "项目经理", name = "xmcjr", dataType = "String")
    private String xmcjr;

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
}
