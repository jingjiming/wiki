package com.css.wiki.service.impl;

import com.css.wiki.entity.Content;
import com.css.wiki.mapper.ContentMapper;
import com.css.wiki.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
