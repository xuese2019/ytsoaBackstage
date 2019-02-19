package com.yts.ytsoa.business.bggl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "报告管理表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BgglModel implements Serializable {
    /**
     * 主键uuid
     */
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    @ApiModelProperty(value = "报告编号", name = "bgbh", dataType = "String")
    private String bgbh;
    /**
     * 报告主办人
     */
    @ApiModelProperty(value = "报告主办人", name = "bgzbr", dataType = "String")
    private String bgzbr;
    /**
     * 申请方
     */
    @ApiModelProperty(value = "申请方", name = "sqf", dataType = "String")
    private String sqf;
    /**
     * 报告类型
     */
    @ApiModelProperty(value = "报告类型（专项审计，报表审计，验资，管理建议书，核查报告）", name = "bglx", dataType = "String")
    private int bglx;
    /**
     * 报告册数
     */
    @ApiModelProperty(value = "报告册数", name = "bgcs", dataType = "int")
    private int bgcs;
    /**
     * 报告日期
     */
    @ApiModelProperty(value = "报告日期", name = "bgrq", dataType = "Date")
    @NotNull(message = "入职日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bgrq;
    /**
     * 审核结果
     */
    @ApiModelProperty(value = "状态（1未审核/2已审核/3项目经理审核4/部门经理审核/5质控部审核6合伙人审核7未通过）", name = "shjg", dataType = "int")
    private int shjg;
    /**
     * 项目性质
     */
    @ApiModelProperty(value = "项目性质", name = "xmxz", dataType = "String")
    private String xmxz;
    /**
     * 报告名称
     */
    @ApiModelProperty(value = "报告名称", name = "bgmc", dataType = "String")
    private String bgmc;
    /**
     * 项目经理复核
     */
    @ApiModelProperty(value = "1未审/2已审项目经理复核", name = "xmjlfh", dataType = "int")
    private int xmjlfh;
    /**
     * 部门经理复核
     */
    @ApiModelProperty(value = "1未审/2已审部门经理复核", name = "bmjlfh", dataType = "int")
    private int bmjlfh;
    /**
     * 质控部复核
     */
    @ApiModelProperty(value = "1未审/2已审部门经理复核", name = "bmjlfh", dataType = "int")
    private int zkbfh;
    /**
     * 项目合伙人复核
     */
    @ApiModelProperty(value = "1未审/2已审项目合伙人复核", name = "xmhhrfh", dataType = "int")
    private int xmhhrfh;
    /**
     * 报告防伪编号前缀
     */
    @ApiModelProperty(value = "报告防卫编号前缀", name = "bgfwbhQz", dataType = "String")
    private String bgfwbhQz;
    /**
     * 报告防伪编号后缀
     */
    @ApiModelProperty(value = "报告防伪编号后缀", name = "bgfwbhHz", dataType = "int")
    private int bgfwbhHz;
    /**
     * 资产总额
     */
    @ApiModelProperty(value = "资产总额", name = "zcze", dataType = "BigDecimal")
    private BigDecimal zcze;
    /**
     * 负债总额
     */
    @ApiModelProperty(value = "负债总额", name = "fzze", dataType = "BigDecimal")
    private BigDecimal fzze;
    /**
     * 收入利润
     */
    @ApiModelProperty(value = "收入利润", name = "srlr", dataType = "BigDecimal")
    private BigDecimal srlr;
    /**
     * 利润总额
     */
    @ApiModelProperty(value = "利润总额", name = "lrze", dataType = "BigDecimal")
    private BigDecimal lrze;
    /**
     * 净利润
     */
    @ApiModelProperty(value = "净利润", name = "jlr", dataType = "BigDecimal")
    private BigDecimal jlr;
    /**
     * 修改次数
     */
    private int xgcs;
    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因", name = "xgyy", dataType = "String")
    private String xgyy;
    /**
     * 签字注师1
     */
    @ApiModelProperty(value = "签字注师1", name = "qzzs1", dataType = "String")
    private String qzzs1;
    /**
     * 签字注师2
     */
    @ApiModelProperty(value = "签字注师2", name = "qzzs2", dataType = "String")
    private String qzzs2;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型", name = "xmlx", dataType = "String")
    private String xmlx;

    /**
     * 归档有效期
     */
    @ApiModelProperty(value = "归档有效期", name = "gdyxq", dataType = "int")
    private int gdyxq;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目子名称id", name = "xmzmcid", dataType = "String")
    private String xmid;

    /*@ApiModelProperty(value = "报告类型", name = "model")
    private BglxModel model;*/
    @ApiModelProperty(value = "编号", name = "bh", dataType = "int")
    private int bh;
    @ApiModelProperty(value = "多条报告", name = "bgs", dataType = "String")
    private String[][] bgs;
    /**
     * 报告的最终状态
     * 当所有的报告全部出具后，那么要给一个状态3，表示最终的报告出具完成
     * 如果所有的报告没有都完成，那么就不给这个状态
     */
    @ApiModelProperty(value = "完成状态", name = "wczt", dataType = "int")
    private int wczt;
}
