package com.css.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.common.beans.response.JsonResult;
import com.css.wiki.dto.DocAddDTO;
import com.css.wiki.dto.DocQueryDTO;
import com.css.wiki.entity.Doc;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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

    JsonResult add(DocAddDTO dto);

    void updateViewCount(Long id);

    void vote(Long id);

    void updateEbookInfo();

}
