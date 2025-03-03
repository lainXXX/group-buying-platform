package top.javarem.infrastructure.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.javarem.infrastructure.dao.po.CrowdTags;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;

/**
* @author aaa
* @description 针对表【crowd_tags(人群标签)】的数据库操作Service
* @createDate 2025-03-03 14:20:03
*/
public interface CrowdTagsService extends IService<CrowdTags> {


    void updateCrowdTagStatistics(String tagId, int count);
}
