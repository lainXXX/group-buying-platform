package top.javarem.domain.trade.adapter.port;

import top.javarem.domain.trade.model.entity.NotifyTaskEntity;

/**
 * @Author: rem
 * @Date: 2025/03/17/13:44
 * @Description: 交易接口
 */
public interface ITradePort {

    String groupBuyingNotifyTask(NotifyTaskEntity notifyTaskEntity);

}
