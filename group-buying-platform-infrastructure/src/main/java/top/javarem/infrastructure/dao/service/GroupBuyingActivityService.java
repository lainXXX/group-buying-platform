package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;

/**
* @author aaa
* @description 针对表【group_buying_activity(拼团活动)】的数据库操作Service
* @createDate 2025-02-25 21:55:00
*/
public interface GroupBuyingActivityService extends IService<GroupBuyingActivity> {

    /**
     * 获取有效的拼团活动
     * @param source 来源
     * @param channel 渠道
     * @return 拼团活动
     */
    GroupBuyingActivity queryValidGroupBuyingActivity(String source, String channel);

    /**
     * 获取有效的拼团活动通过活动ID
     * @param activityId 活动ID
     * @return 拼团活动
     */
    GroupBuyingActivity queryValidGroupBuyingActivityByActivityId(Long activityId);
}
