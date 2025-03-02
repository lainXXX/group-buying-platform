package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.Sku;

/**
* @author aaa
* @description 针对表【sku(商品信息)】的数据库操作Service
* @createDate 2025-02-27 16:33:01
*/
public interface SkuService extends IService<Sku> {

    /**
     * 查询商品信息
     *  @param goodsId 商品Id
     * @return Sku
     */
    Sku querySkuByGoodsId(String goodsId);
}
