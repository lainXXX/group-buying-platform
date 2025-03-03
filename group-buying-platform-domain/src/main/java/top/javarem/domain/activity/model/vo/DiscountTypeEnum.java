package top.javarem.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: rem
 * @Date: 2025/03/02/10:55
 * @Description: 折扣类型枚举值
 */
@Getter
@AllArgsConstructor
public enum DiscountTypeEnum {

    BASE(0, "基础折扣"),
    TAG(1, "标签折扣"),
    ;

    private Integer code;

    private String info;

    /**
     * 通过折扣类型的code来获取枚举值
     * @param code 代码 0 1
     * @return
     */
    public static DiscountTypeEnum get(Integer code) {

        switch (code) {

            case 0: return BASE;

            case 1: return TAG;

            default: throw new RuntimeException("error code");

        }

    }

}
