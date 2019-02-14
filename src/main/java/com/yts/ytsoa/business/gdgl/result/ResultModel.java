package com.yts.ytsoa.business.gdgl.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "归档审核表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultModel {
    @ApiModelProperty(value = "归档申请编号后缀", name = "gdsqbh_hz", dataType = "int")
    @Length(max = 50)
    private String gdsqbh_hz;

    @ApiModelProperty(value = "档案名称", name = "damc", dataType = "String")
    private String damc;

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;

    @ApiModelProperty(value = "被审计单位", name = "bsjdw", dataType = "String")
    private String bsjdw;

    @ApiModelProperty(value = "承接部门", name = "cjbm", dataType = "String")
    private String cjbm;

    @ApiModelProperty(value = "项目承接人", name = "xmcjr", dataType = "String")
    private String xmcjr;

    @ApiModelProperty(value = "报告册数", name = "bgcs", dataType = "int")
    private int bgcs;

    @ApiModelProperty(value = "底稿册数", name = "dgcs", dataType = "int")
    private int dgcs;
}
