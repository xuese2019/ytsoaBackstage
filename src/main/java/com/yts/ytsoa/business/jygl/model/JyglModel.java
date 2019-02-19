package com.yts.ytsoa.business.jygl.model;

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

@ApiModel(value = "借阅管理")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JyglModel implements Serializable {
    /**
     * uuid主键
     */

    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 档案名称
     */
    @ApiModelProperty(value = "档案名称", name = "damc", dataType = "String")
    private String damc;
    /**
     * 所属年份
     */

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
    /**
     * 备注
     */

    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    private String bz;
    /**
     * 归还日期
     */

    @ApiModelProperty(value = "归还日期", name = "ghrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ghrq;
    /**
     * 归还人
     */

    @ApiModelProperty(value = "归还人", name = "ghr", dataType = "String")
    private String ghr;
    /**
     * 申请方
     */
    @ApiModelProperty(value = "申请方", name = "sqf", dataType = "String")
    private String sqf;
    /**
     * 借阅日期
     */
    @ApiModelProperty(value = "借阅日期", name = "jyrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    /**
     * 借阅人
     */
    @ApiModelProperty(value = "借阅人", name = "jyr", dataType = "String")
    private String jyr;
    /**
     * 底稿借阅编号
     */
    @ApiModelProperty(value = "底稿借阅编号", name = "dgjybh", dataType = "String")
    private String dgjybh;
    /**
     * 项目名称
     */

    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;

    /**
     * 借阅状态
     * 1:未借阅
     * 2：已借阅
     */
    @ApiModelProperty(value = "借阅状态", name = "jyzt", dataType = "int")
    private int jyzt;
    /**
     * 报告id
     */
    @ApiModelProperty(value = "底稿id", name = "dgid", dataType = "String")
    private String dgid;
    @ApiModelProperty(value = "借阅人名称", name = "name", dataType = "String")
    private String name;
    /**
     * 审核结果
     * 1：不同意
     * 2：同意
     */
    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "int")
    private int shjg;
}
