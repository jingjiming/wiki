package com.css.wiki.service;

import com.css.wiki.entity.EbookSnapshot;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.wiki.vo.StatisticVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
public interface EbookSnapshotService extends IService<EbookSnapshot> {

    void createSnapshot();

    List<StatisticVO> getStatistic();

    /**
     * 30天数值统计
     */
    List<StatisticVO> get30Statistic();
}
