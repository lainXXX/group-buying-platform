package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.javarem.infrastructure.dao.po.NotifyTask;

/**
* @author aaa
* @description 针对表【notify_task(任务回调表)】的数据库操作Mapper
* @createDate 2025-03-13 21:28:48
* @Entity generator.domain.NotifyTask
*/
@Mapper
public interface NotifyTaskMapper extends BaseMapper<NotifyTask> {


}
