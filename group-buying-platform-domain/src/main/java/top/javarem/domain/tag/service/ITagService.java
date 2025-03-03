package top.javarem.domain.tag.service;

/**
 * @Author: rem
 * @Date: 2025/03/03/14:31
 * @Description: 标签服务接口
 */
public interface ITagService {

    /**
     * 执行人群标签任务
     * @param tagId 人群标签Id
     * @param batchId 批次Id
     */
    void execCrowdTagJob(String tagId, String batchId);

}
