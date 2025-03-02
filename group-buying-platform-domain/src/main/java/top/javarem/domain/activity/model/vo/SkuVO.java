package top.javarem.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/02/27/20:33
 * @Description: 商品信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuVO {

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal originalPrice;

}
