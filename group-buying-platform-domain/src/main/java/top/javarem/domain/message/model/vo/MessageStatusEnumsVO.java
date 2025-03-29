package top.javarem.domain.message.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: rem
 * @Date: 2025/03/27/17:21
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum MessageStatusEnumsVO {

    CREATED(0, "创建"),
    COMPLETED(1, "完成"),
    RETRY(2, "重试"),
    FAIL(3, "失败"),
    ;

    private Integer code;

    private String info;

}
