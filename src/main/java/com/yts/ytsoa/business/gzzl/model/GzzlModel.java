package com.yts.ytsoa.business.gzzl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "工作指令")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GzzlModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "发送人", name = "fsr", dataType = "String")
    private String fsr;
    @ApiModelProperty(value = "接收人", name = "jsr", dataType = "String")
    private String jsr;
    @ApiModelProperty(value = "发送时间", name = "fssj", dataType = "Date")
    private Date fssj;
    @ApiModelProperty(value = "发送内容", name = "fsnr", dataType = "String")
    private String fsnr;
    @ApiModelProperty(value = "项目id.", name = "xmid", dataType = "String")
    private String xmid;
    @ApiModelProperty(value = "状态.", name = "zt", dataType = "int")
    private int zt;

}
