package top.javarem.domain.trade.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.javarem.domain.trade.model.entity.PayActivityEntity;
import top.javarem.domain.trade.model.entity.PayDiscountEntity;
import top.javarem.domain.trade.model.entity.UserEntity;

/**
 * @Author: rem
 * @Date: 2025/03/09/16:59
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingOrderAggregate {

    /** 用户实体对象 */
    private UserEntity userEntity;
    /** 支付活动实体对象 */
    private PayActivityEntity payActivityEntity;
    /** 支付优惠实体对象 */
    private PayDiscountEntity payDiscountEntity;
    /** 用户参与数量 */
    private Integer userPartakeCount;

}
