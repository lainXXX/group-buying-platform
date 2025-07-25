package top.javarem.infrastructure.redis;

import org.redisson.api.RBitSet;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/03/16:27
 * @Description: redisson服务工具类
 */
@Service("redissonService")
public class RedissonService implements IRedisService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public RBitSet getBitSet(String key) {

        return redissonClient.getBitSet(key);

    }

    @Override
    public <T> T getValue(String cacheKey) {
        return redissonClient.<T>getBucket(cacheKey).get();
    }

    @Override
    public <T> void setValue(String cacheKey, T dbResult) {
        redissonClient.<T>getBucket(cacheKey).set(dbResult);
    }
}
