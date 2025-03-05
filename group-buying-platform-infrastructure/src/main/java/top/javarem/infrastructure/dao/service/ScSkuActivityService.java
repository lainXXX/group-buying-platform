package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.ScSkuActivity;

/**
* @author aaa
* @description 针对表【sc_sku_activity(渠道商品活动配置关联表)】的数据库操作Service
* @createDate 2025-03-05 08:59:37
*/
public interface ScSkuActivityService extends IService<ScSkuActivity> {

    ScSkuActivity getScSkuActivity(String source, String channel, String goodsId);
}
