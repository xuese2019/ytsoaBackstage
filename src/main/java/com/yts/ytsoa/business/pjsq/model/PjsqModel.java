package com.yts.ytsoa.business.pjsq.model;

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

@ApiModel(value = "票据申请")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PjsqModel {
    /**
     * uuid主键
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 开票编号
     */
    @ApiModelProperty(value = "开票编号", name = "kpbh", dataType = "String")
    private String kpbh;
    /**
     * 申请方
     */
    @ApiModelProperty(value = "申请方", name = "sqf", dataType = "String")
    private String sqf;
    /**
     * 发票申请人
     */
    @ApiModelProperty(value = "发票申请人", name = "fpsqr", dataType = "String")
    private String fpsqr;
    /**
     * 开票金额
     */
    @ApiModelProperty(value = "开票金额", name = "kpje", dataType = "BigDecimal")
    private BigDecimal kpje;
    /**
     * 发票类型
     * 1：总所发票
     * 2：分所发票
     */
    @ApiModelProperty(value = "发票类型", name = "fplx", dataType = "String")
    private String fplx;
    /**
     * 开票类型
     */
    @ApiModelProperty(value = "开票类型", name = "kplx", dataType = "String")
    private String kplx;
    /**
     * 发票类别
     * 1:增值税专用发票
     * 2:通用机打发票
     * 3:通用手工发票
     * 4:通用定额发票
     * 5:其他发票
     */
    @ApiModelProperty(value = "发票类别", name = "fplb", dataType = "String")
    private String fplb;
    /**
     * 开票名称
     */
    @ApiModelProperty(value = "开票名称", name = "kpmc", dataType = "String")
    private String kpmc;
    /**
     * 税号
     */
    @ApiModelProperty(value = "税号", name = "sh", dataType = "String")
    private String sh;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "dz", dataType = "String")
    private String dz;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", name = "dh", dataType = "String")
    private String dh;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行", name = "khh", dataType = "String")
    private String khh;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "zh", dataType = "String")
    private String zh;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;
    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人", name = "xmfzr", dataType = "String")
    private String xmfzr;
    /**
     * 部门
     */
    @ApiModelProperty(value = "部门", name = "bm", dataType = "String")
    private String bm;
    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期", name = "kprq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date kprq;

    @ApiModelProperty(value = "审核状态，1未审核2已审核", name = "shzt", dataType = "int")
    private int shzt;
    /**
     * 多表联查返回的字段
     */
    private String name;
}
