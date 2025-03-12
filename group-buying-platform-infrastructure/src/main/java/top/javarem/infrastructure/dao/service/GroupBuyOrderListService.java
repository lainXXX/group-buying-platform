package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;

/**
* @author aaa
* @description 针对表【group_buy_order_list(拼团订单详情)】的数据库操作Service
* @createDate 2025-03-09 16:16:00
*/
public interface GroupBuyOrderListService extends IService<GroupBuyOrderList> {

    GroupBuyOrderList queryNoPayOrderByOutTradeNo(String userId, String outTradeNo);

    int queryUserActivityPartakeCount(Long activityId, String userId);
}
