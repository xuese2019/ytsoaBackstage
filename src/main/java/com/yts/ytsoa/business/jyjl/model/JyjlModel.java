package com.yts.ytsoa.business.jyjl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@ApiModel(value = "借阅记录表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JyjlModel {

    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "底稿借阅编号", name = "dgjybh", dataType = "String")
    private String dgjybh;
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
    @ApiModelProperty(value = "借阅人id", name = "jyrid", dataType = "String")
    private String jyrid;
    @ApiModelProperty(value = "归还日期", name = "ghrq", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ghrq;
    @ApiModelProperty(value = "归还人id", name = "ghrid", dataType = "String")
    private String ghrid;
}
