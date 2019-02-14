package com.yts.ytsoa.business.zsk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "知识库")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZskModel implements Serializable {

    /**
     * 主键uuid
     */

    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 文件来源
     */

    @ApiModelProperty(value = "文件来源", name = "wjly", dataType = "String")
    private String wjly;
    /**
     * 文件主题
     */

    @ApiModelProperty(value = "文件主题", name = "wjzt", dataType = "String")
    private String wjzt;
    /**
     * 类型
     */

    @ApiModelProperty(value = "类型", name = "lx", dataType = "int")
    private int lx;
    /**
     * 加入时间
     */

    @ApiModelProperty(value = "加入时间", name = "jrsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jrsj;
    /**
     * 添加人
     */

    @ApiModelProperty(value = "添加人", name = "tjr", dataType = "String")
    private String tjr;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称", name = "wjmc", dataType = "String")
    private String wjmc;
}
