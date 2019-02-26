package com.yts.ytsoa.business.xmcy.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "返回结果集")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class resultModel {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "员工姓名", name = "name", dataType = "String")
    private String name;
    @ApiModelProperty(value = "职业技术能力", name = "zyjsnl", dataType = "String")
    private String zyjsnl;
    @ApiModelProperty(value = "工作效率", name = "gzxl", dataType = "String")
    private String gzxl;
    @ApiModelProperty(value = "沟通能力", name = "gtnl", dataType = "String")
    private String gtnl;
    @ApiModelProperty(value = "责任心及团队精神", name = "zrxjtdjs", dataType = "String")
    private String zrxjtdjs;
    @ApiModelProperty(value = "投入该项目时间", name = "trgxmsj", dataType = "double")
    private double trgxmsj;
    @ApiModelProperty(value = "项目天数", name = "xmts", dataType = "double")
    private double xmts;
    @ApiModelProperty(value = "出差天数", name = "ccts", dataType = "int")
    private int ccts;
    @ApiModelProperty(value = "评语", name = "py", dataType = "String")
    private String py;
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
}
