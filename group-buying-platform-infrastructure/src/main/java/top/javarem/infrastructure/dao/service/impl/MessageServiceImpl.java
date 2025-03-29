package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.MessageMapper;
import top.javarem.infrastructure.dao.po.MessageTask;
import top.javarem.infrastructure.dao.service.MessageService;

import java.util.Date;
import java.util.List;

/**
* @author aaa
* @description 针对表【message(消息任务表)】的数据库操作Service实现
* @createDate 2025-03-27 15:21:54
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageTask>
implements MessageService {

    @Override
    public boolean isExits(String msgId) {

        MessageTask messageTask = lambdaQuery()
                .select(MessageTask::getMsgId)
                .eq(MessageTask::getMsgId, msgId)
                .one();
        return null != messageTask;

    }

    @Override
    public List<MessageTask> queryUnExecutedMessage() {

        return lambdaQuery()
                .select(MessageTask::getMsgId, MessageTask::getDeliveryType, MessageTask::getDeliveryCount, MessageTask::getRetryCount, MessageTask::getMsgJson, MessageTask::getNotifyUrl)
                .in(MessageTask::getStatus, 0, 2)
                .list();

    }

    @Override
    public int updateMessageStatusSuccess(String msgId) {

        return baseMapper.update(null, new LambdaUpdateWrapper<MessageTask>()
                .setSql("delivery_count = delivery_count + 1")
                .set(MessageTask::getStatus, 1)
                .set(MessageTask::getUpdateTime, new Date())
                .eq(MessageTask::getMsgId, msgId)
        );

    }

    @Override
    public int updateMessageStatusError(String msgId) {
        return baseMapper.update(null, new LambdaUpdateWrapper<MessageTask>()
                .setSql("delivery_count = delivery_count + 1")
                .set(MessageTask::getStatus, 3)
                .set(MessageTask::getUpdateTime, new Date())
                .eq(MessageTask::getMsgId, msgId)
        );
    }

    @Override
    public int updateMessageStatusRetry(String msgId) {

        return baseMapper.update(null, new LambdaUpdateWrapper<MessageTask>()
                .setSql("delivery_count = delivery_count + 1")
                .set(MessageTask::getStatus, 2)
                .set(MessageTask::getUpdateTime, new Date())
                .eq(MessageTask::getMsgId, msgId)
        );

    }
}
