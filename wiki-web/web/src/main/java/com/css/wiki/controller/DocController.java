package com.css.wiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.common.beans.response.JsonResult;
import com.css.common.util.CopyUtil;
import com.css.common.util.SnowFlake;
import com.css.wiki.dto.DocAddDTO;
import com.css.wiki.dto.DocQueryDTO;
import com.css.wiki.entity.Content;
import com.css.wiki.entity.Doc;
import com.css.wiki.service.ContentService;
import com.css.wiki.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 文档 前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    DocService docService;

    @GetMapping("/all/{ebookId}")
    public JsonResult<Doc> all(@PathVariable Long ebookId, @Valid DocQueryDTO dto) {
        dto.setEbookId(ebookId);
        List<Doc> list = this.docService.findAll(dto);
        return JsonResult.ok().data(list);
    }

    @GetMapping("/list")
    public JsonResult<Doc> list(@Valid DocQueryDTO dto) {
        Page<Doc> list = this.docService.findByPage(dto);
        return JsonResult.ok().data(list);
    }

    @PostMapping("/add")
    public JsonResult add(@Valid @RequestBody DocAddDTO dto) {
        return this.docService.add(dto);
    }

    @DeleteMapping("/delete/{ids}")
    public JsonResult delete(@PathVariable String ids) {
        //this.docService.removeById(id);
        List<String> list = Arrays.asList(ids.split(","));
        this.docService.removeByIds(list);
        return JsonResult.ok();
    }

    @GetMapping("/vote/{id}")
    public JsonResult vote(@PathVariable Long id) {
        this.docService.vote(id);
        return JsonResult.ok();
    }
}
