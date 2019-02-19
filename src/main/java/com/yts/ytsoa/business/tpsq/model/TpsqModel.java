package com.yts.ytsoa.business.tpsq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "退票申请")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TpsqModel {
    /**
     * uuid 主键
     */
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id", name = "prentid", dataType = "String")
    private String prentid;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", name = "bh", dataType = "String")
    private String bh;

    /**
     * 开票编号
     */
    @ApiModelProperty(value = "开票编号", name = "kpbh", dataType = "String")
    private String kpbh;
    /**
     * 退票金额
     */
    @ApiModelProperty(value = "退票金额", name = "tpje", dataType = "BigDecimal")
    private BigDecimal tpje;

    /**
     * 退票申请人
     */
    @ApiModelProperty(value = "退票申请人", name = "tpsqr", dataType = "String")
    private String tpsqr;
    /**
     * 申请方
     */
    @ApiModelProperty(value = "申请方", name = "sqf", dataType = "String")
    private String sqf;
    /**
     * 退票申请时间
     */
    @ApiModelProperty(value = "退票申请时间", name = "tpsqsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tpsqsj;
    /**
     * 退票原因
     */
    @ApiModelProperty(value = "退票原因", name = "tpyy", dataType = "String")
    private String tpyy;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
    /**
     * 发票类型
     */
    @ApiModelProperty(value = "发票类型", name = "fplx", dataType = "int")
    private int fplx;
    /**
     * 发票邮寄联系人
     */
    @ApiModelProperty(value = "发票邮寄联系人", name = "fpyjlxr", dataType = "String")
    private String fpyjlxr;
    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期", name = "kprq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date kprq;
    /**
     * 发票申请人
     */
    @ApiModelProperty(value = "发票申请人", name = "fpsqr", dataType = "String")
    private String fpsqr;
    /**
     * 发票类别
     */
    @ApiModelProperty(value = "发票类别", name = "fplb", dataType = "String")
    private String fplb;
    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人", name = "xmfzr", dataType = "String")
    private String xmfzr;

    @ApiModelProperty(value = "审核状态,1不同意2同意", name = "shjg", dataType = "int")
    private int shjg;

    /**
     * 多表联查返回的数据
     */
    private String name;
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
}