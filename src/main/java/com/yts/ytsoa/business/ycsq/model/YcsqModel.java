package com.yts.ytsoa.business.ycsq.model;

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

@ApiModel(value = "用车申请")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YcsqModel implements Serializable {
    /**
     * 主键uuid
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人", name = "sqr", dataType = "String")
    private String sqr;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;
    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期", name = "sqrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sqrq;
    /**
     * 用车事由
     */
    @ApiModelProperty(value = "用车事由", name = "ycsy", dataType = "String")
    private String ycsy;
    /**
     * 车牌号归属地
     */
    @ApiModelProperty(value = "车牌号归属地", name = "cphgsd", dataType = "String")
    private String cphgsd;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", name = "cph", dataType = "String")
    private String cph;
    /**
     * 出发公里数
     */
    @ApiModelProperty(value = "出发公里数", name = "cfgls", dataType = "String")
    private String cfgls;
    /**
     * 出发时间
     */
    @ApiModelProperty(value = "出发时间", name = "cfsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cfsj;
    /**
     * 返回时间
     */
    @ApiModelProperty(value = "返回时间", name = "fhsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fhsj;
    /**
     * 实际返回时间
     */
    @ApiModelProperty(value = "实际返回时间", name = "sjfhsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sjfhsj;
    /**
     * 返回公里数
     */
    @ApiModelProperty(value = "返回公里数", name = "fhgls", dataType = "String")
    private String fhgls;
    /**
     * 用车状态
     */
    @ApiModelProperty(value = "用车状态", name = "zt", dataType = "int")
    private int zt;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    private String bz;
    /**
     * 归还人
     */
    @ApiModelProperty(value = "归还人", name = "ghr", dataType = "String")
    private String ghr;
    /**
     * 归还日期
     */
    @ApiModelProperty(value = "归还日期", name = "ghrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ghrq;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    /**
     * 审核结果
     */
    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "int")
    private int shjg;

}