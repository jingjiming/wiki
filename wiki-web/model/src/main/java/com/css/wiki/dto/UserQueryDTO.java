package com.css.wiki.dto;

import com.css.common.beans.request.PageParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiming.jing
 * @date 2023/8/8 01:24
 */
@Data
@ApiModel(value = "User查询参数DTO", description = "User查询参数DTO")
public class UserQueryDTO extends PageParams {

    @ApiModelProperty("登录名")
    private String loginName;

}
