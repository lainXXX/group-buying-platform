package top.javarem.domain.activity.service.trial.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.service.trial.node.RootNode;
import top.javarem.types.design.framework.tree.StrategyHandler;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:37
 * @Description:默认拼团活动策略工厂
 */
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {
        /**
         * 拼团活动营销配置值对象
         */
        private GroupBuyingActivityDiscountVO groupBuyingActivityDiscountVO;

        /**
         * 商品信息
         */
        private SkuVO skuVO;

        /**
         * 折扣价格
         */
        private BigDecimal discountPrice;

        /**
         * 支付金额
         */
        private BigDecimal payPrice;

        /**
         * 可见性
         */
        private boolean visible;

        /**
         * 是否能参与
         */
        private boolean enable;

    }

}
