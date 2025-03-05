package top.javarem.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/05/9:24
 * @Description: 渠道商品活动配置关联实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScSkuActivityEntity {

    private String source;

    private String channel;

    private Long activityId;

    private String goodsId;

}
