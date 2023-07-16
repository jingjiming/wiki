package com.css.wiki.controller;

import com.css.common.beans.response.JsonResult;
import com.css.wiki.entity.Ebook;
import com.css.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    EbookService ebookService;

    @RequestMapping("/list")
    public JsonResult list() {
        List<Ebook> list = this.ebookService.list();
        return JsonResult.ok().data(list);
    }
}
