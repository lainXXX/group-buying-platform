package top.javarem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: rem
 * @Date: 2025/03/20/10:06
 * @Description: 营销结算支付单响应dto
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettleMarketPayOrderResponseDTO implements Serializable {

    private String userId;

    private String teamId;

    private Long activityId;

    private String outTradeNo;

}
