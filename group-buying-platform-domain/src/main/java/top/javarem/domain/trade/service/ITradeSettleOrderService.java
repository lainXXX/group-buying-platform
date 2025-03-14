package top.javarem.domain.trade.service;

import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.TradePaySettleEntity;

/**
 * @Author: rem
 * @Date: 2025/03/13/15:07
 * @Description: 交易结算订单服务接口
 */
public interface ITradeSettleOrderService {

    /**
     * 结算支付单
     * @param paySuccessEntity 支付成功实体
     * @return 交易结算成功实体
     */
    TradePaySettleEntity settlePayOrder(PaySuccessEntity paySuccessEntity);

}
