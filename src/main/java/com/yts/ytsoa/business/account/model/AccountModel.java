package com.yts.ytsoa.business.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.account.model.interfaces.AccountAdd;
import com.yts.ytsoa.business.account.model.interfaces.AccountGrzl;
import com.yts.ytsoa.business.account.model.interfaces.AccountLogin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LD
 * <p>
 * lombok常用注解整理
 * @Data 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * @Setter ：注解在属性上；为属性提供 setting 方法
 * @Setter ：注解在属性上；为属性提供 getting 方法
 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * @NoArgsConstructor ：注解在类上；为类提供一个无参的构造方法
 * @AllArgsConstructor ：注解在类上；为类提供一个全参的构造方法
 * @Cleanup : 可以关闭流
 * @Builder ： 被注解的类加个构造者模式
 * @Synchronized ： 加个同步锁
 * @SneakyThrows : 等同于try/catch 捕获异常
 * @NonNull : 如果给参数加个这个注解 参数为null会抛出空指针异常
 * @Value : 注解和@Data类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法。
 */
@ApiModel(value = "员工信息表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "员工姓名", name = "name", dataType = "String")
    @NotBlank(message = "姓名不能为空", groups = {AccountGrzl.class})
    @Length(max = 31, message = "姓名最大长度为30位", groups = {AccountGrzl.class})
    private String name;
    /**
     * 性别
     * 1:女
     * 2：男
     */
    @ApiModelProperty(value = "员工性别", name = "xb", dataType = "String")
    @NotNull(message = "性别不能为空", groups = {AccountGrzl.class})
    private Integer xb;
    /**
     * 首字母
     */
    @ApiModelProperty(value = "员工首字母", name = "szm", dataType = "String")
    private String szm;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", name = "sfzh", dataType = "String")
    @NotBlank(message = "身份证号不能为空", groups = {AccountGrzl.class})
    @Length(min = 18, max = 19, message = "身份证号长度为18位", groups = {AccountGrzl.class})
    private String sfzh;
    /**
     * 工作单位
     */
    @ApiModelProperty(value = "工作单位", name = "gzdw", dataType = "String")
    @NotBlank(message = "工作单位不能为空", groups = {AccountAdd.class})
    @Length(max = 151, message = "工作单位最大长度为150位", groups = {AccountAdd.class})
    private String gzdw;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "account", dataType = "String")
    @NotBlank(message = "账户不能为空", groups = {AccountAdd.class, AccountLogin.class})
    @Length(min = 6, max = 16, message = "账户长度为6-16位", groups = {AccountAdd.class, AccountLogin.class})
    private String account;
    @ApiModelProperty(value = "密码", name = "password", dataType = "String")
    @NotBlank(message = "密码不能为空", groups = {AccountAdd.class, AccountLogin.class})
    @Length(min = 6, max = 16, message = "密码长度为6-16位", groups = {AccountAdd.class, AccountLogin.class})
    private String password;
    /**
     * 所属部门职位
     */
    @ApiModelProperty(value = "所属部门职位", name = "bm", dataType = "String")
    private String bm;
    /**
     * 职位类型
     */
    @ApiModelProperty(value = "职位类型", name = "zwlx", dataType = "String")
    private String zwlx;
    /**
     * 员工状态
     * 1:在职
     * 2：休假
     * 3：离职
     */
    @ApiModelProperty(value = "员工状态", name = "ygzt", dataType = "int")
    @Min(value = 1, message = "员工状态最小取值1", groups = {AccountAdd.class})
    @Max(value = 3, message = "员工状态最大取值3", groups = {AccountAdd.class})
    private int ygzt;
    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期", name = "rzrq", dataType = "Date")
    @NotNull(message = "入职日期不能为空", groups = {AccountAdd.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    @DateTimeFormat(pattern = “yyyy-MM-dd HH:mm:ss”)
    private Date rzrq;
    /**
     * 转正日期
     */
    @ApiModelProperty(value = "转正日期", name = "zzrq", dataType = "Date")
    @NotNull(message = "转正日期不能为空", groups = {AccountAdd.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zzrq;
    /**
     * 毕业时间
     */
    @ApiModelProperty(value = "毕业时间", name = "bysj", dataType = "Date")
    @NotNull(message = "毕业时间不能为空", groups = {AccountGrzl.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bysj;
    /**
     * 毕业院校
     */
    @ApiModelProperty(value = "毕业院校", name = "byyx", dataType = "String")
    @NotBlank(message = "毕业院校不能为空", groups = {AccountGrzl.class})
    @Length(max = 150, message = "毕业院校长度为150位", groups = {AccountGrzl.class})
    private String byyx;
    /**
     * 职称等级
     */
    @ApiModelProperty(value = "职称等级", name = "zcdj", dataType = "String")
    private String zcdj;
    /**
     * 户口所在地
     */
    @ApiModelProperty(value = "户口所在地", name = "hkszd", dataType = "String")
    @NotBlank(message = "户口所在地不能为空", groups = {AccountGrzl.class})
    @Length(max = 151, message = "户口所在地长度为151位", groups = {AccountGrzl.class})
    private String hkszd;
    /**
     * 现住址
     */
    @ApiModelProperty(value = "现住址", name = "xzz", dataType = "String")
    @NotBlank(message = "现住址不能为空", groups = {AccountGrzl.class})
    @Length(max = 151, message = "现住址长度为151位", groups = {AccountGrzl.class})
    private String xzz;
    /**
     * 紧急联系人
     */
    @ApiModelProperty(value = "紧急联系人", name = "jjlxr", dataType = "String")
    @NotBlank(message = "紧急联系人不能为空", groups = {AccountGrzl.class})
    @Length(max = 150, message = "紧急联系人长度为150位", groups = {AccountGrzl.class})
    private String jjlxr;
    /**
     * 紧急联系电话
     */
    @ApiModelProperty(value = "紧急联系电话", name = "jjlxdh", dataType = "String")
    @NotBlank(message = "紧急联系电话不能为空", groups = {AccountGrzl.class})
    @Length(min = 7, max = 11, message = "紧急联系电话长度为7-11位", groups = {AccountGrzl.class})
    private String jjlxdh;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", name = "dh", dataType = "String")
    @NotBlank(message = "电话不能为空", groups = {AccountGrzl.class})
    @Length(min = 7, max = 11, message = "电话长度为7-11位", groups = {AccountGrzl.class})
    private String dh;
    /**
     * 政治面貌
     * 1：党员
     * 2：群众
     * 3：其它
     */
    @ApiModelProperty(value = "政治面貌", name = "zzmm", dataType = "int")
    @Max(value = 3, message = "政治面貌最大值为3", groups = {AccountGrzl.class})
    private int zzmm;
    /**
     * 职级系数
     */
    @ApiModelProperty(value = "职级系数", name = "zjxs", dataType = "String")
    @NotNull(message = "职级系数不能为空", groups = {AccountAdd.class})
    @Max(value = 250, message = "职级系数最大值为250", groups = {AccountAdd.class})
    private String zjxs;

    /**
     * 账户类型
     * 0:普通
     * 1：管理员
     */
    @ApiModelProperty(value = "账户类型", name = "lx", dataType = "int")
    private int lx = 1;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "bz", dataType = "String")
    @Length(max = 251, message = "备注最大长度为250位", groups = {AccountAdd.class})
    private String bz;
    /**
     * 是否是首次登陆
     * 0:首次
     * 1：不是首次
     */
    @ApiModelProperty(value = "是否是首次登陆", name = "scdl", dataType = "int")
    private int scdl = 0;
    /**
     * 是否完善信息
     * 0:无
     * 1：是
     */
    @ApiModelProperty(value = "是否完善信息", name = "wsxx", dataType = "int")
    private int wsxx = 0;
    @ApiModelProperty(value = "职级", name = "zj", dataType = "double")
    private double zj;
}
