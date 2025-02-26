package top.javarem.domain.activity.service.trial;

import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:56
 * @Description:
 */
public interface IIndexGroupBuyingService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProduct) throws Exception;

}
