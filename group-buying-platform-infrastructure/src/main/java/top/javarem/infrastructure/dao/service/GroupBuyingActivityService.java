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
     * @return
     */
    GroupBuyingActivity queryValidGroupBuyingActivity(String source, String channel);
}
