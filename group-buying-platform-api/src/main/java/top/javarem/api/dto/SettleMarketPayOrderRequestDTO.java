package top.javarem.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: rem
 * @Date: 2025/03/18/9:09
 * @Description: 营销结算支付单请求dto
 */
@Data
public class SettleMarketPayOrderRequestDTO {

    private String userId;

    private String outTradeNo;

    private String channel;

    private String source;

    private Date outTradeTime;

}
