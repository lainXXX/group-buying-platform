package top.javarem.domain.message.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: rem
 * @Date: 2025/03/27/17:23
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum MessageDeliveryTypeEnumsVO {

    HTTP("HTTP"),
    MQ("MQ"),
    RPC("RPC"),
    ;

    private String code;

}
