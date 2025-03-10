package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.GroupBuyingOrderMapper;
import top.javarem.infrastructure.dao.po.GroupBuyingOrder;
import top.javarem.infrastructure.dao.service.GroupBuyingOrderService;

import java.util.Date;

/**
 * @author aaa
 * @description 针对表【group_buying_order(拼团订单)】的数据库操作Service实现
 * @createDate 2025-03-09 16:16:05
 */
@Service
public class GroupBuyingOrderServiceImpl extends ServiceImpl<GroupBuyingOrderMapper, GroupBuyingOrder>
        implements GroupBuyingOrderService {

    @Override
    public GroupBuyingOrder queryGroupBuyingProgress(String teamId) {

        return lambdaQuery()
                .select(GroupBuyingOrder::getTargetCount, GroupBuyingOrder::getCompleteCount, GroupBuyingOrder::getLockCount)
                .eq(GroupBuyingOrder::getTeamId, teamId)
                .one();

    }

    @Override
    public int updateAddLockCount(String teamId) {

        return this.baseMapper.update(null, Wrappers.<GroupBuyingOrder>lambdaUpdate()
                .setSql("lock_count = lock_count + 1")
                .set(GroupBuyingOrder::getUpdateTime, new Date())
                .eq(GroupBuyingOrder::getTeamId, teamId)
                .apply("target_count > lock_count")
        );


    }
}
