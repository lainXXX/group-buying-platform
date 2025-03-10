package top.javarem.domain.trade.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: rem
 * @Date: 2025/03/09/16:56
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum TradeOrderStatusEnumVO {

    CREATE(0, "创建"),
    COMPLETE(1, "完成"),
    CLOSE(2, "关闭"),
    ;

    private Integer code;

    private String info;

    public static TradeOrderStatusEnumVO getByCode(Integer code) {

        switch (code) {
            case 0: return TradeOrderStatusEnumVO.CREATE;

            case 1: return TradeOrderStatusEnumVO.COMPLETE;

            case 2: return TradeOrderStatusEnumVO.CLOSE;

            default: return null;
        }

    }

}
