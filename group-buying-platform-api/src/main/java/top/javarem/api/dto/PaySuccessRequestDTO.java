package top.javarem.api.dto;

import lombok.Data;

/**
 * @Author: rem
 * @Date: 2025/03/18/9:09
 * @Description:
 */
@Data
public class PaySuccessRequestDTO {

    private String userId;

    private String outTradeNo;

    private String goodId;

    private String channel;

    private String source;

}
