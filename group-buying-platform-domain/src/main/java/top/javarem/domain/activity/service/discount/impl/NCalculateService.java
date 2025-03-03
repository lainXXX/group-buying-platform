package top.javarem.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/11:17
 * @Description: N元购优惠计算服务
 */
@Service("N")
@Slf4j
public class NCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount) {

        log.info("折扣优惠服务- N元购优惠计算， 原始价格: {}", originPrice);
//        1.获取折扣表达式-N 直接减少至N元
        String marketingExpr = groupBuyingDiscount.getMarketingExpr();
        return new BigDecimal(marketingExpr);
    }
}
