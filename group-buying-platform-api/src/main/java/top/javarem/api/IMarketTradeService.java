package top.javarem.api;

import top.javarem.api.dto.LockMarketPayOrderRequestDTO;
import top.javarem.api.dto.LockMarketPayOrderResponseDTO;
import top.javarem.api.dto.PaySuccessRequestDTO;
import top.javarem.api.response.Response;

/**
 * @Author: rem
 * @Date: 2025/03/09/19:06
 * @Description:
 */
public interface IMarketTradeService {

    /**
     * 订单加锁
     * @param lockMarketPayOrderRequestDTO dto
     * @return response
     */
    Response<LockMarketPayOrderResponseDTO> lockPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

    /**
     * 结算订单
     * @param paySuccessRequestDTO 支付成功请求对象
     * @return boolean
     */
    Response<Boolean> settlePayOrder(PaySuccessRequestDTO paySuccessRequestDTO);


}
