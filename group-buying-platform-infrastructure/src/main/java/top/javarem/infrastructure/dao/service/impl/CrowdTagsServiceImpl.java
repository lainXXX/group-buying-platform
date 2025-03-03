package top.javarem.infrastructure.dao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.CrowdTagsMapper;
import top.javarem.infrastructure.dao.po.CrowdTags;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;
import top.javarem.infrastructure.dao.service.CrowdTagsService;

/**
* @author aaa
* @description 针对表【crowd_tags(人群标签)】的数据库操作Service实现
* @createDate 2025-03-03 14:20:03
*/
@Service
public class CrowdTagsServiceImpl extends ServiceImpl<CrowdTagsMapper, CrowdTags>
implements CrowdTagsService {

    @Override
    public void updateCrowdTagStatistics(String tagId, int count) {

        lambdaUpdate()
                .setSql("statistics = statistics + " + count)
                .eq(CrowdTags::getTagId, tagId)
                .update();

    }
}
