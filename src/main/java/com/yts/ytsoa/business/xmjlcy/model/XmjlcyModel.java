package com.yts.ytsoa.business.xmjlcy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "项目内部交流")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmjlcyModel implements Serializable {
    /**
     * 项目主键uuid
     */
    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", name = "xmid", dataType = "String")
    private String xmid;
    /**
     * 成员name
     */
    @ApiModelProperty(value = "成员name", name = "name", dataType = "String")
    private String name;
}
