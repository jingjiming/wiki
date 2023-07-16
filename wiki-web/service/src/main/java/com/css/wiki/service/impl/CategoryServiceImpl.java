package com.css.wiki.service.impl;

import com.css.wiki.entity.Category;
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

}
