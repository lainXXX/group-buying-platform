package top.javarem.domain.activity.service.discount;

import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/10:09
 * @Description: 折扣计算服务接口
 */
public interface IDiscountCalculateService {

    /**
     * 折扣计算
     * @param userId 用户Id
     * @param originPrice 原始价格
     * @param groupBuyingDiscount 拼团折扣配置
     * @return 优惠价格
     */
    BigDecimal calculate(String userId, BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount);

}
