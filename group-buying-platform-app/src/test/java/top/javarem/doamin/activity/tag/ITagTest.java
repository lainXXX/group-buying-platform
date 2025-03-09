package top.javarem.doamin.activity.tag;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.tag.service.TagService;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.Constants;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/03/21:48
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ITagTest {

    @Resource
    private TagService tagService;
    @Resource
    private IRedisService redisService;

    @Test
    public void test_execCrowdTagJob() throws Exception {

        tagService.execCrowdTagJob("RQ_KJHKL98UU78H66554GFDV", "10001");

    }

    @Test
    public void test_get_tag_bitmap() {
        String key = Constants.RedisKey.BIT_SET_KEY + "RQ_KJHKL98UU78H66554GFDV";
        RBitSet bitSet = redisService.getBitSet(key);
        // 是否存在
// 是否存在
        log.info("rem 存在，预期结果为 true，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("rem")));
        log.info("gudebai 不存在，预期结果为 false，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("gudebai")));
    }


}
