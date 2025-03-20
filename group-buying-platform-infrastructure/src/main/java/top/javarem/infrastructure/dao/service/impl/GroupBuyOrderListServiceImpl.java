package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.GroupBuyOrderListMapper;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author aaa
 * @description 针对表【group_buy_order_list(拼团订单详情)】的数据库操作Service实现
 * @createDate 2025-03-09 16:16:00
 */
@Service
public class GroupBuyOrderListServiceImpl extends ServiceImpl<GroupBuyOrderListMapper, GroupBuyOrderList>
        implements GroupBuyOrderListService {

    @Resource
    private GroupBuyOrderListMapper mapper;

    @Override
    public GroupBuyOrderList queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {

        return lambdaQuery()
                .select(GroupBuyOrderList::getOrderId, GroupBuyOrderList::getTeamId, GroupBuyOrderList::getStatus, GroupBuyOrderList::getActivityId, GroupBuyOrderList::getDiscountPrice)
                .eq(GroupBuyOrderList::getUserId, userId)
                .eq(GroupBuyOrderList::getOutTradeNo, outTradeNo)
                .eq(GroupBuyOrderList::getStatus, 0)
                .one();

    }

    @Override
    public int queryUserActivityPartakeCount(Long activityId, String userId) {

        return mapper.queryUserActivityPartakeCount(activityId, userId);

    }

    @Override
    public int updateStatusPaySuccess(String userId, String outTradeNO, Date outTradeTime) {

        return this.baseMapper.update(null, new LambdaUpdateWrapper<GroupBuyOrderList>()
                .set(GroupBuyOrderList::getStatus, 1)
                .set(GroupBuyOrderList::getOutTradeTime, outTradeTime)
                .set(GroupBuyOrderList::getUpdateTime, new Date())
                .eq(GroupBuyOrderList::getUserId, userId)
                .eq(GroupBuyOrderList::getOutTradeNo, outTradeNO)
        );

    }

    @Override
    public List<String> queryAllOutTradeNoByTeamId(String teamId, Long activityId) {

        return mapper.queryAllOutTradeNoByTeamId(teamId, activityId);

    }
}
