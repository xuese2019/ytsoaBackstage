package com.yts.ytsoa.business.qxgl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表
 *
 * @author LD
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QxglModel implements Serializable {

    private String uuid;
    /**
     * 名称
     */
    private String qxmc;
    /**
     * 路径
     */
    private String qxbs;
    /**
     * 父级
     * 一级用0标识
     */
    private String qxfj;
    /**
     * 权限类型 1:菜单 2:内部按钮
     */
    private String qxlx;
    /**
     * class
     */
    private String ico;
    /**
     * 图标
     */
    private String icoi;

    private List<QxglModel> list = new ArrayList<>();

}
