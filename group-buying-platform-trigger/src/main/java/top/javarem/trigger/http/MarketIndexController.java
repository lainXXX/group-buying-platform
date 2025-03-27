package top.javarem.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.javarem.api.dto.GoodsMarketRequestDTO;
import top.javarem.api.dto.GoodsMarketResponseDTO;
import top.javarem.api.IMarketIndexService;
import top.javarem.api.dto.GroupBuyingTeamResponseDTO;
import top.javarem.api.response.Response;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.model.vo.TeamStatisticVO;
import top.javarem.domain.activity.service.IIndexGroupBuyingService;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.enums.ResponseCode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/20/10:42
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/gbm/index")
@CrossOrigin("*")
@DubboService(version = "1.0")
@Slf4j
public class MarketIndexController implements IMarketIndexService {

    @Resource
    private IIndexGroupBuyingService indexGroupBuyingService;
    @Resource
    private IActivityRepository activityRepository;

    @PostMapping("query_group_buy_market_config")
    @Override
    public Response<GoodsMarketResponseDTO> queryGroupBuyingMarketConfig(@RequestBody GoodsMarketRequestDTO requestDTO) {
        log.info("查询营销拼团配置开始:{} goods:{}", requestDTO.getUserId(), requestDTO.getGoodsId());
        try {
            if (StringUtils.isAnyBlank(requestDTO.getUserId(), requestDTO.getGoodsId())) {
                log.error("查询营销拼团配置 -非法参数");
                return Response.error(ResponseCode.ILLEGAL_PARAMETER);
            }
            SkuVO skuVO = activityRepository.querySkuByGoodsId(requestDTO.getGoodsId());
            if (skuVO == null) {
                log.error("查询营销拼团配置 -非法参数");
                return Response.error(ResponseCode.ILLEGAL_PARAMETER);
            }
//            1.营销优惠试算
            TrialBalanceEntity trialBalanceEntity = indexGroupBuyingService.indexMarketTrial(MarketProductEntity.builder()
                    .userId(requestDTO.getUserId())
                    .goodsId(requestDTO.getGoodsId())
                    .source(skuVO.getSource())
                    .channel(skuVO.getChannel())
                    .build());
            GroupBuyingActivityDiscountVO discountVO = trialBalanceEntity.getGroupBuyingActivityDiscountVO();
            Long activityId = discountVO.getActivityId();
//            2.查询拼团组队信息
            List<GroupBuyingTeamOrderDetailEntity> groupBuyingTeamOrderDetailList = indexGroupBuyingService.queryInProgressTeamList(activityId, requestDTO.getUserId(), 1, 2);
//            3.统计拼团数据
            TeamStatisticVO teamStatisticVO = indexGroupBuyingService.queryTeamStatisticsByActivityId(activityId, requestDTO.getGoodsId());
            GoodsMarketResponseDTO.Goods goods = GoodsMarketResponseDTO.Goods.builder()
                    .goodsId(trialBalanceEntity.getGoodsId())
                    .originalPrice(trialBalanceEntity.getOriginalPrice())
                    .discountPrice(trialBalanceEntity.getDiscountPrice())
                    .payPrice(trialBalanceEntity.getPayPrice())
                    .build();

            List<GoodsMarketResponseDTO.Team> teams = new ArrayList<>();
            if (!CollectionUtils.isEmpty(groupBuyingTeamOrderDetailList)) {
                for (GroupBuyingTeamOrderDetailEntity userGroupBuyOrderDetailEntity : groupBuyingTeamOrderDetailList) {
                    GoodsMarketResponseDTO.Team team = GoodsMarketResponseDTO.Team.builder()
                            .userId(userGroupBuyOrderDetailEntity.getUserId())
                            .teamId(userGroupBuyOrderDetailEntity.getTeamId())
                            .activityId(userGroupBuyOrderDetailEntity.getActivityId())
                            .targetCount(userGroupBuyOrderDetailEntity.getTargetCount())
                            .completeCount(userGroupBuyOrderDetailEntity.getCompleteCount())
                            .lockCount(userGroupBuyOrderDetailEntity.getLockCount())
                            .validBeginTime(userGroupBuyOrderDetailEntity.getValidBeginTime())
                            .validEndTime(userGroupBuyOrderDetailEntity.getValidEndTime())
                            .validTimeCountdown(GoodsMarketResponseDTO.Team.differenceDateTime2Str(new Date(), userGroupBuyOrderDetailEntity.getValidEndTime()))
                            .outTradeNo(userGroupBuyOrderDetailEntity.getOutTradeNo())
                            .build();
                    team.setDifferenceCount(team.getTargetCount() - team.getLockCount());
                    teams.add(team);
                }
            }

            GoodsMarketResponseDTO.TeamStatistic teamStatistic = GoodsMarketResponseDTO.TeamStatistic.builder()
                    .teamCount(teamStatisticVO.getAllTeamCount())
                    .teamCompleteCount(teamStatisticVO.getAllTeamCompleteCount())
                    .userPartakeCount(teamStatisticVO.getAllTeamUserCount())
                    .build();

            Response<GoodsMarketResponseDTO> response = Response.<GoodsMarketResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(GoodsMarketResponseDTO.builder()
                            .activityId(activityId)
                            .goods(goods)
                            .teamList(teams)
                            .teamStatistic(teamStatistic)
                            .build())
                    .build();
            log.info("查询拼团营销配置完成:{} goodsId:{} response:{}", requestDTO.getUserId(), requestDTO.getGoodsId(), GsonUtils.gson.toJson(response));

            return response;
        } catch (Exception e) {
            log.error("查询拼团营销配置失败:{} goodsId:{}", requestDTO.getUserId(), requestDTO.getGoodsId(), e);
            return Response.<GoodsMarketResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping("/query_all_team")
    @Override
    public Response<GroupBuyingTeamResponseDTO> queryGroupBuyingTeams(GoodsMarketRequestDTO requestDTO) {
        return null;
    }


}
