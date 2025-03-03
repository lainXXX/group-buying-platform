package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;

/**
* @author aaa
* @description 针对表【crowd_tags_job(人群标签任务)】的数据库操作Service
* @createDate 2025-03-03 14:20:09
*/
public interface CrowdTagsJobService extends IService<CrowdTagsJob> {

    /**
     * 获取人群标签任务
     * @param tagId
     * @param batchId
     * @return
     */
    CrowdTagsJob getCrowdTagJob(String tagId, String batchId);

}
