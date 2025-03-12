package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/11/20:00
 * @Description: 交易规则过滤返回实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeRuleFilterBackEntity {

    private Integer userPartakeCount;

}
