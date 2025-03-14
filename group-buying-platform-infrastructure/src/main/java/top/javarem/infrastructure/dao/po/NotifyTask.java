package top.javarem.infrastructure.dao.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务回调表
 * @TableName notify_task
 */

@Data
public class NotifyTask implements Serializable {
    /**
     * 自增ID
     */

    private Integer id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 拼团组队ID
     */
    private String teamId;

    /**
     * 回调接口
     */
    private String notifyUrl;

    /**
     * 回调次数
     */
    private Integer notifyCount;

    /**
     * 回调状态
     */
    private Integer notifyStatus;

    /**
     * 参数对象
     */
    private String parameterJson;

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