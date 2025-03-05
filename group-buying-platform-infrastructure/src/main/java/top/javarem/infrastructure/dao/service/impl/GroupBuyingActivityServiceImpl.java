package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.GroupBuyingActivityMapper;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.service.GroupBuyingActivityService;

/**
* @author aaa
* @description 针对表【group_buying_activity(拼团活动)】的数据库操作Service实现
* @createDate 2025-02-25 21:55:00
*/
@Service
public class GroupBuyingActivityServiceImpl extends ServiceImpl<GroupBuyingActivityMapper, GroupBuyingActivity>
implements GroupBuyingActivityService {

    @Override
    public GroupBuyingActivity queryValidGroupBuyingActivity(String source, String channel) {
/*        return this.lambdaQuery()
                .select(GroupBuyingActivity::getActivityId, GroupBuyingActivity::getSource, GroupBuyingActivity::getChannel, GroupBuyingActivity::getGoodsId, GroupBuyingActivity::getDiscountId, GroupBuyingActivity::getGroupType, GroupBuyingActivity::getTakeLimitCount, GroupBuyingActivity::getTarget, GroupBuyingActivity::getValidTime, GroupBuyingActivity::getStatus, GroupBuyingActivity::getBeginTime, GroupBuyingActivity::getEndTime, GroupBuyingActivity::getTagId, GroupBuyingActivity::getTagScope)
                .eq(GroupBuyingActivity::getSource, source)
                .eq(GroupBuyingActivity::getChannel, channel)
                .orderByDesc(GroupBuyingActivity::getId)
                .last("limit 1")
                .one();*/
        return null;

    }

    @Override
    public GroupBuyingActivity queryValidGroupBuyingActivityByActivityId(Long activityId) {

        return this.lambdaQuery()
                .select(GroupBuyingActivity::getActivityId, GroupBuyingActivity::getGoodsId, GroupBuyingActivity::getDiscountId, GroupBuyingActivity::getGroupType, GroupBuyingActivity::getTakeLimitCount, GroupBuyingActivity::getTarget, GroupBuyingActivity::getValidTime, GroupBuyingActivity::getStatus, GroupBuyingActivity::getBeginTime, GroupBuyingActivity::getEndTime, GroupBuyingActivity::getTagId, GroupBuyingActivity::getTagScope)
                .eq(GroupBuyingActivity::getActivityId, activityId)
                .orderByDesc(GroupBuyingActivity::getId)
                .last("limit 1")
                .one();

    }
}
