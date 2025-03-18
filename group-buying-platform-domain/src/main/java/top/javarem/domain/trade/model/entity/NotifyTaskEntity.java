package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/17/13:45
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyTaskEntity {


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

}
