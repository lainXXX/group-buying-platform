package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.SkuMapper;
import top.javarem.infrastructure.dao.po.Sku;
import top.javarem.infrastructure.dao.service.SkuService;

/**
* @author aaa
* @description 针对表【sku(商品信息)】的数据库操作Service实现
* @createDate 2025-02-27 16:33:01
*/
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku>
implements SkuService {

    @Override
    public Sku querySkuByGoodsId(String goodsId) {
        return lambdaQuery()
                .select(Sku::getGoodsId, Sku::getGoodsName, Sku::getOriginalPrice)
                .eq(Sku::getGoodsId, goodsId)
                .one();
    }

}
