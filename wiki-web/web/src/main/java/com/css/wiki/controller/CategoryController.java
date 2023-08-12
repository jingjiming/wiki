package com.css.wiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.common.beans.response.JsonResult;
import com.css.common.util.CopyUtil;
import com.css.common.util.SnowFlake;
import com.css.wiki.dto.CategoryAddDTO;
import com.css.wiki.dto.CategoryQueryDTO;
import com.css.wiki.entity.Category;
import com.css.wiki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 分类 前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    SnowFlake snowFlake;

    @GetMapping("/all")
    public JsonResult<Category> all(@Valid CategoryQueryDTO dto) {
        List<Category> list = this.categoryService.findAll(dto);
        return JsonResult.ok().data(list);
    }

    @GetMapping("/list")
    public JsonResult<Category> list(@Valid CategoryQueryDTO dto) {
        Page<Category> list = this.categoryService.findByPage(dto);
        return JsonResult.ok().data(list);
    }

    @PostMapping("/add")
    public JsonResult add(@Valid @RequestBody CategoryAddDTO dto) {
        boolean flag = false;
        Category category = CopyUtil.copy(dto, Category.class);
        if (category.getId() == null) {
            category.setId(this.snowFlake.nextId());
            flag = this.categoryService.save(category);
        } else {
            flag = this.categoryService.updateById(category);
        }
        if (flag) {
            return JsonResult.ok();
        }
        return JsonResult.badRequest();
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult delete(@PathVariable Long id) {
        this.categoryService.removeById(id);
        return JsonResult.ok();
    }
}
