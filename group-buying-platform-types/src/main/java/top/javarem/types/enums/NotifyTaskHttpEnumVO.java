package top.javarem.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: rem
 * @Date: 2025/03/17/10:56
 * @Description:
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum NotifyTaskHttpEnumVO {

    SUCCESS("success", "成功"),
    ERROR("error", "失败"),
    NULL(null, "空执行"),
    ;

    private String code;
    private String info;

}
