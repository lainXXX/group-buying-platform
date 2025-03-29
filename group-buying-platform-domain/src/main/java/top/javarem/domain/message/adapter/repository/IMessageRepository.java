package top.javarem.domain.message.adapter.repository;

import top.javarem.domain.message.model.entity.MessageEntity;

import java.util.Map;

/**
 * @Author: rem
 * @Date: 2025/03/28/12:40
 * @Description: 消息仓储接口
 */
public interface IMessageRepository {

    void distributeMessage(MessageEntity messageEntity, boolean isAsync);

    Map<String, Integer> execNotifyTaskJob() throws Exception;
}
