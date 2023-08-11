package com.css.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.wiki.dto.CategoryQueryDTO;
import com.css.wiki.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.wiki.entity.Ebook;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
public interface CategoryService extends IService<Category> {

    Page<Category> findByPage(CategoryQueryDTO dto);

}
