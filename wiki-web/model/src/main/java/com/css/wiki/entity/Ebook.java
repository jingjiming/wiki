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
 * 电子书
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Getter
@Setter
@TableName("ebook")
@ApiModel(value = "Ebook对象", description = "电子书")
public class Ebook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("父分类ID")
    @TableField("category_parent_id")
    private Long categoryParentId;

    @ApiModelProperty("分类ID")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("封面")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("文档数")
    @TableField("doc_count")
    private Integer docCount;

    @ApiModelProperty("阅读数")
    @TableField("view_count")
    private Integer viewCount;

    @ApiModelProperty("点赞数")
    @TableField("vote_count")
    private Integer voteCount;


}
