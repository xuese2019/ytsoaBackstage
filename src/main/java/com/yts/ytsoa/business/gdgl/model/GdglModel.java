package com.yts.ytsoa.business.gdgl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GdglModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;

    @ApiModelProperty(value = "项目名称", name = "xmmc", dataType = "String")
    private String xmmc;

    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;

    @ApiModelProperty(value = "项目子名称", name = "xmzmc", dataType = "String")
    private String xmzmc;

    @ApiModelProperty(value = "归档申请编号后缀", name = "gdsqbh_hz", dataType = "String")
    @Length(max = 50)
    private String gdsqbh_hz;

    @ApiModelProperty(value = "档案名称", name = "damc", dataType = "String")
    private String damc;

    @ApiModelProperty(value = "申请日期", name = "sqrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sqrq;

    @ApiModelProperty(value = "申请方", name = "sqf", dataType = "String")
    private String sqf;

    @ApiModelProperty(value = "档案室", name = "das", dataType = "String")
    private String das;
    /**
     * 1:电子
     * 2:纸质
     */
    @ApiModelProperty(value = "档案类型（电子/纸质）", name = "gdlx", dataType = "int")
    private int gdlx;

    @ApiModelProperty(value = "底稿册数", name = "dgcs", dataType = "int")
    private int dgcs;

    @ApiModelProperty(value = "报告册数", name = "bgcs", dataType = "int")
    private int bgcs;

    @ApiModelProperty(value = "归入已有档案", name = "gryyda", dataType = "String")
    private String gryyda;

    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    private String bz;

    @ApiModelProperty(value = "底稿内容", name = "dgnr", dataType = "String")
    private String dgnr;

    @ApiModelProperty(value = "是否必要", name = "sfby", dataType = "Integer")
    private Integer sfby;

    @ApiModelProperty(value = "是否纸质", name = "sfzz", dataType = "int")
    private int sfzz;

    @ApiModelProperty(value = "是否电子", name = "sfdz", dataType = "int")
    private int sfdz;

    @ApiModelProperty(value = "附件个数", name = "fjgs", dataType = "Integer")
    private Integer fjgs;

    @ApiModelProperty(value = "状态", name = "shjg", dataType = "Integer")
    private Integer shjg;

    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;

    @ApiModelProperty(value = "不/同意", name = "sfty", dataType = "Integer")
    private Integer sfty;

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String ssnf;

    @ApiModelProperty(value = "报告编号", name = "bgbh", dataType = "String")
    private String bgbh;
    /**
     * 1:未审核
     * 2：已审核
     */
    @ApiModelProperty(value = "状态", name = "zt", dataType = "int")
    private int zt;
    /**
     * 1:未借阅
     * 2：已借阅
     */
    private int jyzt;
    /**
     * 归档的最终状态
     * 当所有的归档全部完成后，那么要给一个状态9，表示最终的归档完成
     * 如果所有的归档没有都归档，那么就不给这个状态
     */
    @ApiModelProperty(value = "完成状态", name = "wczt", dataType = "int")
    private int wczt;

}
