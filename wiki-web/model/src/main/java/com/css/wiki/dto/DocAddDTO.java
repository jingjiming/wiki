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
 * 
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Getter
@Setter
@ApiModel(value = "Doc对象", description = "")
public class DocAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("电子书ID")
    @NotNull(message = "【电子书】不能为空")
    private Long ebookId;

    @ApiModelProperty("父ID")
    @NotNull(message = "【父文档】不能为空")
    private Long parent;

    @ApiModelProperty("名称")
    @NotNull(message = "【名称】不能为空")
    private String name;

    @ApiModelProperty("顺序")
    @NotNull(message = "【顺序】不能为空")
    private Integer sort;

    @ApiModelProperty("阅读数")
    private Integer viewCount;

    @ApiModelProperty("点赞数")
    private Integer voteCount;

    @ApiModelProperty("内容")
    @NotNull(message = "【内容】不能为空")
    private String content;


}
