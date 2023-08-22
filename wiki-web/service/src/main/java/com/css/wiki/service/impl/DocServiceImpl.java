package com.css.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.common.util.StringUtils;
import com.css.wiki.dto.DocQueryDTO;
import com.css.wiki.entity.Doc;
import com.css.wiki.mapper.DocMapper;
import com.css.wiki.service.DocService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    @Override
    public List<Doc> findAll(DocQueryDTO dto) {
        QueryWrapper<Doc> qw = new QueryWrapper<Doc>()
                .eq("ebook_id", dto.getEbookId())
                .like(StringUtils.isNotBlank(dto.getName()), "name", dto.getName());
        return this.list(qw);
    }

    @Override
    public Page<Doc> findByPage(DocQueryDTO dto) {
        QueryWrapper<Doc> qw = new QueryWrapper<Doc>();
        qw.like(StringUtils.isNotBlank(dto.getName()), "name", dto.getName());
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        return this.page(page, qw);
    }

}
