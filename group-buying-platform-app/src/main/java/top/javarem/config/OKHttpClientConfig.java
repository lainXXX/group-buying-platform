package top.javarem.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rem
 * @Date: 2025/03/17/11:19
 * @Description: 注册okHttp
 */
@Configuration
public class OKHttpClientConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
