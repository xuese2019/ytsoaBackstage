package com.yts.ytsoa.business.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author LD
 */
@ApiModel(value = "员工信息表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PwdModel implements Serializable {

    @ApiModelProperty(value = "员工主键", name = "uuid", dataType = "String")
    private String uuid;

    /**
     * 密码
     */
    @ApiModelProperty(value = "原密码", name = "passwordBack", dataType = "String")
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    private String passwordBack;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码1", name = "password", dataType = "String")
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    private String password;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码2", name = "password2", dataType = "String")
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    private String password2;

    private boolean passwordOk = false;

    public boolean isPasswordOk() {
        if (password != null && password2 != null) {
            return password.equals(password2);
        } else {
            return false;
        }
    }
}
