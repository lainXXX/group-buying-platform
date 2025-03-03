package top.javarem.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/11:14
 * @Description: 直减计算
 */
@Service("ZJ")
@Slf4j
public class ZJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount) {

        log.info("折扣优惠服务- 直减计算， 原始价格: {}", originPrice);
//        1.获取折扣表达式 - ZJ 直接减少多少元
        String marketingExpr = groupBuyingDiscount.getMarketingExpr();
//        2.解析表达式
        BigDecimal deduction = new BigDecimal(marketingExpr);
        if (deduction.compareTo(originPrice) > 0)
            return originPrice;

        return judgePrice(originPrice.subtract(deduction));

    }
}
