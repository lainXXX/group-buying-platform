package top.javarem.infrastructure.adapter.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.aggregate.GroupBuyingSettleOrderAggregate;
import top.javarem.domain.trade.model.entity.*;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;
import top.javarem.domain.trade.model.vo.TradeOrderStatusEnumVO;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.po.GroupBuyingOrder;
import top.javarem.infrastructure.dao.po.NotifyTask;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;
import top.javarem.infrastructure.dao.service.GroupBuyingActivityService;
import top.javarem.infrastructure.dao.service.GroupBuyingOrderService;
import top.javarem.infrastructure.dao.service.NotifyTaskService;
import top.javarem.types.common.Constants;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:09
 * @Description:
 */
@Repository
public class TradeRepository implements ITradeRepository {

    @Resource
    private GroupBuyingOrderService groupBuyingOrderService;
    @Resource
    private GroupBuyOrderListService groupBuyOrderListService;
    @Resource
    private GroupBuyingActivityService groupBuyingActivityService;
    @Resource
    private NotifyTaskService notifyTaskService;

    @Override
    public MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {
        GroupBuyOrderList groupBuyOrderList = groupBuyOrderListService.queryNoPayOrderByOutTradeNo(userId, outTradeNo);
        if (groupBuyOrderList == null) return null;
        return MarketPayOrderEntity.builder()
                .teamId(groupBuyOrderList.getTeamId())
                .activityId(groupBuyOrderList.getActivityId())
                .orderId(groupBuyOrderList.getOrderId())
                .discountPrice(groupBuyOrderList.getDiscountPrice())
                .outTradeNo(outTradeNo)
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.getByCode(groupBuyOrderList.getStatus()))
                .build();
    }

    @Override
    public GroupBuyingProgressVO queryGroupBuyingProgress(String teamId) {

        GroupBuyingOrder groupBuyingOrder = groupBuyingOrderService.queryGroupBuyingProgress(teamId);
        if (groupBuyingOrder == null) return null;
        return GroupBuyingProgressVO.builder()
                .completeCount(groupBuyingOrder.getCompleteCount())
                .lockCount(groupBuyingOrder.getLockCount())
                .targetCount(groupBuyingOrder.getTargetCount())
                .build();

    }

    @Transactional(timeout = 500)
    @Override
    public MarketPayOrderEntity lockMarketPayOrder(GroupBuyingOrderAggregate groupBuyingOrderAggregate) {
        PayActivityEntity payActivityEntity = groupBuyingOrderAggregate.getPayActivityEntity();
        UserEntity userEntity = groupBuyingOrderAggregate.getUserEntity();
        PayDiscountEntity payDiscountEntity = groupBuyingOrderAggregate.getPayDiscountEntity();
        String teamId = payActivityEntity.getTeamId();
        if (StringUtils.isBlank(teamId)) {

            teamId = RandomStringUtils.randomNumeric(8);
            GroupBuyingOrder groupBuyingOrder = GroupBuyingOrder.builder()
                    .teamId(teamId)
                    .activityId(payActivityEntity.getActivityId())
                    .channel(payDiscountEntity.getChannel())
                    .source(payDiscountEntity.getSource())
                    .originalPrice(payDiscountEntity.getOriginalPrice())
                    .discountPrice(payDiscountEntity.getDiscountPrice())
                    .payPrice(payDiscountEntity.getPayPrice())
                    .targetCount(payActivityEntity.getTargetCount())
                    .completeCount(0)
                    .lockCount(1)
                    .status(TradeOrderStatusEnumVO.CREATE.getCode())
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
//            写入记录
            groupBuyingOrderService.save(groupBuyingOrder);

        } else {
            int count = groupBuyingOrderService.updateAddLockCount(teamId);
            if (count != 1) {
                throw new AppException(ResponseCode.E0003);
            }
        }

        String orderId = RandomStringUtils.randomNumeric(12);
//        业务Id拼接 activityId_teamId_userId_orderId
        String bizId = payActivityEntity.getActivityId() + Constants.UNDERLINE + payActivityEntity.getTeamId() + Constants.UNDERLINE + userEntity.getUserId() + Constants.UNDERLINE + orderId;
        GroupBuyOrderList groupBuyOrderList = GroupBuyOrderList.builder()
                .userId(userEntity.getUserId())
                .teamId(teamId)
                .orderId(orderId)
                .activityId(payActivityEntity.getActivityId())
                .beginTime(payActivityEntity.getBeginTime())
                .endTime(payActivityEntity.getEndTime())
                .goodsId(payDiscountEntity.getGoodsId())
                .source(payDiscountEntity.getSource())
                .channel(payDiscountEntity.getChannel())
                .originalPrice(payDiscountEntity.getOriginalPrice())
                .discountPrice(payDiscountEntity.getDiscountPrice())
                .status(TradeOrderStatusEnumVO.CREATE.getCode())
                .outTradeNo(payDiscountEntity.getOutTradeNo())
                .bizId(bizId)
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        try {
            groupBuyOrderListService.save(groupBuyOrderList);
        } catch (DuplicateKeyException e) {
            throw new AppException(ResponseCode.INDEX_EXCEPTION);
        } catch (AppException ex) {
            throw new RuntimeException(ex);
        }
        return MarketPayOrderEntity.builder()
                .orderId(orderId)
                .discountPrice(payDiscountEntity.getDiscountPrice())
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.CREATE)
                .build();
    }

    @Override
    public GroupBuyingActivityEntity queryActivity(Long activityId) {

        GroupBuyingActivity groupBuyingActivity = groupBuyingActivityService.queryGroupBuyingActivityByActivityId(activityId);
        GroupBuyingActivityEntity groupBuyingActivityEntity = new GroupBuyingActivityEntity();
        BeanUtils.copyProperties(groupBuyingActivity, groupBuyingActivityEntity);
        return groupBuyingActivityEntity;

    }

    @Override
    public Integer queryUserActivityPartakeCount(Long activityId, String userId) {

        return groupBuyOrderListService.queryUserActivityPartakeCount(activityId, userId);

    }

    @Override
    public GroupBuyingTeamEntity queryGroupBuyingTeam(String teamId, Long activityId) {

        GroupBuyingOrder groupBuyingOrder = groupBuyingOrderService.queryGroupBuyingTeam(teamId, activityId);
        if (groupBuyingOrder == null) return null;
        return GroupBuyingTeamEntity.builder()
                .teamId(teamId)
                .activityId(activityId)
                .targetCount(groupBuyingOrder.getTargetCount())
                .completeCount(groupBuyingOrder.getCompleteCount())
                .lockCount(groupBuyingOrder.getLockCount())
                .status(TradeOrderStatusEnumVO.getByCode(groupBuyingOrder.getStatus()))
                .build();

    }

    @Transactional(timeout = 500)
    @Override
    public void updateTradeOrder(GroupBuyingSettleOrderAggregate groupBuyingSettleOrderAggregate) {
        String userId = groupBuyingSettleOrderAggregate.getUserEntity().getUserId();
        MarketPayOrderEntity tradePayOrderEntity = groupBuyingSettleOrderAggregate.getMarketPayOrderEntity();
        GroupBuyingTeamEntity groupBuyingTeamEntity = groupBuyingSettleOrderAggregate.getGroupBuyingTeamEntity();
//            1.更新用户订单状态
        int updateStatusPatSuccess = groupBuyOrderListService.updateStatusPaySuccess(userId, tradePayOrderEntity.getOutTradeNo());
        if (updateStatusPatSuccess != 1) {
            throw new AppException(ResponseCode.UPDATE_ZERO);
        }
//            2.更新组队拼团完成量
        int updateComplete = groupBuyingOrderService.updateCompleteCount(groupBuyingTeamEntity.getTeamId(), groupBuyingTeamEntity.getActivityId());
        if (updateComplete != 1) {
            throw new AppException(ResponseCode.UPDATE_ZERO);
        }
//            3.查询组队拼团完成量
        GroupBuyingOrder groupBuyingOrderRes = groupBuyingOrderService.queryGroupBuyingProgress(groupBuyingTeamEntity.getTeamId());
        int remainingCount = groupBuyingOrderRes.getTargetCount() - groupBuyingOrderRes.getCompleteCount();
        if (remainingCount == 0) {

            int updateStatusComplete = groupBuyingOrderService.updateStatusComplete(groupBuyingTeamEntity.getTeamId(), groupBuyingTeamEntity.getActivityId());
            if (updateStatusComplete != 1) {
                throw new AppException(ResponseCode.UPDATE_ZERO);
            }
//            查询组队全部外部单号
            List<String> outTradeNoList = groupBuyOrderListService.queryAllOutTradeNoByTeamId(groupBuyingTeamEntity.getTeamId(), groupBuyingTeamEntity.getActivityId());
            NotifyTask notifyTask = new NotifyTask();
            notifyTask.setTeamId(groupBuyingTeamEntity.getTeamId());
            notifyTask.setActivityId(groupBuyingTeamEntity.getActivityId());
            notifyTask.setNotifyUrl("暂无");
            notifyTask.setNotifyCount(0);
            notifyTask.setNotifyStatus(0);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("teamId", groupBuyingTeamEntity.getTeamId());
            hashMap.put("outTradeNo", outTradeNoList);
            notifyTask.setParameterJson(GsonUtils.gson.toJson(hashMap));
            notifyTaskService.save(notifyTask);
        }


    }

}


