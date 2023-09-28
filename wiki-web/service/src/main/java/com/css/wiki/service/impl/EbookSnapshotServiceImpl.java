package com.css.wiki.service.impl;

import com.css.wiki.entity.EbookSnapshot;
import com.css.wiki.mapper.EbookSnapshotMapper;
import com.css.wiki.service.EbookSnapshotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.wiki.vo.StatisticVO;
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
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot> implements EbookSnapshotService {

    @Override
    public void createSnapshot() {
        this.baseMapper.createSnapshot();
    }

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    public List<StatisticVO> getStatistic() {
        return this.baseMapper.selectStatistic();
    }

    /**
     * 30天数值统计
     */
    public List<StatisticVO> get30Statistic() {
        return this.baseMapper.select30Statistic();
    }
}
