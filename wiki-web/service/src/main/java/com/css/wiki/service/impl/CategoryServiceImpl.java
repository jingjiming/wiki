package com.css.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.common.util.StringUtils;
import com.css.wiki.dto.CategoryQueryDTO;
import com.css.wiki.dto.EbookQueryDTO;
import com.css.wiki.entity.Category;
import com.css.wiki.entity.Ebook;
import com.css.wiki.mapper.CategoryMapper;
import com.css.wiki.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Page<Category> findByPage(CategoryQueryDTO dto) {
        QueryWrapper<Category> qw = new QueryWrapper<Category>();
        qw.like(StringUtils.isNotBlank(dto.getName()), "name", dto.getName());
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        return this.page(page, qw);
    }

}
