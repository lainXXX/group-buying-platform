package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.javarem.infrastructure.dao.po.Sku;

/**
* @author aaa
* @description 针对表【sku(商品信息)】的数据库操作Mapper
* @createDate 2025-02-27 16:33:01
* @Entity generator.domain.Sku
*/
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {


}
