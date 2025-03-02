package top.javarem.domain.activity.service.trial.thread;

import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.vo.SkuVO;

import java.util.concurrent.Callable;

/**
 * @Author: rem
 * @Date: 2025/02/27/21:27
 * @Description: 查询商品信息任务
 */
public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {

    private final String goodsId;

    private final IActivityRepository activityRepository;

    public QuerySkuVOFromDBThreadTask(String goodsId, IActivityRepository activityRepository) {
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public SkuVO call() throws Exception {
        return activityRepository.querySkuByGoodsId(goodsId);
    }

}
