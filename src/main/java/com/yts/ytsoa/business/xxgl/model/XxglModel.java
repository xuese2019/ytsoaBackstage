package com.yts.ytsoa.business.xxgl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "消息表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XxglModel implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "uuid", dataType = "String")
    private String uuid;

    /**
     * 消息类型
     * 如果通知类型需要增加则按顺序在下方增加
     * -1:作废此消息
     * 1：委派通知
     * 2：承接通知
     * 3：申请通知
     * 4：审核通知
     * 5：指令通知
     * 6：其它通知
     * 7：全体通知
     */
    @ApiModelProperty(hidden = true)
    private int xxlx;

    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题", name = "xxbt", dataType = "String")
    @NotBlank(message = "标题不能为空")
    @Length(max = 101, message = "标题最大长度100")
    private String xxbt;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容", name = "xxnr", dataType = "String")
    @NotBlank(message = "内容不能为空")
    @Length(max = 221, message = "消息最大长度220")
    private String xxnr;

    /**
     * 消息跳转连接
     */
    @ApiModelProperty(value = "消息跳转连接", name = "tzlj", dataType = "String")
    private String tzlj;

    /**
     * 消息发送时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date xxfssj;

    /**
     * 消息查看时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date xxcksj;

    /**
     * 消息状态
     * 1:未查看
     * 2：已查看
     */
    @ApiModelProperty(value = "消息状态", name = "zt", dataType = "int")
    private int zt;

    /**
     * 消息发送人
     */
    @ApiModelProperty(hidden = true)
    private String fsr;

    /**
     * 消息接收人
     */
    @ApiModelProperty(value = "消息接收人", name = "jsr", dataType = "String")
    @NotBlank(message = "消息接收人不能为空")
    private String jsr;

    /**
     * 同一条消息标识
     * 如果有多个接收人会插入多条记录，用此标识判断多条记录是否是同一条消息
     */
    @ApiModelProperty(hidden = true)
    private String tytFlag;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
