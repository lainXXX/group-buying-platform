package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 拼团活动
 * @TableName group_buying_activity
 */
@Data
public class GroupBuyingActivity implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 折扣ID
     */
    private String discountId;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}