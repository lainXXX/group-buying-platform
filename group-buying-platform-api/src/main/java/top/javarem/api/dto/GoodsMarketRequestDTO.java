package top.javarem.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: rem
 * @Date: 2025/03/20/10:18
 * @Description:
 */
@Data
public class GoodsMarketRequestDTO implements Serializable {

    private String goodsId;

    private String userId;

}
