package top.javarem.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.javarem.infrastructure.dao.po.CrowdTagsJob;

/**
* @author aaa
* @description 针对表【crowd_tags_job(人群标签任务)】的数据库操作Mapper
* @createDate 2025-03-03 14:20:09
* @Entity generator.domain.CrowdTagsJob
*/
@Mapper
public interface CrowdTagsJobMapper extends BaseMapper<CrowdTagsJob> {


}
