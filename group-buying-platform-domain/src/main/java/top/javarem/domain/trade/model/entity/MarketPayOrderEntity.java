package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.javarem.domain.trade.model.vo.TradeOrderStatusEnumVO;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:00
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketPayOrderEntity {

    private String teamId;

    private Long activityId;

    /** 预购订单ID */
    private String orderId;
    /** 折扣金额 */
    private BigDecimal discountPrice;

    private String outTradeNo;
    /** 交易订单状态枚举 */
    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;

}
