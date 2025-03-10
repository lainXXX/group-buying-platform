package top.javarem.domain.trade.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:02
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingProgressVO {

    /** 目标数量 */
    private Integer targetCount;
    /** 完成数量 */
    private Integer completeCount;
    /** 锁单数量 */
    private Integer lockCount;

}
