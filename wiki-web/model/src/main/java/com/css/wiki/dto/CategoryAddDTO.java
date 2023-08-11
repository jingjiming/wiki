package com.css.wiki.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Getter
@Setter
@ApiModel(value = "Category对象", description = "分类")
public class CategoryAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("父ID")
    private Long parent;

    @ApiModelProperty("类别名称")
    @NotNull(message = "【名称】不能为空")
    private String name;

    @ApiModelProperty("排序号")
    @NotNull(message = "【排序】不能为空")
    private Integer sort;


}
