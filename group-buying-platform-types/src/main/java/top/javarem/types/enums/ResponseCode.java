package top.javarem.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: rem
 * @Date: 2025/02/27/17:45
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    INDEX_EXCEPTION("0003", "唯一索引冲突"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    E0001("E0001", "不存在对应的折扣服务计算"),
    E0002("E0002", "无拼团营销服务配置"),
    E0003("E0003", "拼团组队失败，数据更新量为0"),
    E0004("E0006", "拼团组队完结，锁单量已达成"),;


    private String info;

    private String code;


}
