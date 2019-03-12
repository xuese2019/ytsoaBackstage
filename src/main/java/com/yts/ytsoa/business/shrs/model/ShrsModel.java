package com.yts.ytsoa.business.shrs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "审核人")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShrsModel implements Serializable {
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;
    private String bmjl;
    private String sz;
    /**
     * 1:归档
     * 2：借阅
     * 3：票据
     * 4：用车
     * 5：用章
     * 6：请假审核
     */
    private int shlb;
    private String accountid;
    private String shbm;
    private String shr;


}
