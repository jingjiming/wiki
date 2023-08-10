package com.css.wiki.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
@ApiModel(value = "Ebook对象", description = "电子书")
public class EbookAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    @NotNull(message = "【名称】不能为空")
    private String name;

    @ApiModelProperty("父分类ID")
    private Long categoryParentId;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("封面")
    private String cover;

    @ApiModelProperty("文档数")
    private Integer docCount;

    @ApiModelProperty("阅读数")
    private Integer viewCount;

    @ApiModelProperty("点赞数")
    private Integer voteCount;


}
