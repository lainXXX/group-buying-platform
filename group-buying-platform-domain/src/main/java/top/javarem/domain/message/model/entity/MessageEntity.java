package top.javarem.domain.message.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.javarem.domain.message.model.vo.MessageDeliveryTypeEnumsVO;
import top.javarem.domain.message.model.vo.MessageStatusEnumsVO;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity implements Serializable {

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 发送方式【MQ、HTTP、RPC】
     */
    private MessageDeliveryTypeEnumsVO deliveryType;

    /**
     * 发送次数
     */
    private Integer deliveryCount;

    /**
     * 可重试次数
     */
    private Integer retryCount;

    /**
     * 重试时间间隔
     */
    private Integer retryInterval;

    /**
     * json格式内容
     */
    private String msgJson;

    /**
     * http回调url
     */
    private String notifyUrl;

    /**
     * 状态【0-创建 1-成功 2-重试】
     */
    private MessageStatusEnumsVO status;

}