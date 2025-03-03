package top.javarem.domain.tag.adapter.repository;

import top.javarem.domain.tag.model.entity.CrowdTagJobEntity;

/**
 * @Author: rem
 * @Date: 2025/03/03/14:31
 * @Description: 标签仓储接口
 */
public interface ITagRepository {


    CrowdTagJobEntity queryCrowdTagJob(String tagId, String batchId);

    void addCrowdTagDetail(String tagId, String userId);

    void updateCrowdTagStatistics(String tagId, int count);
}
