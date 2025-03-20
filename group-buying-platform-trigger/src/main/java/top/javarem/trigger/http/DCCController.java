package top.javarem.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javarem.api.DCCService;
import top.javarem.api.response.Response;
import top.javarem.types.common.Constants;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/07/14:10
 * @Description: 动态客户端配置
 */
@RestController
@RequestMapping("/api/v1/dcc")
@CrossOrigin("*")
@Slf4j
public class DCCController implements DCCService {

    @Resource
    private RTopic dccTopic;

    @PostMapping("/update_config")
    @Override
    public Response<Boolean> updateConfig(String key, String value) {

        try {
            log.info("动态更新配置 key: {} value: {}", key, value);
            dccTopic.publish(key + Constants.COMMA + value);
            return Response.success(true);
        } catch (Exception e) {
            return Response.error(false);
        }

    }
}
