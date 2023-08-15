package com.css.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.css.common.util.StringUtils;
import com.css.wiki.dto.EbookQueryDTO;
import com.css.wiki.entity.Ebook;
import com.css.wiki.mapper.EbookMapper;
import com.css.wiki.service.EbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {

    @Override
    public Page<Ebook> findByPage(EbookQueryDTO dto) {
        QueryWrapper<Ebook> qw = new QueryWrapper<Ebook>()
                .like(StringUtils.isNotBlank(dto.getName()), "name", dto.getName())
                .eq(dto.getCategoryId() != null, "category_id", dto.getCategoryId());
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        return this.page(page, qw);
    }
}
