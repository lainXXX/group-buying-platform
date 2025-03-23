package top.javarem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rem
 * @Date: 2025/03/20/10:19
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsMarketResponseDTO implements Serializable {

    private Goods goods;

    private List<Team> teams;

    private TeamStatistic teamStatistic;

    /**
     * 商品信息
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Goods implements Serializable {

        private String goodsId;

        private BigDecimal originPrice;

        private BigDecimal discountPrice;

        private BigDecimal payPrice;

    }

    /**
     * 组队信息
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Team implements Serializable {
        // 用户ID
        private String userId;
        // 拼单组队ID
        private String teamId;
        // 活动ID
        private Long activityId;
        // 目标数量
        private Integer targetCount;
        // 完成数量
        private Integer completeCount;
        // 锁单数量
        private Integer lockCount;
        // 拼团开始时间 - 参与拼团时间
        private Date validBeginTime;
        // 拼团结束时间 - 拼团有效时长
        private Date validEndTime;
        // 倒计时(字符串) validEndTime - validStartTime
        private String validTimeCountdown;
        /**
         * 外部交易单号-确保外部调用唯一幂等
         */
        private String outTradeNo;

        public static String differenceDateTime2Str(Date validStartTime, Date validEndTime) {
            if (validStartTime == null || validEndTime == null) {
                return "无效的时间";
            }

            long diffInMilliseconds = validEndTime.getTime() - validStartTime.getTime();

            if (diffInMilliseconds < 0) {
                return "已结束";
            }

            long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMilliseconds) % 60;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds) % 60;
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMilliseconds) % 24;
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TeamStatistic implements Serializable {

        /**
         * 拼团队伍总数
         */
        private Integer teamCount;

        /**
         * 拼团完成队伍总数
         */
        private Integer teamCompleteCount;

        /**
         * 用户参与拼团总数
         */
        private Integer userPartakeCount;

    }

}
