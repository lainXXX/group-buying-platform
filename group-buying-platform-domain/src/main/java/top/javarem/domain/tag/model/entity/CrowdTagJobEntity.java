package top.javarem.domain.tag.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/03/14:55
 * @Description: 人群标签任务实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrowdTagJobEntity {

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 批次ID
     */
    private String batchId;

    /**
     * 标签类型（参与量、消费金额）
     */
    private Integer tagType;

    /**
     * 标签规则（限定类型 N次）
     */
    private String tagRule;

}
