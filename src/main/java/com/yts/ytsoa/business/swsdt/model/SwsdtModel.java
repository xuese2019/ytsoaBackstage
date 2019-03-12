package com.yts.ytsoa.business.swsdt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.swsdttp.model.SwsdttpModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "事务所动态表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwsdtModel {
    @ApiModelProperty(value = "uuid", name = "主键", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "scsj", name = "上传时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date scsj;
    @ApiModelProperty(value = "scr", name = "上传人", dataType = "String")
    private String scr;
    @ApiModelProperty(value = "bt", name = "标题", dataType = "String")
    private String bt;
    @ApiModelProperty(value = "tpmc", name = "图片名称", dataType = "String")
    private String tpmc;
    @ApiModelProperty(value = "nr", name = "内容", dataType = "String")
    private String nr;
    @ApiModelProperty(value = "models", name = "返回多张图片", dataType = "List")
    private List<SwsdttpModel> models = new ArrayList<>();
}
