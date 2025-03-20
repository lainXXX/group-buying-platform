package top.javarem.domain.trade.service;

import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PayActivityEntity;
import top.javarem.domain.trade.model.entity.PayDiscountEntity;
import top.javarem.domain.trade.model.entity.UserEntity;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;

/**
 * @Author: rem
 * @Date: 2025/03/09/16:22
 * @Description: 交易订单服务接口
 */
public interface ITradeLockOrderService {

    /**
     * 查询未支付订单
     * @param userId 用户
     * @param outTradeNo 外部单号
     * @return 营销支付单
     */
    MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo);

    /**
     * 查询拼团过程
     * @param teamId 组团Id
     * @return GroupBuyingProgressVO
     */
    GroupBuyingProgressVO queryGroupBuyingProgress(String teamId);

    /**
     * 锁单
     * @param userEntity 用户
     * @param payActivityEntity 活动
     * @param payDiscountEntity 折扣
     * @return MarketPayOrderEntity
     */
    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception;

}
