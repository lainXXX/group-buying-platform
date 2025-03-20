package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.GroupBuyingOrder;

import java.util.List;
import java.util.Set;

/**
* @author aaa
* @description 针对表【group_buying_order(拼团订单)】的数据库操作Service
* @createDate 2025-03-09 16:16:05
*/
public interface GroupBuyingOrderService extends IService<GroupBuyingOrder> {

    GroupBuyingOrder queryGroupBuyingProgress(String teamId);

    int updateAddLockCount(String teamId);

    GroupBuyingOrder queryGroupBuyingTeam(String teamId, Long activityId);

    int updateCompleteCount(String teamId, Long activityId);

    int updateStatusComplete(String teamId, Long activityId);

    List<GroupBuyingOrder> queryInProgressGroupBuyingTeam(Set<String> teamIdSet);

    Integer getAllCompleteCount(Set<String> teamIds);

    Integer getUserCount(Set<String> teamIds);
}
