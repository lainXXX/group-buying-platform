package top.javarem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/09/19:11
 * @Description: 锁定营销支付单响应dto
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockMarketPayOrderResponseDTO implements Serializable {

    private String orderId;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    private BigDecimal payPrice;

    private Integer tradeOrderStatus;

}
