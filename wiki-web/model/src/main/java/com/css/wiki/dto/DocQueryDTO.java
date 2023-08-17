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
@ApiModel(value = "Doc查询参数DTO", description = "文档查询参数DTO")
public class DocQueryDTO extends PageParams {

    @ApiModelProperty("名称")
    private String name;
}
