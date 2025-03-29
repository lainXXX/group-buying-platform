package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息任务表
 * @TableName message
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTask implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

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
    private String deliveryType;

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
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}