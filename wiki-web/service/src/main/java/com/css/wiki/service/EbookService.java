package com.css.wiki.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.wiki.dto.EbookQueryDTO;
import com.css.wiki.entity.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
public interface EbookService extends IService<Ebook> {

    Page<Ebook> findByPage(EbookQueryDTO dto);

}
