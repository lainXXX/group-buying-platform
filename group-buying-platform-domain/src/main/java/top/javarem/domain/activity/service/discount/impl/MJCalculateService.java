package top.javarem.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.discount.AbstractDiscountCalculateService;
import top.javarem.types.common.constants.Constants;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/11:15
 * @Description: 满减计算服务
 */
@Service("MJ")
@Slf4j
public class MJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount) {

        log.info("折扣优惠服务- 满减计算， 原始价格: {}", originPrice);
//        1.获取折扣表达式- 满减表达式 【100,10 满一百减10元】
        String marketingExpr = groupBuyingDiscount.getMarketingExpr();
//        2.拆分表达式
        String[] split = marketingExpr.split(Constants.COMMA);
//        3.判断表达式是否合法
        if (split.length != 2)
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        BigDecimal x = new BigDecimal(split[0]);
        BigDecimal y = new BigDecimal(split[1]);
//        4.对比原始价格和满减条件
        if (originPrice.compareTo(x) < 0)
            return originPrice;
        BigDecimal deductionPrice = originPrice.subtract(y);

        return judgePrice(deductionPrice);

    }
}
