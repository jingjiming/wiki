package com.css.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.common.beans.enums.ResultCode;
import com.css.common.beans.response.JsonResult;
import com.css.common.exceptions.GenericBusinessException;
import com.css.common.util.CopyUtil;
import com.css.common.util.SnowFlake;
import com.css.common.util.StringUtils;
import com.css.common.util.redis.JedisUtil;
import com.css.wiki.dto.DocAddDTO;
import com.css.wiki.dto.DocQueryDTO;
import com.css.wiki.entity.Content;
import com.css.wiki.entity.Doc;
import com.css.wiki.mapper.DocMapper;
import com.css.wiki.service.ContentService;
import com.css.wiki.service.DocService;
import com.css.wiki.service.WsService;
import com.css.wiki.util.RequestContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Autowired
    JedisUtil jedisUtil;

    @Autowired
    ContentService contentService;

    @Autowired
    SnowFlake snowFlake;

    @Resource
    public WsService wsService;

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

    @Override
    @Transactional
    public JsonResult add(DocAddDTO dto) {
        boolean flag = false;
        Doc doc = CopyUtil.copy(dto, Doc.class);
        Content content = CopyUtil.copy(dto, Content.class);
        if (doc.getId() == null) {
            doc.setId(this.snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            this.save(doc);

            content.setId(doc.getId());
            this.contentService.save(content);
            flag = true;
        } else {
            this.updateById(doc);
            this.contentService.saveOrUpdate(content);
            flag = true;
        }
        if (flag) {
            return JsonResult.ok();
        }
        return JsonResult.badRequest();
    }

    @Override
    public void updateViewCount(Long id) {
        UpdateWrapper<Doc> uw = new UpdateWrapper<Doc>()
                .eq("id", id)
                .setSql("view_count = view_count + 1");
        this.update(uw);
    }

    @Override
    public void vote(Long id) {
        // 远程IP+doc.id作为key，24小时内不能重复
        String ip = RequestContext.getRemoteAddr();
        if (jedisUtil.validateRepeat("DOC_VOTE_" + id + "_" + ip, 5000)) {
            UpdateWrapper<Doc> uw = new UpdateWrapper<Doc>()
                    .eq("id", id)
                    .setSql("vote_count = vote_count + 1");
            this.update(uw);
        } else {
            throw new GenericBusinessException(ResultCode.VOTE_REPEAT);
        }

        // 推送消息
        Doc doc = this.getById(id);
        String logId = MDC.get("LOG_ID");
        wsService.sendInfo("【" + doc.getName() + "】被点赞！", logId);
        // rocketMQTemplate.convertAndSend("VOTE_TOPIC", "【" + doc.getName() + "】被点赞！");
    }

    @Override
    public void updateEbookInfo() {
        this.baseMapper.updateEbookInfo();
    }

}
