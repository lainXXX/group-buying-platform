package top.javarem.domain.activity.service;

import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:56
 * @Description: 首页营销服务接口
 */
public interface IIndexGroupBuyingService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProduct) throws Exception;

}
