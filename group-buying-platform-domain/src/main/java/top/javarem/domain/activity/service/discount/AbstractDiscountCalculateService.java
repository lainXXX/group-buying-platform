package top.javarem.domain.activity.service.discount;

import lombok.extern.slf4j.Slf4j;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.vo.DiscountTypeEnum;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: rem
 * @Date: 2025/03/02/10:43
 * @Description: 折扣计算服务抽象类
 */
@Slf4j
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    @Resource
    protected IActivityRepository repository;

//    定义的折扣优惠后的商品最低价格
    private static final BigDecimal LOWEST_PRICE = new BigDecimal("0.01");


    @Override
    public BigDecimal calculate(String userId, BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount) {
//        1.优先处理带标签的用户
        if (DiscountTypeEnum.TAG.equals(groupBuyingDiscount.getDiscountType())) {

            boolean isTagRange = filterTagId(userId, groupBuyingDiscount.getTagId());
//            如果不是目标标签用户 则直接返回原始价格
            if (!isTagRange) {
                log.info("折扣计算拦截 : userId:{}", userId );
                return originPrice;
            }

        }
//        2.优惠折扣计算
        return doCalculate(originPrice, groupBuyingDiscount);

    }

    protected abstract BigDecimal doCalculate(BigDecimal originPrice, GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount);

    /**
     * 过滤标签用户
     * @param userId 用户Id
     * @param tagId 标签Id
     * @return 布尔值
     */
    private boolean filterTagId(String userId, String tagId) {

        return repository.isTagCrowdRange(userId, tagId);

    }

    /**
     * 判断折扣后价格是否低于或等于定义的最低价格
     * @param discountedPrice 折扣价格
     * @return 判断后的价格
     */
    protected BigDecimal judgePrice(BigDecimal discountedPrice) {

        if (LOWEST_PRICE.compareTo(discountedPrice) >= 0) return LOWEST_PRICE;
        return discountedPrice;
    }

}
