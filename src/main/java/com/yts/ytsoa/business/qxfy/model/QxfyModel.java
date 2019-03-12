package com.yts.ytsoa.business.qxfy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "权限关系表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QxfyModel implements Serializable {
    private String uuid;
    /*账号id*/
    private String accId;
    /*权限id*/
    private String qxId;
}
