package top.javarem.api;

import top.javarem.api.dto.GoodsMarketRequestDTO;
import top.javarem.api.dto.GoodsMarketResponseDTO;
import top.javarem.api.dto.GroupBuyingTeamResponseDTO;
import top.javarem.api.response.Response;

/**
 * @Author: rem
 * @Date: 2025/03/20/10:16
 * @Description:
 */
public interface IMarketIndexService {

    /**
     * 查询拼团营销商品信息
     * @param requestDTO 请求dto
     * @return request
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyingMarketConfig(GoodsMarketRequestDTO requestDTO);

    Response<GroupBuyingTeamResponseDTO> queryGroupBuyingTeams(GoodsMarketRequestDTO requestDTO);


}
