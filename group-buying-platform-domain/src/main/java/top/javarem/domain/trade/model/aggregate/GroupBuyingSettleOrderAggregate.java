package top.javarem.domain.trade.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.javarem.domain.trade.model.entity.GroupBuyingTeamEntity;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.UserEntity;

/**
 * @Author: rem
 * @Date: 2025/03/13/16:24
 * @Description: 拼团结算订单聚合
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingSettleOrderAggregate {
    private UserEntity userEntity;

    private GroupBuyingTeamEntity groupBuyingTeamEntity;

    private MarketPayOrderEntity marketPayOrderEntity;

}
