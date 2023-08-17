package com.css.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.wiki.dto.DocQueryDTO;
import com.css.wiki.entity.Doc;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
public interface DocService extends IService<Doc> {

    List<Doc> findAll(DocQueryDTO dto);

    Page<Doc> findByPage(DocQueryDTO dto);

}
