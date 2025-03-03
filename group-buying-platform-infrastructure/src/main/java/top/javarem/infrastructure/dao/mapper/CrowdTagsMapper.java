package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.javarem.infrastructure.dao.po.CrowdTags;

/**
* @author aaa
* @description 针对表【crowd_tags(人群标签)】的数据库操作Mapper
* @createDate 2025-03-03 14:20:03
* @Entity generator.domain.CrowdTags
*/
@Mapper
public interface CrowdTagsMapper extends BaseMapper<CrowdTags> {


}
