package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 拼团订单
 * @TableName group_buying_order
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingOrder implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 来源
     */
    private String source;

    /**
     * 原始价格
     */
    private BigDecimal originalPrice;

    /**
     * 折扣价格
     */
    private BigDecimal discountPrice;

    /**
     * 支付价格
     */
    private BigDecimal payPrice;

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

    /**
     * 状态（0-拼单中、1-完成、2-失败）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}