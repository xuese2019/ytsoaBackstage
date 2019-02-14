package com.yts.ytsoa.business.shlc.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class shlcModel {
    /**
     * uuid主键
     */
    private String uuid;
    /**
     * 部门经理复核
     */
    private String bmjlfh;
    /**
     * 质控部复核
     */
    private String zkbfh;
    /**
     * 项目合伙人复核
     */
    private String xmhhrfh;
    /**
     * 生成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date scsj;
    /**
     * 顺序
     */
    private int sx;
    /**
     * 类型
     */
    private String lx;
}
