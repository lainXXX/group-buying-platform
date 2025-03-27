package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.javarem.infrastructure.dao.po.GroupBuyingOrder;

import java.util.List;
import java.util.Set;

/**
* @author aaa
* @description 针对表【group_buying_order(拼团订单)】的数据库操作Mapper
* @createDate 2025-03-09 16:16:05
* @Entity generator.domain.GroupBuyingOrder
*/
@Mapper
public interface GroupBuyingOrderMapper extends BaseMapper<GroupBuyingOrder> {

    Integer getAllCompleteCount(@Param("teamIds") Set<String> teamIds);

    Integer queryAllUserCount(Set<String> teamIds);
}
