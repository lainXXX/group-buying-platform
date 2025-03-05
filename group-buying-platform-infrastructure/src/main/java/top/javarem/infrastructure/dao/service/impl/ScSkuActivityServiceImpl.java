package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.ScSkuActivityMapper;
import top.javarem.infrastructure.dao.po.ScSkuActivity;
import top.javarem.infrastructure.dao.service.ScSkuActivityService;

/**
* @author aaa
* @description 针对表【sc_sku_activity(渠道商品活动配置关联表)】的数据库操作Service实现
* @createDate 2025-03-05 08:59:37
*/
@Service
public class ScSkuActivityServiceImpl extends ServiceImpl<ScSkuActivityMapper, ScSkuActivity>
implements ScSkuActivityService {

    @Override
    public ScSkuActivity getScSkuActivity(String source, String channel, String goodsId) {

        return lambdaQuery()
                .select(ScSkuActivity::getActivityId, ScSkuActivity::getGoodsId, ScSkuActivity::getSource, ScSkuActivity::getChannel)
                .eq(ScSkuActivity::getSource, source)
                .eq(ScSkuActivity::getChannel, channel)
                .eq(ScSkuActivity::getGoodsId, goodsId)
                .one();

    }
}
