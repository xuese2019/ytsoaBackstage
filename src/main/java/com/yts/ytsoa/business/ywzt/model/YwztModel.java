package com.yts.ytsoa.business.ywzt.model;

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


@ApiModel(value = "交流主题")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YwztModel implements Serializable {
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;

    @ApiModelProperty(value = "发起主题和业务交流中主题相对应", name = "fqzt_d", dataType = "String")

    private String fqzt_d;
    @ApiModelProperty(value = "回复人", name = "hfr", dataType = "String")
    private String hfr;
    @ApiModelProperty(value = "回复时间", name = "hfsj", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date hfsj;
    @ApiModelProperty(value = "回复内容", name = "hfnr", dataType = "String")
    private String hfnr;
    @ApiModelProperty(value = "所属年份", name = "ssnf", dataType = "String")
    private String ssnf;
}
