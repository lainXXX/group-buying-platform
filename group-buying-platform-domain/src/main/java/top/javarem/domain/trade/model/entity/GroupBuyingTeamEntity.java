package top.javarem.domain.trade.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.javarem.domain.trade.model.vo.TradeOrderStatusEnumVO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: rem
 * @Date: 2025/03/13/15:40
 * @Description: 拼团组队信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingTeamEntity {

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 目标数量
     */
    private Integer targetCount;

    /**
     * 完成数量
     */
    private Integer completeCount;

    /**
     * 锁单数量
     */
    private Integer lockCount;

    /** 回调url */
    private String notifyUrl;

    /**
     * 有效开始时间
     */
    private Date validBeginTime;

    /**
     * 有效结束时间
     */
    private Date validEndTime;

    /**
     * 状态（0-拼单中、1-完成、2-失败）
     */
    private TradeOrderStatusEnumVO status;

}
