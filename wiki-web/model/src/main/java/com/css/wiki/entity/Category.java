package com.css.wiki.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@TableName("category")
@ApiModel(value = "Category对象", description = "分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("父ID")
    @TableField("parent")
    private Long parent;

    @ApiModelProperty("类别名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("排序号")
    @TableField("sort")
    private Integer sort;


}
