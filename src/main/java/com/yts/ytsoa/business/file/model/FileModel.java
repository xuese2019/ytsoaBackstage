package com.yts.ytsoa.business.file.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileModel implements Serializable {
    /**
     * 主键uuid
     */
    private String uuid;
    /**
     * 上传时间
     */
    private Date scsj;
    /**
     * 文件名称
     */
    private String wjmc;
    /**
     * 文件原名称
     */
    private String wjymc;
    /**
     * 文件类型
     */
    private int wjlx;
    /**
     * 文件大小/字节
     */
    private String wjdx;
}
