package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/13/15:15
 * @Description: 支付成功返回实体 【包含用户、活动以及一些商品必要信息】
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaySuccessEntity {

    private String userId;

    private String outTradeNO;

    private String goodId;

    private String channel;

    private String source;

}
