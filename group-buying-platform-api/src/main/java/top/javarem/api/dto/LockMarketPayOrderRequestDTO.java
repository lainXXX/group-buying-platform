package top.javarem.api.dto;

import lombok.Data;


/**
 * @Author: rem
 * @Date: 2025/03/09/19:07
 * @Description: 锁定营销支付单请求dto
 */
@Data
public class LockMarketPayOrderRequestDTO {

    private String userId;

    private String outTradeNo;

    private String teamId;

    private Long activityId;

    private String goodsId;

    private String channel;

    private String source;

    private String notifyUrl;

}
