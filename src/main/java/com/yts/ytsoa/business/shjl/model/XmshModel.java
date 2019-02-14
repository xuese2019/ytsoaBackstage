package com.yts.ytsoa.business.shjl.model;

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

@ApiModel(value = "项目审核")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmshModel implements Serializable {
    /**
     * 主键uuid
     */

    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;

    /**
     * 项目父级id
     */

    @ApiModelProperty(value = "项目父级id", name = "prentid", dataType = "String")
    private String prentid;

    /**
     * 审核意见
     */

    @ApiModelProperty(value = "审核意见", name = "shyj", dataType = "String")
    private String shyj;
    /**
     * 1:未审核
     * 2：已审核
     * 审核结果
     */

    @ApiModelProperty(value = "审核结果", name = "shjg", dataType = "int")
    private int shjg;
    /**
     * 审核时间
     */

    @ApiModelProperty(value = "审核时间", name = "shsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date shsj;
    /**
     * 审核人
     */

    @ApiModelProperty(value = "审核人", name = "shr", dataType = "String")
    private String shr;

    /**
     * 两表联查返回的结果
     */
    @ApiModelProperty(value = "姓名", name = "name", dataType = "String")
    private String name;
}
