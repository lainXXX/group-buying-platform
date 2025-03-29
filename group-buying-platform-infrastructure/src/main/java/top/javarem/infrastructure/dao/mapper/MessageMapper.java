package top.javarem.infrastructure.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.javarem.infrastructure.dao.po.MessageTask;

/**
* @author aaa
* @description 针对表【message(消息任务表)】的数据库操作Mapper
* @createDate 2025-03-27 15:21:54
* @Entity generator.domain.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<MessageTask> {


}
