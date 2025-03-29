package top.javarem.infrastructure.adapter.port.settle;

import top.javarem.infrastructure.dao.po.MessageTask;

/**
 * @Author: rem
 * @Date: 2025/03/28/21:56
 * @Description: 拼团结算策略出口
 */
public interface ISettleStrategyPort {

    /**
     * 拼团回调任务
     * @param messageTaskEntity 消息实体
     * @return success fail 字符串
     */
    String groupBuyingNotifyTask(MessageTask messageTaskEntity);

}
