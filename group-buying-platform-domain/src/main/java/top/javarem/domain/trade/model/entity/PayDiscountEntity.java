package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/09/16:49
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayDiscountEntity {

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 折扣ID
     */
    private String discountId;

    /**
     *
     */
    private String source;

    /**
     *
     */
    private String channel;

    private BigDecimal originalPrice;

    /**
     *
     */
    private BigDecimal discountPrice;

    private BigDecimal payPrice;

    private String outTradeNo;

    private String notifyUrl;

}
