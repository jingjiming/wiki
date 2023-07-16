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
 * 
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Getter
@Setter
@TableName("doc")
@ApiModel(value = "Doc对象", description = "")
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("ebook_id")
    private Long ebookId;

    @TableField("parent")
    private Long parent;

    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("view_count")
    private Integer viewCount;

    @TableField("vote_count")
    private Integer voteCount;


}
