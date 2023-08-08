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
@ApiModel(value = "Ebook查询参数DTO", description = "电子书")
public class EbookQueryDTO extends PageParams {

    @ApiModelProperty("名称")
    private String name;
}
