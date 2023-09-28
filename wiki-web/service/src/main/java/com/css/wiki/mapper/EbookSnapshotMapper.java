package com.css.wiki.mapper;

import com.css.wiki.entity.EbookSnapshot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.css.wiki.vo.StatisticVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@Mapper
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {

    void createSnapshot();

    List<StatisticVO> selectStatistic();

    List<StatisticVO> select30Statistic();
}
