package top.javarem.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: rem
 * @Date: 2025/03/09/16:49
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayActivityEntity {

    private String teamId;

    private Long activityId;

    private String activityName;

    private Date beginTime;

    private Date endTime;

    private Integer targetCount;

}
