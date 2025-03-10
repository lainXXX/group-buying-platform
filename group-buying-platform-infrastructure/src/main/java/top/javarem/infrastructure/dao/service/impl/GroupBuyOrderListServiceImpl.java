package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.infrastructure.dao.mapper.GroupBuyOrderListMapper;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;

/**
* @author aaa
* @description 针对表【group_buy_order_list(拼团订单详情)】的数据库操作Service实现
* @createDate 2025-03-09 16:16:00
*/
@Service
public class GroupBuyOrderListServiceImpl extends ServiceImpl<GroupBuyOrderListMapper, GroupBuyOrderList>
implements GroupBuyOrderListService {

    @Override
    public GroupBuyOrderList queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {

        return lambdaQuery()
                .select(GroupBuyOrderList::getOrderId, GroupBuyOrderList::getUserId, GroupBuyOrderList::getTeamId, GroupBuyOrderList::getStatus, GroupBuyOrderList::getActivityId, GroupBuyOrderList::getBeginTime, GroupBuyOrderList::getEndTime, GroupBuyOrderList::getGoodsId, GroupBuyOrderList::getSource, GroupBuyOrderList::getChannel, GroupBuyOrderList::getOriginalPrice, GroupBuyOrderList::getDiscountPrice, GroupBuyOrderList::getOutTradeNo)
                .eq(GroupBuyOrderList::getUserId, userId)
                .eq(GroupBuyOrderList::getOutTradeNo, outTradeNo)
                .eq(GroupBuyOrderList::getStatus, 0)
                .one();

    }
}
