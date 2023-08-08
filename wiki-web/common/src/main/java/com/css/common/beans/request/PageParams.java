package com.css.common.beans.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by jiming.jing on 2019/12/17.
 */
@Getter
@Setter
@ApiModel(value = "PageParams", description = "分页参数")
public class PageParams implements Serializable {

    /**
     * 当前页
     */
    @ApiModelProperty(name = "pageNum", value = "当前页数")
    @NotNull(message = "【页码】不能为空")
    @TableField(exist = false)
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    @ApiModelProperty(name = "pageSize", value = "每页显示纪录数")
    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 100, message = "【每页条数】不能超过100")
    @TableField(exist = false)
    private Integer pageSize = 15;

    /**
     * 版本号
     */
    @TableField(exist = false)
    private String version;

}
