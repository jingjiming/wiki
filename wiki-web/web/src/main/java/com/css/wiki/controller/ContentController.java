package com.css.wiki.controller;

import com.css.common.beans.response.JsonResult;
import com.css.common.util.StringUtils;
import com.css.wiki.entity.Content;
import com.css.wiki.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @GetMapping("/get/{id}")
    public JsonResult<String> getContent(@PathVariable String id) {
        Content content = this.contentService.getById(id);
        return JsonResult.ok().data(content != null ? content.getContent() : "");
    }

}
