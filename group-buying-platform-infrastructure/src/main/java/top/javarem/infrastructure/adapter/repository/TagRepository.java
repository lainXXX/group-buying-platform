package top.javarem.infrastructure.adapter.repository;

import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import top.javarem.domain.tag.adapter.repository.ITagRepository;
import top.javarem.domain.tag.model.entity.CrowdTagJobEntity;
import top.javarem.infrastructure.dao.po.CrowdTagsDetail;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;
import top.javarem.infrastructure.dao.service.CrowdTagsDetailService;
import top.javarem.infrastructure.dao.service.CrowdTagsJobService;
import top.javarem.infrastructure.dao.service.CrowdTagsService;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.constants.Constants;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/03/15:30
 * @Description: 标签仓储实现类
 */
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private CrowdTagsService crowdTagsService;
    @Resource
    private CrowdTagsDetailService crowdTagsDetailService;
    @Resource
    private CrowdTagsJobService crowdTagsJobService;
    @Resource
    private IRedisService redisService;

    @Override
    public CrowdTagJobEntity queryCrowdTagJob(String tagId, String batchId) {

        CrowdTagsJob crowdTagsJob = crowdTagsJobService.getCrowdTagJob(tagId, batchId);
        if (crowdTagsJob != null) return null;
        return CrowdTagJobEntity.builder()
                .tagId(tagId)
                .batchId(batchId)
                .tagRule(crowdTagsJob.getTagRule())
                .tagType(crowdTagsJob.getTagType())
                .build();

    }

    @Override
    public void addCrowdTagDetail(String tagId, String userId) {

        CrowdTagsDetail crowdTagsDetail = new CrowdTagsDetail();
        crowdTagsDetail.setTagId(tagId);
        crowdTagsDetail.setUserId(userId);

        try {
            crowdTagsDetailService.save(crowdTagsDetail);
            String cacheKey = Constants.RedisKey.BIT_SET_KEY + tagId;
            RBitSet bitSet = redisService.getBitSet(cacheKey);
            bitSet.set(redisService.getIndexFromUserId(userId), true);
        } catch (DuplicateKeyException ignore) {
            // 忽略唯一索引冲突
        }

    }

    @Override
    public void updateCrowdTagStatistics(String tagId, int count) {

        crowdTagsService.updateCrowdTagStatistics(tagId, count);

    }

}
