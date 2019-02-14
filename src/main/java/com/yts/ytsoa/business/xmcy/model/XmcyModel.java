package com.yts.ytsoa.business.xmcy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "员工列表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmcyModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
    @ApiModelProperty(value = "员工id", name = "ygid", dataType = "String")
    private String ygid;
    @ApiModelProperty(value = "专业技术能力", name = "zyjsnl", dataType = "int")
    private int zyjsnl;
    @ApiModelProperty(value = "工作效率", name = "gzxl", dataType = "String")
    private String gzxl;
    @ApiModelProperty(value = "沟通能力", name = "gtnl", dataType = "String")
    private String gtnl;
    @ApiModelProperty(value = "责任心及团队精神", name = "zrxjtdjs", dataType = "String")
    private String zrxjtdjs;
    @ApiModelProperty(value = "评语", name = "py", dataType = "String")
    private String py;
    @ApiModelProperty(value = "员工姓名", name = "name", dataType = "String")
    private String name;
    @ApiModelProperty(value = "项目天数", name = "xmts", dataType = "double")
    private double xmts;

}
