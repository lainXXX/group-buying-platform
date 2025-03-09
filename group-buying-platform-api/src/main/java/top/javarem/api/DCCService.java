package top.javarem.api;

import top.javarem.api.response.Response;

/**
 * @Author: rem
 * @Date: 2025/03/07/14:13
 * @Description:
 */
public interface DCCService {

    Response<Boolean> updateConfig(String key, String value);

}
