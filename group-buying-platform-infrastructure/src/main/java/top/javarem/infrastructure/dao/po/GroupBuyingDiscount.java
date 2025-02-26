package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 拼团折扣表
 * @TableName group_buying_discount
 */
@Data
public class GroupBuyingDiscount implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 折扣ID
     */
    private Integer discountId;

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
    private Integer discountType;

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