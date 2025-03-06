package top.javarem.domain.activity.model.vo;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import top.javarem.types.common.constants.Constants;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: rem
 * @Date: 2025/02/27/19:32
 * @Description: 拼团活动营销配置值对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingActivityDiscountVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 来源
     */
    private String source;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 折扣信息
     */
    private GroupBuyingDiscount groupBuyingDiscount;

    /**
     * 拼团方式 【0-自动成团、 1-达成目标成团】
     */
    private Integer groupType;

    /**
     * 拼团次数限制
     */
    private Integer takeLimitCount;

    /**
     * 拼团目标
     */
    private Integer target;

    /**
     * 拼团时长【分钟】
     */
    private Integer validTime;

    /**
     * 活动状态【0-创建、 1-生效、 2-过期、 3-废弃】
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 人群标签规则标识
     */
    private String tagId;

    /**
     * 人群标签规则范围【多选】
     */
    private String tagScope;

    /**
     * 判断活动是否可见
     *
     * @return boolean
     */
    public boolean isVisible() {

        String[] split = tagScope.split(Constants.COMMA);
        if (split.length > 0 && Objects.equals(split[0], "1")) {
            return false;
        }
        return true;

    }

    /**
     * 判断活动是否能参与
     *
     * @return boolean
     */
    public boolean isEnable() {
        String[] split = tagScope.split(Constants.COMMA);
        if (split.length == 2 && Objects.equals(split[1], "2")) {
            return false;
        }
        return true;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupBuyingDiscount {

        /**
         * 折扣标题
         */
        private String discountName;

        /**
         * 折扣描述
         */
        private String discountDesc;

        /**
         * 折扣类型【0-base、 1-tag】
         */
        private DiscountTypeEnum discountType;

        /**
         * 营销优惠计划【ZJ-直减、MJ-满减、N元购】
         */
        private String marketingPlan;

        /**
         * 营销优惠表达式
         */
        private String marketingExpr;

        /**
         * 人群标签，特定优惠限定
         */
        private String tagId;

    }

}
