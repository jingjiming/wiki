package com.css.wiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.common.beans.response.JsonResult;
import com.css.common.util.CopyUtil;
import com.css.common.util.SnowFlake;
import com.css.wiki.dto.EbookAddDTO;
import com.css.wiki.dto.EbookQueryDTO;
import com.css.wiki.entity.Ebook;
import com.css.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Autowired
    SnowFlake snowFlake;

    @GetMapping("/list")
    public JsonResult<Ebook> list(@Valid EbookQueryDTO dto) {
        Page<Ebook> list = this.ebookService.findByPage(dto);
        return JsonResult.ok().data(list);
    }

    @PostMapping("/add")
    public JsonResult add(@Valid @RequestBody EbookAddDTO dto) {
        boolean flag = false;
        Ebook ebook = CopyUtil.copy(dto, Ebook.class);
        if (ebook.getId() == null) {
            ebook.setId(this.snowFlake.nextId());
            flag = this.ebookService.save(ebook);
        } else {
            flag = this.ebookService.updateById(ebook);
        }
        if (flag) {
            return JsonResult.ok();
        }
        return JsonResult.badRequest();
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult delete(@PathVariable Long id) {
        this.ebookService.removeById(id);
        return JsonResult.ok();
    }
}
