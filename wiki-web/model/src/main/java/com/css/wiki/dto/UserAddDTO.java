package com.css.wiki.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
public class UserAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("登录名")
    @NotNull(message = "【登录名】不能为空")
    private String loginName;

    @ApiModelProperty("昵称")
    @NotNull(message = "【昵称】不能为空")
    private String name;

    @ApiModelProperty("密码")
    @NotNull(message = "【密码】不能为空")
    //@Length(min = 6, max = 20, message = "【密码】6~32位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】至少包含 数字和英文，长度6-32")
    private String password;

}
