package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;

/**
* @author aaa
* @description 针对表【group_buy_order_list(拼团订单详情)】的数据库操作Mapper
* @createDate 2025-03-09 16:16:00
* @Entity generator.domain.GroupBuyOrderList
*/
@Mapper
public interface GroupBuyOrderListMapper extends BaseMapper<GroupBuyOrderList> {


    @Select("select count(*) from group_buy_order_list where activity_id = #{activityId} and user_id = #{userId}")
    int queryUserActivityPartakeCount(Long activityId, String userId);
}
