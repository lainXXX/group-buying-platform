package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.MessageTask;

import java.util.List;

/**
* @author aaa
* @description 针对表【message(消息任务表)】的数据库操作Service
* @createDate 2025-03-27 15:21:54
*/
public interface MessageService extends IService<MessageTask> {

    boolean isExits(String msgId);

    /**
     * 查询未执行的消息任务
     * @return message
     */
    List<MessageTask> queryUnExecutedMessage();

    int updateMessageStatusSuccess(String msgId);

    int updateMessageStatusError(String msgId);

    int updateMessageStatusRetry(String msgId);
}
