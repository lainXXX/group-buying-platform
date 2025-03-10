package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 渠道商品活动配置关联表
 * @TableName sc_sku_activity
 */
@Data
public class ScSkuActivity implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String goodsId;

    /**
     * 
     */
    private Long activityId;

    /**
     * 来源
     */
    private String source;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}