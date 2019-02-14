package com.yts.ytsoa.business.kqtj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "考勤统计")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KqtjModel implements Serializable {
    /**
     * 主键uuid
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 月份
     */
    @ApiModelProperty(value = "月份", name = "yf", dataType = "Date")
    private Date yf;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "xm", dataType = "String")
    private String xm;
    /**
     * 部门
     */
    @ApiModelProperty(value = "部门", name = "bm", dataType = "String")
    private String bm;
    /**
     * 项目天数
     */
    @ApiModelProperty(value = "项目天数", name = "xmts", dataType = "int")
    private int xmts;
    /**
     * 出差天数
     */
    @ApiModelProperty(value = "出差天数", name = "ccts", dataType = "int")
    private int ccts;
    /**
     * 休假天数
     */
    @ApiModelProperty(value = "休假天数", name = "xjts", dataType = "int")
    private int xjts;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "zt", dataType = "int")
    private int zt;
    /**
     * 所属年份
     */
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
}
