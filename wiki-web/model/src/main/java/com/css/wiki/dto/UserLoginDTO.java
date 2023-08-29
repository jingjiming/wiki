package com.css.wiki.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "用户")
public class UserLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录名")
    @NotEmpty(message = "【登录名】不能为空")
    private String loginName;

    @ApiModelProperty("密码")
    @NotEmpty(message = "【密码】不能为空")
    //@Length(min = 6, max = 20, message = "【密码】6~32位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】规则不正确")
    private String password;

}
