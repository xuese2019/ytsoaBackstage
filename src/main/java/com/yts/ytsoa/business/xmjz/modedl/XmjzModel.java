package com.yts.ytsoa.business.xmjz.modedl;

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

@ApiModel(value = "项目进展")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class XmjzModel implements Serializable {
    /**
     * 项目主键uuid
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 日期
     */

    @ApiModelProperty(value = "日期", name = "rq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date rq;
    /**
     * 每周进展
     */
    @ApiModelProperty(value = "每周进展", name = "mzjz", dataType = "String")
    private String mzjz;

    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
}
