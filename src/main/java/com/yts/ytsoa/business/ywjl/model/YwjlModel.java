package com.yts.ytsoa.business.ywjl.model;

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

@ApiModel(value = "业务交流")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YwjlModel implements Serializable {
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;

    @ApiModelProperty(value = "发起人", name = "fqr", dataType = "String")
    private String fqr;

    @ApiModelProperty(value = "部门", name = "bm", dataType = "String")
    private String bm;

    @ApiModelProperty(value = "发起主题", name = "fqzt", dataType = "String")
    private String fqzt;

    @ApiModelProperty(value = "发起时间", name = "fqsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fqsj;

    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private Date ssnf;
}
