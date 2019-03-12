package com.yts.ytsoa.business.bm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "部门")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BmModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "部门名称", name = "bmmc", dataType = "String")
    private String bmmc;
    @ApiModelProperty(value = "部门list", name = "bmzwList", dataType = "List")
    private List<BmzwModel> bmzwList = new ArrayList<>();

}
