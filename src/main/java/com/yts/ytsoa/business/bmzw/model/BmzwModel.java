package com.yts.ytsoa.business.bmzw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "部门职位")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BmzwModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    @ApiModelProperty(value = "主键", name = "bmparentid", dataType = "String")
    private String bmparentid;
    @ApiModelProperty(value = "部门职务", name = "bmzw", dataType = "String")
    private String bmzw;

}
