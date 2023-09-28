package com.css.wiki.controller;

import com.css.common.beans.response.JsonResult;
import com.css.wiki.service.EbookSnapshotService;
import com.css.wiki.vo.StatisticVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/07/17
 */
@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public JsonResult getStatistic() {
        List<StatisticVO> list = ebookSnapshotService.getStatistic();
        return JsonResult.ok(list);
    }

    @GetMapping("/get-30-statistic")
    public JsonResult get30Statistic() {
        List<StatisticVO> list = ebookSnapshotService.get30Statistic();
        return JsonResult.ok(list);
    }
}
