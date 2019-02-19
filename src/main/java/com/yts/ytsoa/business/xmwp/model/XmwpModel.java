package com.yts.ytsoa.business.xmwp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmwpModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    @NotBlank(message = "项目名称不能为空")
    private String xmmc;
    @ApiModelProperty(value = "委托方", name = "wtf", dataType = "String")
    @NotBlank(message = "委托方不能为空")
    private String wtf;
    //委托方联系人
    @ApiModelProperty(value = "委托方联系人")
    @NotBlank(message = "委托方联系人不能为空")
    private String wtflxr;
    //委托方联系电话
    @ApiModelProperty(value = "委托方联系电话", name = "wtflxdh", dataType = "String")
    @NotBlank(message = "委托方联系电话不能为空")
    @Length(min = 11, max = 11, message = "电话最少为11位")
    private String wtflxdh;
    //委托时间
    @ApiModelProperty(value = "委托时间", name = "wtsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date wtsj;
    //被审计单位
    @ApiModelProperty(value = "被审计单位", name = "bsjdw", dataType = "String")
    @NotBlank(message = "被审计单位不能为空")
    private String bsjdw;
    @ApiModelProperty(value = "委派人", name = "wpr", dataType = "String")
    @NotBlank(message = "委托人不能为空")
    private String wpr;
    @ApiModelProperty(value = "承接部门", name = "cjbm", dataType = "String")
    @NotBlank(message = "承接部门不能为空")
    private String cjbm;
    @ApiModelProperty(value = "项目负责人", name = "xmfzr", dataType = "String")
    @NotBlank(message = "项目负责人不能为空")
    private String xmfzr;
    @ApiModelProperty(value = "预计费用", name = "yjfy", dataType = "BigDecimal")
    @NotBlank(message = "预计费用不能为空，单位：万元")
    private BigDecimal yjsf;
    /**
     * 1，未进点
     * 2，项目进行中
     * 3，项目经理复核
     * 4，部门经理复核
     * 5，质控部复核
     * 6，项目合伙人复核
     * 7，报告已出具
     * 8，底稿已归档
     * 9.最终完成状态
     */
    @ApiModelProperty(value = "状态（1未承接/2已承接）", name = "ywzt", dataType = "int")
    private int ywzt;
    @ApiModelProperty(value = "委派单生成时间", name = "wpdscsj", dataType = "Timestamp")
    private Timestamp wpdscsj;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    private String bz;
    @ApiModelProperty(value = "项目状态(1未完成2已完成)", name = "xmzt", dataType = "int")
    private int xmzt;
    @ApiModelProperty(value = "业务类型", name = "ywlx", dataType = "String")
    private String ywlx;
    @ApiModelProperty(value = "项目分类", name = "xmfl", dataType = "String")
    private String xmfl;
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    /**
     * 低1，中2，高3
     */
    @ApiModelProperty(value = "风险评估", name = "fxpg", dataType = "String")
    private String fxpg;
    @ApiModelProperty(value = "项目开始时间", name = "xmkssj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmkssj;
    @ApiModelProperty(value = "项目现场结束时间", name = "xmxcjssj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmxcjssj;
    @ApiModelProperty(value = "项目标准工时", name = "xmbzgs", dataType = "String")
    private String xmbzgs;
    @ApiModelProperty(value = "报告出具时间", name = "bgcjsj", dataType = "Date")
    private Date bgcjsj;
    @ApiModelProperty(value = "员工列表", name = "yglb", dataType = "List")
    private String yglb;
    /**
     * 默认是1；否
     */
    @ApiModelProperty(value = "是否签署合同", name = "sfqsht", dataType = "int")
    private int sfqsht;
    /**
     * 默认是1；否
     */
    @ApiModelProperty(value = "发票是否开具", name = "fpsfkj", dataType = "int")
    private int fpsfkj;
    /**
     * 默认是1；否
     */
    @ApiModelProperty(value = "是否收费", name = "sfsf", dataType = "int")
    private int sfsf;

    @ApiModelProperty(value = "(1已审2未审)项目审核状态", name = "xmshzt", dataType = "int")
    private int xmshzt;
    /*项目子名称*/
    private List<XmzmcModel> xmzmcModels = new ArrayList<>();

    @ApiModelProperty(value = "合伙人审核1是2否", name = "hhrsh", dataType = "int")
    private int hhrsh;
    @ApiModelProperty(value = "质控部审核1是2否", name = "zkbsh", dataType = "int")
    private int zkbsh;
}
