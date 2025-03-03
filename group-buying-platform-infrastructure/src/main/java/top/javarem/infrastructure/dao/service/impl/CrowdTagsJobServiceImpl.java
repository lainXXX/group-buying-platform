package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.CrowdTagsJobMapper;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;
import top.javarem.infrastructure.dao.service.CrowdTagsJobService;

/**
* @author rem
* @description 针对表【crowd_tags_job(人群标签任务)】的数据库操作Service实现
* @createDate 2025-03-03 14:20:09
*/
@Service
public class CrowdTagsJobServiceImpl extends ServiceImpl<CrowdTagsJobMapper, CrowdTagsJob>
implements CrowdTagsJobService {

    @Override
    public CrowdTagsJob getCrowdTagJob(String tagId, String batchId) {

        return lambdaQuery()
                .select(CrowdTagsJob::getTagId, CrowdTagsJob::getBatchId, CrowdTagsJob::getTagRule, CrowdTagsJob::getTagType)
                .eq(CrowdTagsJob::getTagId, tagId)
                .eq(CrowdTagsJob::getBatchId, batchId)
                .one();

    }

}
