package com.css.wiki.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author jiming.jing
 * @since 2023/02/01
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "User对象", description = "用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("id")
    private Long id;

    @ApiModelProperty("登录名")
    @TableField("login_name")
    private String loginName;

    @ApiModelProperty("昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    /*@ApiModelProperty("删除标识 0-否 1-是")
    @TableField("DEL_FLAG")
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;

    @ApiModelProperty("创建人")
    @TableField("CREATOR")
    private String creator;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改人")
    @TableField("LAST_EDITOR")
    private String lastEditor;

    @ApiModelProperty("修改时间")
    @TableField(value = "LAST_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date lastTime;*/

}
