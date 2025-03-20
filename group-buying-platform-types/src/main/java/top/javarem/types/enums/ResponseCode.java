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
    ILLEGAL_PARAMETER("0002", "非法参数"),
    INDEX_EXCEPTION("0003", "唯一索引冲突"),
    UPDATE_ZERO("0004", "数据库更新数量为0"),
    HTTP_EXCEPTION("0005", "HTTP接口调用异常"),

    E0001("E0001", "不存在对应的折扣服务计算"),
    E0002("E0002", "无拼团营销服务配置"),
    E0003("E0003", "拼团组队失败，数据更新量为0"),
    E0004("E0004", "拼团组队完结，锁单量已达成"),
    E0005("E0005", "活动未生效"),
    E0006("E0006", "非可参与时间范围"),
    E0007("E0007", "用户拼团次数已达上限"),
    E0008("E0008", "用户拼团订单无效"),
    E0009("E0009", "商品已下架"),
    E0010("E0010", "交易时间不在拼团有效时间内"),
    ;


    private String info;

    private String code;


}
