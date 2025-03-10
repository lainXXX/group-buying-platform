package top.javarem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/09/19:11
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockMarketPayOrderResponseDTO {

    private BigDecimal discountPrice;

    private String orderId;

    private Integer tradeOrderStatus;

}
