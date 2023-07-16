package com.css.wiki.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("ebook_snapshot")
@ApiModel(value = "EbookSnapshot对象", description = "")
public class EbookSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("ebook_id")
    private Long ebookId;

    @TableField("date")
    private LocalDate date;

    @TableField("view_count")
    private Integer viewCount;

    @TableField("vote_count")
    private Integer voteCount;

    @TableField("view_increase")
    private Integer viewIncrease;

    @TableField("vote_increase")
    private Integer voteIncrease;


}
