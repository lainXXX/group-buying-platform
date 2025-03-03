package top.javarem.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/11:16
 * @Description: 打折计算服务
 */
@Service("ZK")
@Slf4j
public class ZKCalculateService  extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount) {

        log.info("折扣优惠服务- 打折计算， 原始价格: {}", originPrice);
//        1.获取折扣表达式-ZK 打多少折
        String marketingExpr = groupBuyingDiscount.getMarketingExpr();
//        2.根据折扣表达式计算折扣
        BigDecimal discountedPrice = originPrice.multiply(new BigDecimal(marketingExpr));

//        3.判断价格
        return judgePrice(discountedPrice);

    }

}
