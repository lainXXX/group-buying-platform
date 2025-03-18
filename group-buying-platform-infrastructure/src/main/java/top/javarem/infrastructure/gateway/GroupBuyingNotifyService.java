package top.javarem.infrastructure.gateway;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/17/11:20
 * @Description: 拼团回调服务
 */
@Service
@Slf4j
public class GroupBuyingNotifyService {

    @Resource
    private OkHttpClient client;

    public String groupBuyingNotify(String apiUrl, String notifyRequestDTOJson) throws Exception {

        try {
//            构建参数
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, notifyRequestDTOJson);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();
//            发送请求并返回结果
            return client.newCall(request).execute().body().string();
        } catch (Exception e) {
            log.error("拼团回调 http接口回调异常 apiUrl:{}", apiUrl, e);
            throw new AppException(ResponseCode.HTTP_EXCEPTION);
        }

    }

}
